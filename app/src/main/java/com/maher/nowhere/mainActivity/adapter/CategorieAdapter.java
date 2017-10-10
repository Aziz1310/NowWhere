package com.maher.nowhere.mainActivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.categoriesDetail.CategoriesDetailActivity;
import com.maher.nowhere.model.Categorie;

import java.util.ArrayList;

/**
 * Created by RaniaH on 08/10/2017.
 */

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.RecycleView_Holder>{

    private final Context mContext;
    private final ArrayList<Categorie> post;

    public CategorieAdapter(Context context, ArrayList<Categorie> post){

        this.mContext = context;
        this.post = post;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categorie, parent, false);
       RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        final Categorie categ = post.get(position);
        holder.img1.setImageResource(categ.getImg1());
        holder.img2.setImageResource(categ.getImg2());
        holder.textview.setText(categ.getText());
        if(!categ.getText2().isEmpty()){
            if(position==4){
                holder.textView2.setTextSize(18);
                holder.textView2.setText(categ.getText2());
                return;
            }

            holder.textView2.setTextSize(28);
            holder.textView2.setText(categ.getText2());
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, CategoriesDetailActivity.class);
                intent.putExtra("categorie",categ);
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return (null != post ? post.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{


        ImageView img1,img2;
        TextView textview,textView2;



        public RecycleView_Holder(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            textview = itemView.findViewById(R.id.text);
            textView2 = itemView.findViewById(R.id.text2);
        }
    }
}
