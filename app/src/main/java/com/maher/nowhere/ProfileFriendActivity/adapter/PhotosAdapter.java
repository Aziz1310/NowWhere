package com.maher.nowhere.ProfileFriendActivity.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maher.nowhere.PhotoActivity.PhotoActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Photo;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.utiles.EqualWidthAndHeightView;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by RaniaH on 31/10/2017.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.RecycleView_Holder> {

    private final Context mContext;
    private ArrayList<Publication> publications;
    private ArrayList<Photo> photos;

    public PhotosAdapter(Context context, ArrayList<Publication> photos) {
        this.mContext = context;
        this.publications = photos;
    }

    public PhotosAdapter(Context context, ArrayList<Photo> photos, int d) {
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
    public void onBindViewHolder(final RecycleView_Holder holder, int position) {

        String url = "";
        String description = "";
        if (publications != null) {
            final Publication photo = publications.get(position);
            url =Urls.IMG_URL_PUBLICATION + photo.getImage();
            description = photo.getDescription();
        } else if (photos != null) {
            final Photo photo = photos.get(position);
            url = Urls.IMG_URL_PRESTATAIRE+photo.getUrl();
            description = photo.getDescription();
        }
        final String finalUrl = url;

        // holder.img.setMaxHeight(holder.img.getWidth());
        Picasso.with(mContext).load(Uri.parse(url)).resize(100,100).into(holder.img, new Callback() {
            @Override
            public void onSuccess() {
                System.out.println("photo load with success  ");
            }

            @Override
            public void onError() {
                System.out.println("photo load error  "+finalUrl);
            }
        });


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) mContext, (View) holder.img, "img");
                Intent intent = new Intent(mContext, PhotoActivity.class);
                intent.putExtra("img", finalUrl);
                mContext.startActivity(intent, options.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        if (publications != null) return publications.size();
        else if (photos != null) return photos.size();
        return 0;
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder {

        final EqualWidthAndHeightView img;

        public RecycleView_Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_photo);
        }
    }
}
