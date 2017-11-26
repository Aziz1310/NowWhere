package com.maher.nowhere.ContactsActivity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Suggestions;

import java.util.ArrayList;

/**
 * Created by RaniaH on 24/11/2017.
 */

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Suggestions> suggestionss;

    public SuggestionsAdapter(Context mContext, ArrayList<Suggestions> suggestionss) {
        this.mContext = mContext;
        this.suggestionss = suggestionss;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggestions, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Suggestions suggestions = suggestionss.get(position);
        holder.tv_communAmisSugg.setText(suggestions.getAmisCommun());
        holder.tv_nameAmisSugg.setText(suggestions.getNom());
        holder.img_addAmisSugg.setImageResource(R.drawable.profile_add);
        holder.img_addAmisSugg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return suggestionss.size();
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{

        ImageView img_amisSugg, img_addAmisSugg;
        TextView tv_nameAmisSugg, tv_communAmisSugg;

        public RecycleView_Holder(View itemView) {
            super(itemView);
            img_amisSugg = itemView.findViewById(R.id.img_amisSugg);
            img_addAmisSugg = itemView.findViewById(R.id.img_addAmisSugg);
            tv_nameAmisSugg = itemView.findViewById(R.id.tv_nameAmisSugg);
            tv_communAmisSugg = itemView.findViewById(R.id.tv_communAmisSugg);
        }
    }
}
