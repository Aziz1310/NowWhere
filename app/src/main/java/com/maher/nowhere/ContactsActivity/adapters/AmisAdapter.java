package com.maher.nowhere.ContactsActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.ProfileFriendActivity.ProfileFriendActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Friend;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by RaniaH on 09/10/2017.
 */

public class AmisAdapter extends RecyclerView.Adapter<AmisAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<User> amis;

    public interface OnDeleteFrindListener{
        void ondeleteBtnClick(User user);
    }
private OnDeleteFrindListener onDeleteFrindListener;


    public AmisAdapter(Context mContext, ArrayList<User> amis,OnDeleteFrindListener onDeleteFrindListener) {

        this.mContext = mContext;
        this.amis = amis;
        this.onDeleteFrindListener=onDeleteFrindListener;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amis, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        final User amiss = amis.get(position);
        Picasso.with(mContext).
                load(Uri.parse(Urls.IMG_URL_USER + amiss.getImage())).resize(100, 100)
                .into(holder.img_amis);

        holder.tv_nameAmis.setText(amiss.getName());
        holder.tv_dispoAmis.setText("Offline");

        holder.img_suppAmis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteFrindListener.ondeleteBtnClick(amiss);
            }
        });
        holder.img_amis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ProfileFriendActivity.class);
                intent.putExtra("friend_id",amiss);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return amis.size();
    }


    class RecycleView_Holder extends RecyclerView.ViewHolder{

        ImageView img_amis, img_suppAmis;
        TextView tv_nameAmis, tv_dispoAmis;

        public RecycleView_Holder(View itemView) {
            super(itemView);
            img_amis = itemView.findViewById(R.id.img_amis);
            img_suppAmis = itemView.findViewById(R.id.img_suppAmis);
            tv_nameAmis = itemView.findViewById(R.id.tv_nameAmis);
            tv_dispoAmis = itemView.findViewById(R.id.tv_dispoAmis);
        }
    }
}
