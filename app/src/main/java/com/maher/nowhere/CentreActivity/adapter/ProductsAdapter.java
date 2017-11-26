package com.maher.nowhere.CentreActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Product;

import java.util.ArrayList;

/**
 * Created by RaniaH on 25/11/2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Product> product;

    public ProductsAdapter(Context mContext, ArrayList<Product> product) {
        this.mContext = mContext;
        this.product = product;
    }


    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Product products = product.get(position);
        holder.img_product.setImageResource(products.getProduitImage());
        holder.tv_titleProduct.setText(products.getTitleProduit());
        holder.tv_nameProduct.setText(products.getNomProduit());
        holder.prix_product.setText(products.getPrixProduit());

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{

        ImageView img_product;
        TextView tv_titleProduct, tv_nameProduct, prix_product;

        public RecycleView_Holder(View itemView) {
            super(itemView);
            img_product = itemView.findViewById(R.id.img_product);
            tv_titleProduct = itemView.findViewById(R.id.tv_titleProduct);
            tv_nameProduct = itemView.findViewById(R.id.tv_nameProduct);
            prix_product = itemView.findViewById(R.id.prix_product);
        }
    }
}
