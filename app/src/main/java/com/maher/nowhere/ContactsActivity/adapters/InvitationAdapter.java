package com.maher.nowhere.ContactsActivity.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.ProfileActivity.FriendsActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Friend;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by RaniaH on 09/10/2017.
 */

public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<User> amis;

    public interface OnbtnsClickListener {
        void onAcceptBtnClick(User user);

        void onDeclineBtnClick(User user);

    }

    private OnbtnsClickListener onbtnsClickListener;


    public InvitationAdapter(Context mContext, ArrayList<User> amis, OnbtnsClickListener onbtnsClickListener) {

        this.mContext = mContext;
        this.amis = amis;
        this.onbtnsClickListener = onbtnsClickListener;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitation, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        final User amiss = amis.get(position);
        holder.tv_nameAmis.setText(amiss.getName());

        Picasso.with(mContext).
                load(Uri.parse(Urls.IMG_URL_USER + amiss.getImage())).resize(100, 100)
                .into(holder.img_amis);


        holder.img_amis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, FriendsActivity.class));
            }
        });
        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onbtnsClickListener.onDeclineBtnClick(amiss);
            }
        });
        holder.btnAccepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onbtnsClickListener.onAcceptBtnClick(amiss);
            }
        });


    }

    @Override
    public int getItemCount() {
        return amis.size();
    }


    class RecycleView_Holder extends RecyclerView.ViewHolder {

        ImageView img_amis;
        TextView tv_nameAmis;
        Button btnAccepter, btnDecline;

        public RecycleView_Holder(View itemView) {
            super(itemView);
            img_amis = itemView.findViewById(R.id.img_amis);
            tv_nameAmis = itemView.findViewById(R.id.tv_nameAmis);
            btnAccepter = itemView.findViewById(R.id.btnAccepter);
            btnDecline = itemView.findViewById(R.id.btnDecline);
        }
    }
}
