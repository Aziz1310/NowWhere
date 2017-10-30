package com.maher.nowhere.ProfileActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Friend;
import com.maher.nowhere.model.Reservation;

import java.util.ArrayList;

/**
 * Created by RaniaH on 09/10/2017.
 */

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Reservation> reservations;



    public ReservationAdapter(Context mContext, ArrayList<Reservation> reservations) {

        this.mContext = mContext;
        this.reservations = reservations;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Reservation reservation = reservations.get(position);
        holder.tv_num.setText(reservation.getResNum());
        holder.tv_date.setText(reservation.getDate());
        if(reservation.isStatus()==true)
            holder.img_suppAmis.setImageResource(R.drawable.icon_res_approve);
        else
            holder.img_suppAmis.setImageResource(R.drawable.icon_res_delete);


        holder.img_suppAmis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }


    class RecycleView_Holder extends RecyclerView.ViewHolder{

        ImageView img_suppAmis;
        TextView tv_num, tv_date;

        public RecycleView_Holder(View itemView) {
            super(itemView);
            img_suppAmis = itemView.findViewById(R.id.img_suppAmis);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_num = itemView.findViewById(R.id.tv_res_num);
        }
    }
}
