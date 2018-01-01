package com.maher.nowhere.ProfileFriendActivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.commentsActivity.CommentActivity;
import com.maher.nowhere.model.Mur;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by RaniaH on 31/10/2017.
 */

public class MurAdapter extends RecyclerView.Adapter<MurAdapter.RecycleView_Holder> {

    private final Context mContext;
    private final ArrayList<Publication> posts;

    public MurAdapter(Context context, ArrayList<Publication> posts) {
        this.mContext = context;
        this.posts = posts;
    }

    @Override
    public void onBindViewHolder(RecycleView_Holder holder, int position) {

        final Publication post = posts.get(position);

        holder.tv1.setText(String.format(Locale.FRANCE, "%d", post.getNbrJaime()));

        if (post.getComments() != null && post.getComments().size() > 0) {
            holder.tvCommentOwner.setText(post.getComments().get(0).getOwnerName());
            holder.tvComment.setText(post.getComments().get(0).getContenus());
            Picasso.with(mContext).load(Uri.parse(Urls.IMG_URL_USER + post.getComments().get(0).getOwnerImage())).into(holder.imgComment);
            if (post.getComments().size()>1){
                holder.voirComment_tv.setText(String.format("Voir les %d commentaires",post.getComments().size()) );

            }
        }


        holder.tvDay.setText(post.getDayOfWeek().toUpperCase());
        holder.tvMonth.setText(post.getMonthNumber().toUpperCase());
        holder.tvYear.setText(post.getYear().toUpperCase());
        holder.tvHeure.setText(String.format("%sh", post.getHour()));


        Picasso.with(mContext).load(Uri.parse(Urls.IMG_URL_PUBLICATION + post.getImage())).into(holder.img, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                System.out.println(" maher image loaded with success");
            }

            @Override
            public void onError() {
            }
        });
        holder.voirComment_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommentActivity.class);
                intent.putExtra("publication", post);
                mContext.startActivity(intent);


            }
        });

    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mur, parent, false);
        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;
    }

    @Override
    public int getItemCount() {
        return (null != posts ? posts.size() : 0);
    }

    class RecycleView_Holder extends RecyclerView.ViewHolder {

        final TextView tvDay, tvMonth, tvYear, tvHeure;
        final TextView tvCommentOwner, tvComment, tv1, voirComment_tv;
        final ImageView img;
        final CircleImageView imgComment;


        public RecycleView_Holder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.dayTV);
            tvMonth = itemView.findViewById(R.id.monthTV);
            tvYear = itemView.findViewById(R.id.yearTV);
            tvHeure = itemView.findViewById(R.id.heureTV);

            img = itemView.findViewById(R.id.img);
            imgComment = itemView.findViewById(R.id.ivProfile);
            tv1 = itemView.findViewById(R.id.likeTV);
            tvCommentOwner = itemView.findViewById(R.id.friendName);
            tvComment = itemView.findViewById(R.id.commentTV);
            voirComment_tv = itemView.findViewById(R.id.voirComment_tv);
        }
    }
}
