package com.maher.nowhere.CinemaActivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.CinemaActivity.FilmDetailActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.model.EnSalle;
import com.maher.nowhere.model.Film;
import com.maher.nowhere.model.Search;
import com.maher.nowhere.utiles.Urls;
import com.maher.nowhere.utiles.Utiles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by RaniaH on 22/11/2017.
 */

public class EnSalleAdapter extends RecyclerView.Adapter<EnSalleAdapter.RecycleView_Holder>{

    private final Context mContext;
    private final ArrayList<Film> enSalles;

    public EnSalleAdapter(Context mContext, ArrayList<Film> enSalles) {
        this.mContext = mContext;
        this.enSalles = enSalles;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_en_salle, parent, false);
        EnSalleAdapter.RecycleView_Holder vh = new EnSalleAdapter.RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        final Film enSalle = enSalles.get(position);

        Picasso.with(mContext).load(Uri.parse(Urls.IMG_URL_film +enSalle.getPhoto())).into(holder.img_film);




        holder.tvTitleFilm.setText(enSalle.getNom());
        holder.tvLieuFilm.setText(enSalle.getOwner().getNom());
        enSalle.setcDate(new Utiles().parseDate2(enSalle.getDateDebut()));

         holder.tvDayFilm.setText(enSalle.getDayOfWeek());
         holder.tvMonthFilm.setText(enSalle.getMonthNumber());
         holder.tvYearFilm.setText(enSalle.getYear());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, FilmDetailActivity.class);
                intent.putExtra("film",enSalle);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != enSalles ? enSalles.size() : 0);
    }



    class RecycleView_Holder extends RecyclerView.ViewHolder{

        ImageView img_film;
        TextView tvDayFilm, tvMonthFilm, tvYearFilm, tvTitleFilm, tvLieuFilm;

        public RecycleView_Holder(View itemView) {
            super(itemView);
            img_film = itemView.findViewById(R.id.img_film);
            tvDayFilm = itemView.findViewById(R.id.tvDayFilm);
            tvMonthFilm = itemView.findViewById(R.id.tvMonthFilm);
            tvYearFilm = itemView.findViewById(R.id.tvYearFilm);
            tvTitleFilm = itemView.findViewById(R.id.tvTitleFilm);
            tvLieuFilm = itemView.findViewById(R.id.tvLieuFilm);
        }
    }
}
