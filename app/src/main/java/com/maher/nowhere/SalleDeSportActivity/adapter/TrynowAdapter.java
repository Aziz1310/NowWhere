package com.maher.nowhere.SalleDeSportActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Trynow;

import java.util.ArrayList;

/**
 * Created by RaniaH on 24/11/2017.
 */

public class TrynowAdapter extends RecyclerView.Adapter<TrynowAdapter.RecycleView_Holder>{

    private final Context mContext;
    private final ArrayList<Trynow> trynow;

    public TrynowAdapter(Context mContext, ArrayList<Trynow> trynow) {
        this.mContext = mContext;
        this.trynow = trynow;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trynow, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Trynow trynows = trynow.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != trynow ? trynow.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{
        final TextView tv_pack, pack_prst, pack_prix;
        public RecycleView_Holder(View itemView) {
            super(itemView);
            tv_pack = itemView.findViewById(R.id.tv_pack);
            pack_prst = itemView.findViewById(R.id.pack_prst);
            pack_prix = itemView.findViewById(R.id.pack_prix);
        }
    }
}
