package com.maher.nowhere.mainActivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.categoriesDetail.CategoriesDetailActivity;
import com.maher.nowhere.commentsActivity.CommentActivity;
import com.maher.nowhere.model.Comment;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class AcceuilAdapter extends RecyclerView.Adapter<AcceuilAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Publication> posts;

    public AcceuilAdapter(Context context, ArrayList<Publication> posts){

        this.mContext = context;
        this.posts = posts;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accueil, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, final int position) {

        final Publication post= posts.get(position);
       // holder.tvHeure.setText(post.getHeure());

        holder.tv1.setText(String.format(Locale.FRANCE,"%d", post.getNbrJaime()));
        holder.tvComment.setText(post.getDescription());
        holder.tvCommentOwner.setText(post.getOwnerName());
        Picasso.with(mContext).load(Uri.parse(Urls.IMG_URL_USER +post.getOwnerImage())).into(holder.imgComment);


        Picasso.with(mContext).load(Uri.parse(Urls.IMG_URL_PUBLICATION +post.getImage())).into(holder.img, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                System.out.println(" maher image loaded with success");
            }
            @Override
            public void onError() {
            }
        });
        holder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, CommentActivity.class);
                intent.putExtra("publication",post);
                mContext.startActivity(intent);


            }
        });

    }



    @Override
    public int getItemCount() {
        return (null != posts ? posts.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder{


        final TextView tvDay,tvMonth,tvYear,tvHeure,tvCommentOwner,tvComment,tv1;
        final ImageView img;
        final CircleImageView imgComment;
        final LinearLayout btnComment;




        public RecycleView_Holder(View itemView) {
            super(itemView);

            tvDay=itemView.findViewById(R.id.tvDay);
            tvMonth=itemView.findViewById(R.id.tvMonth);
            tvYear=itemView.findViewById(R.id.tvYear);
            tvHeure=itemView.findViewById(R.id.tvheure);
            img=itemView.findViewById(R.id.img);
            imgComment=itemView.findViewById(R.id.ivProfile);
            tv1=itemView.findViewById(R.id.tvlikk);
            tvCommentOwner=itemView.findViewById(R.id.profineName);
            tvComment=itemView.findViewById(R.id.tvComment);
            btnComment=itemView.findViewById(R.id.btnComment);
        }
    }
}
