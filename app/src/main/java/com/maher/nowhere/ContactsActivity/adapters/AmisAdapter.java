package com.maher.nowhere.ContactsActivity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Friend;

import java.util.ArrayList;

/**
 * Created by RaniaH on 09/10/2017.
 */

public class AmisAdapter extends RecyclerView.Adapter<AmisAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Friend> amis;



    public AmisAdapter(Context mContext, ArrayList<Friend> amis) {

        this.mContext = mContext;
        this.amis = amis;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amis, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Friend amiss = amis.get(position);
        holder.img_amis.setImageResource(amiss.getProfileImage());
        holder.tv_nameAmis.setText(amiss.getNom());
        holder.tv_dispoAmis.setText(amiss.getDisponibility());

        holder.img_suppAmis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // taw ba3ed hethi ne5dmouha supprime
            }
        });

    }

    @Override
    public int getItemCount() {
        return amis.size();
    }


    class RecycleView_Holder extends RecyclerView.ViewHolder{

        ImageView img_amis, img_suppAmis;
        TextView tv_nameAmis, tv_dispoAmis;

        public RecycleView_Holder(View itemView) {
            super(itemView);
            img_amis = itemView.findViewById(R.id.img_amis);
            img_suppAmis = itemView.findViewById(R.id.img_suppAmis);
            tv_nameAmis = itemView.findViewById(R.id.tv_nameAmis);
            tv_dispoAmis = itemView.findViewById(R.id.tv_dispoAmis);
        }
    }
}
