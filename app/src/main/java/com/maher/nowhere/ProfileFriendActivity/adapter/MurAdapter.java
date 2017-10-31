package com.maher.nowhere.ProfileFriendActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Mur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaniaH on 31/10/2017.
 */

public class MurAdapter extends RecyclerView.Adapter<MurAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Mur> mur;

    public MurAdapter(Context context, ArrayList<Mur> mur){
        this.mContext = context;
        this.mur = mur;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Mur murs = mur.get(position);
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mur, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public int getItemCount() {
        return (null != mur ? mur.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{

        final TextView tvDay,tvMonth,tvYear,tvHeure;
        public RecycleView_Holder(View itemView) {
            super(itemView);
            tvDay=itemView.findViewById(R.id.dayTV);
            tvMonth=itemView.findViewById(R.id.monthTV);
            tvYear=itemView.findViewById(R.id.yearTV);
            tvHeure=itemView.findViewById(R.id.heureTV);
        }
    }
}
