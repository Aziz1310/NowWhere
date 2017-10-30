package com.maher.nowhere.ContactsActivity.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.ProfileActivity.FriendsActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Friend;

import java.util.ArrayList;

/**
 * Created by RaniaH on 09/10/2017.
 */

public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Friend> amis;



    public InvitationAdapter(Context mContext, ArrayList<Friend> amis) {

        this.mContext = mContext;
        this.amis = amis;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitation, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Friend amiss = amis.get(position);
        holder.img_amis.setImageResource(amiss.getProfileImage());
        holder.tv_nameAmis.setText(amiss.getNom());

        holder.img_amis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, FriendsActivity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return amis.size();
    }


    class RecycleView_Holder extends RecyclerView.ViewHolder{

        ImageView img_amis;
        TextView tv_nameAmis;

        public RecycleView_Holder(View itemView) {
            super(itemView);
            img_amis = itemView.findViewById(R.id.img_amis);
            tv_nameAmis = itemView.findViewById(R.id.tv_nameAmis);
        }
    }
}
