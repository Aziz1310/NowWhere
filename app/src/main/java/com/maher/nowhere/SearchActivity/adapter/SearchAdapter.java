package com.maher.nowhere.SearchActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Search;

import java.util.ArrayList;

/**
 * Created by RaniaH on 08/10/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Search> lsearch;

    public SearchAdapter(Context mContext, ArrayList<Search> lsearch) {
        this.mContext = mContext;
        this.lsearch = lsearch;
    }

    @Override

    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        SearchAdapter.RecycleView_Holder vh = new SearchAdapter.RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Search search = lsearch.get(position);
        holder.img1.setImageResource(search.getImg1());
        holder.tvName.setText(search.getTvName());
        holder.tvTitle.setText(search.getTvTitle());
        holder.tvDay.setText(search.getTvDay());
        holder.tvMonth.setText(search.getTvMonth());
        holder.tvYear.setText(search.getTvYear());
    }

    @Override
    public int getItemCount() {
        return (null != lsearch ? lsearch.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{


        ImageView img1;
        TextView tvDay, tvMonth, tvYear, tvTitle, tvName;



        public RecycleView_Holder(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvMonth = itemView.findViewById(R.id.tvMonth);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
