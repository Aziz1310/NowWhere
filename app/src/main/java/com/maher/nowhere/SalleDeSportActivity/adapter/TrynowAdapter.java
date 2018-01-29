package com.maher.nowhere.SalleDeSportActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Pack;
import com.maher.nowhere.model.Trynow;

import java.util.ArrayList;

public class TrynowAdapter extends RecyclerView.Adapter<TrynowAdapter.RecycleView_Holder>{

    private final Context mContext;
    private final ArrayList<Pack> trynow;

    public TrynowAdapter(Context mContext, ArrayList<Pack> trynow) {
        this.mContext = mContext;
        this.trynow = trynow;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trynow, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(final RecycleView_Holder holder, int position) {
        final Pack pack = trynow.get(position);
        holder.tv_pack.setText(pack.getNom());
        holder.pack_prix.setText(String.format("%s DT / 3 MOIS", pack.getType1()));
        holder.pack_prst.setText(pack.getDetails().toString());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (holder.btnArrow.getRotation()==180f){
                   holder.btnArrow.setRotation(0f);
                   holder.pack_prst.setText("");
                   holder.pack_prix.setText(String.format("%s DT / 3 MOIS\n%s DT / 3 MOIS\n%s DT / 3 MOIS\n%s DT / 3 MOIS",
                           pack.getType1(), pack.getType2(), pack.getType3(), pack.getType4()));
                   for (String s:pack.getDetails()){
                       holder.pack_prst.setText(String.format("%s\n%s", holder.pack_prst.getText().toString(), s));
                   }
               }else {
                   holder.btnArrow.setRotation(180f);
                   holder.pack_prst.setText(pack.getDetails().toString());
                   holder.pack_prix.setText(String.format("%s DT / 3 MOIS", pack.getType1()));

               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != trynow ? trynow.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{
        final TextView tv_pack, pack_prst, pack_prix;
        final ImageView btnArrow;
        final LinearLayout ll;
        public RecycleView_Holder(View itemView) {
            super(itemView);
            tv_pack = itemView.findViewById(R.id.tv_pack);//pack name
            pack_prst = itemView.findViewById(R.id.pack_prst);
            pack_prix = itemView.findViewById(R.id.pack_prix);
            btnArrow=itemView.findViewById(R.id.btnArrow);
            ll=itemView.findViewById(R.id.ll);
        }
    }
}
