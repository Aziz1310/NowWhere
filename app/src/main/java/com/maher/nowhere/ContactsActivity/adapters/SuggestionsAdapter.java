package com.maher.nowhere.ContactsActivity.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Suggestions;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by RaniaH on 24/11/2017.
 */

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.RecycleView_Holder> {

    public interface OnSendInvitListener{
        void onSendBtnClick(User user);
        void onItemClick(User user);
    }

    private final Context mContext;
    private final ArrayList<User> suggestionss;
    private OnSendInvitListener listener;

    public SuggestionsAdapter(Context mContext, ArrayList<User> suggestionss,OnSendInvitListener listener) {
        this.mContext = mContext;
        this.suggestionss = suggestionss;
        this.listener=listener;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggestions, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        final User user = suggestionss.get(position);
        holder.tv_communAmisSugg.setText("5 amis en commun");
        holder.tv_nameAmisSugg.setText(user.getName());

        Picasso.with(mContext).
                load(Uri.parse(Urls.IMG_URL_USER + user.getImage())).resize(100, 100)
                .into(holder.img_amisSugg);

        holder.img_addAmisSugg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                listener.onSendBtnClick(user);
            }
        });
        holder.img_addAmisSugg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(user);
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
