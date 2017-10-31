package com.maher.nowhere.ProfileFriendActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Photo;
import com.maher.nowhere.utiles.EqualWidthAndHeightView;

import java.util.ArrayList;

/**
 * Created by RaniaH on 31/10/2017.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.RecycleView_Holder>{

    private final Context mContext;
    private final ArrayList<Photo> photos;

    public PhotosAdapter(Context context, ArrayList<Photo> photos){
        this.mContext = context;
        this.photos = photos;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photos, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {
        Photo photo = photos.get(position);
       // holder.img.setMaxHeight(holder.img.getWidth());
        holder.img.setImageResource(photo.getImage());

    }

    @Override
    public int getItemCount() {
        return (null != photos ? photos.size():0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{

        final EqualWidthAndHeightView img;
        public RecycleView_Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_photo);
        }
    }
}
