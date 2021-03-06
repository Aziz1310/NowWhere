package com.maher.nowhere.chat.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maher.nowhere.ProfileFriendActivity.ProfileFriendActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.model.ChatRoom;
import com.maher.nowhere.model.Conversation;
import com.maher.nowhere.model.ConversationUser;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChatRoomsAdapter extends RecyclerView.Adapter<ChatRoomsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Conversation> chatRoomArrayList;
    private static String today;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, message, timestamp, count;
        public CircleImageView img;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            message = (TextView) view.findViewById(R.id.message);
            timestamp = (TextView) view.findViewById(R.id.timestamp);
            count = (TextView) view.findViewById(R.id.count);
            img = (CircleImageView) view.findViewById(R.id.img);
        }
    }


    public ChatRoomsAdapter(Context mContext, ArrayList<Conversation> chatRoomArrayList) {
        this.mContext = mContext;
        this.chatRoomArrayList = chatRoomArrayList;

        Calendar calendar = Calendar.getInstance();
        today = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_rooms_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Conversation chatRoom = chatRoomArrayList.get(position);
        User currentUser = User.getCurrentUser(mContext);
        ConversationUser conversationUser;
        if (String.valueOf(currentUser.getId()).equals(chatRoom.getUser1().getId()))
            conversationUser = chatRoom.getUser2();
        else conversationUser = chatRoom.getUser1();


        holder.name.setText(conversationUser.getName());
        //    holder.message.setText(chatRoom.getLastMessage());
        /*if (chatRoom.getUnreadCount() > 0) {
            holder.count.setText(String.valueOf(chatRoom.getUnreadCount()));
            holder.count.setVisibility(View.VISIBLE);
        } else {
            holder.count.setVisibility(View.GONE);
        }*/
        Picasso.with(mContext).
                load(Uri.parse(Urls.IMG_URL_USER + conversationUser.getPhoto())).resize(100, 100)
                .into(holder.img, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

        //  holder.timestamp.setText(getTimeStamp(chatRoom.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return chatRoomArrayList.size();
    }

    public static String getTimeStamp(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+'SS:SS", Locale.FRANCE);
        String timestamp = "";

        today = today.length() < 2 ? "0" + today : today;

        try {
            Date date = format.parse(dateStr);
            SimpleDateFormat todayFormat = new SimpleDateFormat("dd", Locale.FRANCE);
            String dateToday = todayFormat.format(date);
            format = dateToday.equals(today) ? new SimpleDateFormat("hh:mm a", Locale.FRANCE) : new SimpleDateFormat("dd LLL, hh:mm a", Locale.FRANCE);
            String date1 = format.format(date);
            timestamp = date1.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timestamp;
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ChatRoomsAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ChatRoomsAdapter.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
