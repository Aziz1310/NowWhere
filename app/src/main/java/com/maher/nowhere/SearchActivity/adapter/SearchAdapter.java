package com.maher.nowhere.SearchActivity.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.SearchDetailActivity.SearchDetailActivity;
import com.maher.nowhere.model.Search;
import com.maher.nowhere.utiles.RecyclerViewPositionHelper;

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
    public void onBindViewHolder(final RecycleView_Holder holder, final int position) {
        Search search = lsearch.get(position);
        holder.img1.setImageResource(search.getImage());
        holder.tvName.setText(search.getName());
        holder.tvTitle.setText(search.getTitle());
        holder.tvDay.setText(search.getDay());
        holder.tvMonth.setText(search.getMonth());
        holder.tvYear.setText(search.getYear());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int[] screenLocation = new int[2];
                v.getLocationOnScreen(screenLocation);

                Intent intent=new Intent(mContext, SearchDetailActivity.class);

                int orientation = mContext.getResources().getConfiguration().orientation;
                intent.
                        putExtra("orientation", orientation).
                        putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", v.getWidth()).
                        putExtra("height", v.getHeight());


                mContext.startActivity(intent);
                // Override transitions: we don't want the normal window animation in addition
                // to our custom one
                Activity a=(Activity)mContext;
                a.overridePendingTransition(R.anim.fragment_fade_in, R.anim.fragment_fade_out);
            }
        });
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
