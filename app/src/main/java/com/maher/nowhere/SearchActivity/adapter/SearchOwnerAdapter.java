package com.maher.nowhere.SearchActivity.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.CentreActivity.CentreActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.RestaurantProfileActivity.RestaurantProfileActivity;
import com.maher.nowhere.SalleDeSportActivity.SalleSportActivity;
import com.maher.nowhere.SearchDetailActivity.SearchDetailActivity;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by RaniaH on 08/10/2017.
 */

public class SearchOwnerAdapter extends RecyclerView.Adapter<SearchOwnerAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Owner> lsearch;
    public final static String CAT_RESTAURANT = "Réstaurant";
    public final static String CAT_SALLE = "Centres";
    public final static String CAT_ART = "Art";
    public final static String CAT_Disco = "Discos";
    public final static String CAT_CAFFE = "Caffées";
    public final static String CAT_MIND  = "Mind";
    private String categorie;
    private OnDeleteFrindListener onDeleteFrindListener;

    public interface OnDeleteFrindListener {
        void ondeleteBtnClick(Post post);
    }

    public SearchOwnerAdapter(Context mContext, ArrayList<Owner> lsearch, String categorie) {
        this.mContext = mContext;
        this.lsearch = lsearch;
        this.categorie = categorie;
    }

    @Override

    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        SearchOwnerAdapter.RecycleView_Holder vh = new SearchOwnerAdapter.RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(final RecycleView_Holder holder, final int position) {
        final Owner search = lsearch.get(position);

        holder.btnDelet.setVisibility(View.GONE);

        Picasso.with(mContext).load(Uri.parse(search.getUrlImage())).into(holder.img1, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                System.out.println(" maher image loaded with success");
            }

            @Override
            public void onError() {
            }
        });
        holder.tvName.setText(search.getAdresse());
        holder.tvTitle.setText(search.getNom());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;


                switch (categorie) {
                    case CAT_RESTAURANT:
                    case CAT_Disco:
                    case CAT_CAFFE:
                        intent = new Intent(mContext, RestaurantProfileActivity.class);
                        intent.putExtra("owner",search);
                        intent.putExtra("categorie",categorie);
                        break;
                    case CAT_SALLE:
                        intent = new Intent(mContext, SalleSportActivity.class);
                        intent.putExtra("owner",search);
                        intent.putExtra("categorie",categorie);
                        break;
                    case CAT_ART:
                        intent = new Intent(mContext, CentreActivity.class);
                        intent.putExtra("owner",search);
                        intent.putExtra("categorie",categorie);
                        break;
                    default:
                        intent = new Intent(mContext, SearchDetailActivity.class);
                        intent.putExtra("owner", search);

                }
                mContext.startActivity(intent);
                Activity a = (Activity) mContext;
                a.overridePendingTransition(R.anim.fragment_fade_in, R.anim.fragment_fade_out);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != lsearch ? lsearch.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder {


        final ImageView img1, btnDelet;
        final TextView tvDay, tvMonth, tvYear, tvTitle, tvName;


        public RecycleView_Holder(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            btnDelet = itemView.findViewById(R.id.btnDelet);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvMonth = itemView.findViewById(R.id.tvMonth);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvName = itemView.findViewById(R.id.tvName);

        }
    }
}
