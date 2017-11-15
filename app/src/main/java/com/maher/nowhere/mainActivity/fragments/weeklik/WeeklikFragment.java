package com.maher.nowhere.mainActivity.fragments.weeklik;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.maher.nowhere.R;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.providers.EventManager;
import com.maher.nowhere.utiles.TinderCard;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import org.json.JSONArray;

import java.util.ArrayList;

public class WeeklikFragment extends Fragment implements WeeklikView {

    private String mParam1;
    private String mParam2;
    private View view;
    private SwipePlaceHolderView mSwipeView;
    private LottieAnimationView lottieAnimationView;

    public WeeklikFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_weeklik, container, false);
        setRetainInstance(true);
        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.loadingAnimation);

        mSwipeView = (SwipePlaceHolderView) view.findViewById(R.id.swipeView);

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));

        WeeklikPresenter weeklikPresenter=new WeeklikPresenter(this,getActivity());
        weeklikPresenter.loadAllPosts();
        return view;

    }

    @Override
    public void showProgress() {
        lottieAnimationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        lottieAnimationView.setVisibility(View.GONE);
    }

    @Override
    public void loadAllPosts(ArrayList<Post> posts) {
        for (Post post : posts) {
            mSwipeView.addView(new TinderCard(getActivity(), post, mSwipeView));
        }
        view.findViewById(R.id.btnDislike).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);

            }
        });

        view.findViewById(R.id.btnLike).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);

            }
        });
    }

    @Override
    public void loadNoPost() {

    }
}
