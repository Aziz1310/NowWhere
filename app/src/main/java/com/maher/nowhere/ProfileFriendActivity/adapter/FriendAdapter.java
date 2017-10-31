package com.maher.nowhere.ProfileFriendActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.AmisFriend;

import java.util.ArrayList;

/**
 * Created by RaniaH on 31/10/2017.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<AmisFriend> amisFriends;

    public FriendAdapter(Context mContext, ArrayList<AmisFriend> amisFriends) {
        this.mContext = mContext;
        this.amisFriends = amisFriends;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        AmisFriend amisFriend = amisFriends.get(position);
        holder.img_friendAmis.setImageResource(amisFriend.getProfileImage());
        holder.tv_dispoFriend.setText(amisFriend.getNom());
        holder.tv_nameFriend.setText(amisFriend.getNom());
        holder.img_suppFriend.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return amisFriends.size();
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{

        ImageView img_friendAmis, img_suppFriend;
        TextView tv_nameFriend, tv_dispoFriend;

        public RecycleView_Holder(View itemView) {
            super(itemView);
            img_friendAmis = itemView.findViewById(R.id.img_friendAmis);
            img_suppFriend = itemView.findViewById(R.id.img_suppFriend);
            tv_nameFriend = itemView.findViewById(R.id.tv_nameFriend);
            tv_dispoFriend = itemView.findViewById(R.id.tv_dispoFriend);
        }
    }
}
