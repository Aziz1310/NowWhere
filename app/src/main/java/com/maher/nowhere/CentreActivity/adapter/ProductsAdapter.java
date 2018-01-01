package com.maher.nowhere.CentreActivity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Product;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by RaniaH on 25/11/2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Product> products;

    public ProductsAdapter(Context mContext, ArrayList<Product> product) {
        this.mContext = mContext;
        this.products = product;
    }


    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Product product = products.get(position);


        Picasso.with(mContext).load(Uri.parse(Urls.IMG_URL_produit + product.getImg())).into(holder.img_product);




        holder.tv_titleProduct.setText(product.getNom());
        holder.tv_nameProduct.setText(product.getDescription());
        holder.prix_product.setText(String.format("%s DT", product.getPrix()));

    }

    @Override
    public int getItemCount() {
        return products.size();
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
