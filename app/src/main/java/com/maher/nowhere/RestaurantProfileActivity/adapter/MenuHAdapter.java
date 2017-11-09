package com.maher.nowhere.RestaurantProfileActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.MenuH;

import java.util.ArrayList;

/**
 * Created by RaniaH on 09/11/2017.
 */

public class MenuHAdapter extends RecyclerView.Adapter<MenuHAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<MenuH> menuH;

    public MenuHAdapter (Context context, ArrayList<MenuH> menuH){
        this.mContext = context;
        this.menuH = menuH;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_h, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        MenuH menuHs = menuH.get(position);

    }

    @Override
    public int getItemCount() {
        return (null != menuH ? menuH.size() : 0);
    }


    class RecycleView_Holder extends RecyclerView.ViewHolder{
        final TextView categorie;
        public RecycleView_Holder(View itemView) {
            super(itemView);
            categorie = itemView.findViewById(R.id.menu_categ);
        }
    }
}
