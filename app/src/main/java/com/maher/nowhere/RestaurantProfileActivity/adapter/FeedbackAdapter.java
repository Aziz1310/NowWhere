package com.maher.nowhere.RestaurantProfileActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Feedback;

import java.util.ArrayList;

/**
 * Created by RaniaH on 01/12/2017.
 */

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.RecycleView_Holder>{

    private final Context mContext;
    private final ArrayList<Feedback> feedbacks;

    public FeedbackAdapter(Context mContext, ArrayList<Feedback> feedbacks) {
        this.mContext = mContext;
        this.feedbacks = feedbacks;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Feedback feedback = feedbacks.get(position);
    }

    @Override
    public int getItemCount() {
        return (null !=feedbacks ? feedbacks.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{

        final TextView nomProf, comment_avis, time_avis;
        public RecycleView_Holder(View itemView) {
            super(itemView);
            nomProf=itemView.findViewById(R.id.tv_nomProf);
            comment_avis=itemView.findViewById(R.id.comment_avis);
            time_avis=itemView.findViewById(R.id.time_avis);
        }
    }
}
