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
import com.maher.nowhere.CinemaActivity.CinemaActivity;
import com.maher.nowhere.ContactsActivity.adapters.AmisAdapter;
import com.maher.nowhere.R;
import com.maher.nowhere.RestaurantProfileActivity.RestaurantProfileActivity;
import com.maher.nowhere.SalleDeSportActivity.SalleSportActivity;
import com.maher.nowhere.SearchDetailActivity.SearchDetailActivity;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Search;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.RecyclerViewPositionHelper;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by RaniaH on 08/10/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Post> lsearch;
    public final static String CAT_RESTAURANT = "Réstaurant";
    public final static String CAT_SALLE = "Centres";
    public final static String CAT_ART = "Art";
    private String categorie;
    private OnDeleteFrindListener onDeleteFrindListener;

    public interface OnDeleteFrindListener{
        void ondeleteBtnClick(Post post);
    }

    public SearchAdapter(Context mContext, ArrayList<Post> lsearch, String categorie,OnDeleteFrindListener onDeleteFrindListener) {
        this.mContext = mContext;
        this.lsearch = lsearch;
        this.categorie = categorie;
        this.onDeleteFrindListener=onDeleteFrindListener;
    }
    public SearchAdapter(Context mContext, ArrayList<Post> lsearch, String categorie) {
        this.mContext = mContext;
        this.lsearch = lsearch;
        this.categorie = categorie;
    }

    @Override

    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        SearchAdapter.RecycleView_Holder vh = new SearchAdapter.RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(final RecycleView_Holder holder, final int position) {
        final Post search = lsearch.get(position);

        if (categorie.equals("profile")){
            holder.btnDelet.setVisibility(View.VISIBLE);
            holder.btnDelet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteFrindListener.ondeleteBtnClick(search);
                }
            });
        }

       // holder.img1.setImageResource(search.getImage());
        Picasso.with(mContext).load(Uri.parse(search.getUrlImage())).into(holder.img1, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                System.out.println(" maher image loaded with success");
            }
            @Override
            public void onError() {
            }
        });
        holder.tvName.setText(search.getName());
        holder.tvTitle.setText(search.getTitle());
        holder.tvDay.setText(String.format("%s", search.getDayOfWeek()));
        holder.tvMonth.setText(String.format("%s", search.getMonthNumber()));
        holder.tvYear.setText(search.getYear());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;


                switch (categorie) {
                    case CAT_RESTAURANT:
                        intent = new Intent(mContext, RestaurantProfileActivity.class);
                        break;
                    case CAT_SALLE:
                        intent = new Intent(mContext, SalleSportActivity.class);
                        break;
                    default:
                        intent = new Intent(mContext, SearchDetailActivity.class);
                        intent.putExtra("post",search);

                }


                int[] screenLocation = new int[2];
                v.getLocationOnScreen(screenLocation);


                int orientation = mContext.getResources().getConfiguration().orientation;
                intent.
                        putExtra("orientation", orientation).
                        putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", v.getWidth()).
                        putExtra("height", v.getHeight());


                mContext.startActivity(intent);
                // Override transitions: we don't want the normal window animation in addition
                // to our custom one
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


        final ImageView img1,btnDelet;
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
