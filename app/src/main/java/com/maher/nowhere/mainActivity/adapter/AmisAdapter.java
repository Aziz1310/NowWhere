package com.maher.nowhere.mainActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Ams;

import java.util.ArrayList;

/**
 * Created by RaniaH on 09/10/2017.
 */

public class AmisAdapter extends RecyclerView.Adapter<AmisAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Ams> amis;



    public AmisAdapter(Context mContext, ArrayList<Ams> amis) {

        this.mContext = mContext;
        this.amis = amis;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amis, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Ams amiss = amis.get(position);
        holder.img_amis.setImageResource(amiss.getImg_amis());
        holder.img_suppAmis.setImageResource(amiss.getImg_suppAmis());
        holder.tv_nameAmis.setText(amiss.getTv_nameAmis());
        holder.tv_dispoAmis.setText(amiss.getTv_dispoAmis());

    }

    @Override
    public int getItemCount() {
        return 0;
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
