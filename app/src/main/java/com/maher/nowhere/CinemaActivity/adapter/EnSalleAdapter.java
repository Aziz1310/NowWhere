package com.maher.nowhere.CinemaActivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.CinemaActivity.FilmDetailActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.model.EnSalle;

import java.util.ArrayList;

/**
 * Created by RaniaH on 22/11/2017.
 */

public class EnSalleAdapter extends RecyclerView.Adapter<EnSalleAdapter.RecycleView_Holder>{

    private final Context mContext;
    private final ArrayList<EnSalle> enSalles;

    public EnSalleAdapter(Context mContext, ArrayList<EnSalle> enSalles) {
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
        EnSalle enSalle = enSalles.get(position);
        holder.img_film.setImageResource(enSalle.getImage());
        holder.tvTitleFilm.setText(enSalle.getTitle());
        holder.tvLieuFilm.setText(enSalle.getLieu());
        holder.tvDayFilm.setText(enSalle.getDay());
        holder.tvMonthFilm.setText(enSalle.getMonth());
        holder.tvYearFilm.setText(enSalle.getYear());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] screenLocation = new int[2];
                v.getLocationOnScreen(screenLocation);

                Intent intent=new Intent(mContext, FilmDetailActivity.class);
                int orientation = mContext.getResources().getConfiguration().orientation;
                intent.
                        putExtra("orientation", orientation).
                        putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", v.getWidth()).
                        putExtra("height", v.getHeight());
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
