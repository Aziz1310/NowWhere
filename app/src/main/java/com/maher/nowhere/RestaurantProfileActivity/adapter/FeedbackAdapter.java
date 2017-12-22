package com.maher.nowhere.RestaurantProfileActivity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Feedback;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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
        holder.nomProf.setText(feedback.getUser().getName());
        holder.comment_avis.setText(feedback.getContenu());
        holder.ratingBar.setRating(Float.parseFloat(feedback.getUserNote()));

        Picasso.with(mContext).load(Uri.parse(Urls.IMG_URL_USER +feedback.getUser().getImage())).into(holder.img, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                System.out.println(" maher image loaded with success");
            }
            @Override
            public void onError() {
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null !=feedbacks ? feedbacks.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{

        final TextView nomProf, comment_avis, time_avis;
        final CircleImageView img;
        final RatingBar ratingBar;
        public RecycleView_Holder(View itemView) {
            super(itemView);
            nomProf=itemView.findViewById(R.id.tv_nomProf);
            comment_avis=itemView.findViewById(R.id.comment_avis);
            time_avis=itemView.findViewById(R.id.time_avis);
            img=itemView.findViewById(R.id.prof_img);
            ratingBar=itemView.findViewById(R.id.rb_resto);
        }
    }
}
