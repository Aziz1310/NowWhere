package com.maher.nowhere.mainActivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.categoriesDetail.CategoriesDetailActivity;
import com.maher.nowhere.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class AcceuilAdapter extends RecyclerView.Adapter<AcceuilAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Post> posts;

    public AcceuilAdapter(Context context, ArrayList<Post> posts){

        this.mContext = context;
        this.posts = posts;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accueil, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {

        Post post= posts.get(position);
       // holder.tvHeure.setText(post.getHeure());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, CategoriesDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
        /*
        Picasso.with(mContext).load(Uri.parse(position.getIconUrl())).into(holder.img, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                System.out.println(" maher image loaded with success");
            }
            @Override
            public void onError() {
            }
        });
        */
    }



    @Override
    public int getItemCount() {
        return (null != posts ? posts.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{


        final TextView tvDay,tvMonth,tvYear,tvHeure;
        //final ImageView img;



        public RecycleView_Holder(View itemView) {
            super(itemView);

            tvDay=itemView.findViewById(R.id.tvDay);
            tvMonth=itemView.findViewById(R.id.tvMonth);
            tvYear=itemView.findViewById(R.id.tvYear);
            tvHeure=itemView.findViewById(R.id.tvheure);

        }
    }
}
