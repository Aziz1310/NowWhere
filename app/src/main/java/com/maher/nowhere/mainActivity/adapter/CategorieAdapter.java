package com.maher.nowhere.mainActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Categ;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by RaniaH on 08/10/2017.
 */

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.RecycleView_Holder>{

    private final Context mContext;
    private final ArrayList<Categ> post;

    public CategorieAdapter(Context context, ArrayList<Categ> post){

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
        Categ categ = post.get(position);



    }

    @Override
    public int getItemCount() {
        return (null != post ? post.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{


        ImageView img1,img2;
        TextView textview;



        public RecycleView_Holder(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            //textview = itemView.findViewById(R.id.textview);
        }
    }
}
