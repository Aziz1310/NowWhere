package com.maher.nowhere.mainActivity.fragments.weeklik;

import android.content.Context;
import android.graphics.Bitmap;

import com.maher.nowhere.model.Post;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class WeeklikPresenter implements WeeklikInteractor.OnSignUpFinishedListener {

    private WeeklikView weeklikView;
    private WeeklikInteractor weeklikInteractor;
    private Context mcContext;

    public WeeklikPresenter(WeeklikView weeklikView, Context mcContext) {
        this.weeklikView = weeklikView;
        this.mcContext = mcContext;
        weeklikInteractor = new WeeklikInteractor();
    }
    public WeeklikPresenter(Context mcContext) {
        this.mcContext = mcContext;
        weeklikInteractor = new WeeklikInteractor();
    }


    void loadAllPosts(int idUser) {
            weeklikView.showProgress();
            weeklikInteractor.loadAllPosts(idUser,this,mcContext);
    }
    public void addToFavorit(int idUser,int idPost) {
        weeklikInteractor.addTofavorit(idUser,idPost,this,mcContext);
    }



    @Override
    public void onSuccess(ArrayList<Post> posts) {
        weeklikView.hideProgress();
        if (!posts.isEmpty())
            weeklikView.loadAllPosts(posts);
        else weeklikView.loadNoPost();
    }

    @Override
    public void onError() {
        weeklikView.hideProgress();
        weeklikView.loadNoPost();
    }

    @Override
    public void onSuccessDelet() {

    }

    @Override
    public void onErrorDelet() {

    }
}
