package com.maher.nowhere.utiles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.SearchDetailActivity.SearchDetailActivity;
import com.maher.nowhere.model.Post;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;
import com.squareup.picasso.Picasso;

/**
 * Created by maher on 07/10/2017.
 */

@Layout(R.layout.item_weeklik)
public class TinderCard {

    @View(R.id.ImgEv)
    private ImageView profileImageView;

    @View(R.id.tvDay)
    private TextView tvDay;

    @View(R.id.tvMonth)
    private TextView tvMonth;

    @View(R.id.tvYear)
    private TextView tvYear;

    @View(R.id.tvTitle1)
    private TextView tvName;

    @View(R.id.tvTitle)
    private TextView tvGroupeName;

    @View(R.id.tvName)
    private TextView tvPlace;

    @View(R.id.btnIGo)
    private AppCompatButton btnIGo;



    private Post post ;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;

    public TinderCard(Context context, Post profile, SwipePlaceHolderView swipeView) {
        mContext = context;
        post = profile;
        mSwipeView = swipeView;
    }

    @Resolve
    private void onResolved(){

    //profileImageView.setImageResource(post.getImage());
        Picasso.with(mContext).
                load(Uri.parse(post.getUrlImage()))
                .into(profileImageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        System.out.println(" maher image loaded with success");
                    }

                    @Override
                    public void onError() {
                    }
                });
        tvGroupeName.setText(post.getTitle()+":");
        tvName.setText(post.getName());
        tvPlace.setText("At "+post.getOwner().getNom());
        tvDay.setText(post.getDay().toUpperCase());
        tvMonth.setText(post.getMonth().toUpperCase());
        tvYear.setText(post.getYear());
        btnIGo.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent=new Intent(mContext, SearchDetailActivity.class);
                intent.putExtra("post",post);
                mContext.startActivity(intent);
                ((Activity)mContext).overridePendingTransition(R.anim.fragment_fade_in, R.anim.fragment_fade_out);
            }
        });
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }
}
