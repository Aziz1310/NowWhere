package com.maher.nowhere.RestaurantProfileActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.MenuV;

import java.util.ArrayList;

/**
 * Created by RaniaH on 09/11/2017.
 */

public class MenuVAdapter extends RecyclerView.Adapter<MenuVAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<MenuV> menuV;

    public MenuVAdapter(Context context, ArrayList<MenuV> menuV){
        this.mContext = context;
        this.menuV = menuV;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_v, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        MenuV menuVs = menuV.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != menuV ? menuV.size() : 0);
    }


    class RecycleView_Holder extends RecyclerView.ViewHolder{
        final TextView menuNom, menuPrst, menuPrix;
        public RecycleView_Holder(View itemView) {
            super(itemView);
            menuNom = itemView.findViewById(R.id.menu_nom);
            menuPrst = itemView.findViewById(R.id.menu_prst);
            menuPrix = itemView.findViewById(R.id.menu_prix);
        }
    }
}
