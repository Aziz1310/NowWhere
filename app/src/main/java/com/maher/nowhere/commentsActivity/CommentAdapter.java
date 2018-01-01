package com.maher.nowhere.commentsActivity;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Comment;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Comment> comments;

    public CommentAdapter(Context context, ArrayList<Comment> comments){

        this.mContext = context;
        this.comments = comments;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {

        Comment comment= comments.get(position);
       // holder.tvHeure.setText(post.getHeure());

        String date= String.format("%s %s %s %sh:%s", comment.getDayOfWeek(), comment.getMonth(), comment.getYear(), comment.getHour(), comment.getMinute());
        holder.tvOwner.setText(comment.getOwnerName());
        holder.tvComment.setText(comment.getContenus());
        holder.tvDate.setText(date);

        Picasso.with(mContext).load(Uri.parse(Urls.IMG_URL_USER +comment.getOwnerImage())).into(holder.img, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                System.out.println(" maher image loaded with success");
            }
            @Override
            public void onError() {
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != comments ? comments.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{


        final TextView tvDate,tvOwner,tvComment;
        final CircleImageView img;


        public RecycleView_Holder(View itemView) {
            super(itemView);

            tvDate=itemView.findViewById(R.id.tvDate);
            img=itemView.findViewById(R.id.img);
            tvOwner=itemView.findViewById(R.id.tvOwner);
            tvComment=itemView.findViewById(R.id.tvComment);
        }
    }
}
