package com.maher.nowhere.ProfileActivity.fragments.favoris;

import android.content.Context;

import com.maher.nowhere.mainActivity.fragments.weeklik.WeeklikInteractor;
import com.maher.nowhere.mainActivity.fragments.weeklik.WeeklikView;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.User;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class FavorisPresenter implements FavorisInteractor.OnFavorisFinishedListener {

    private FavorisView favorisView;
    private FavorisInteractor favorisInteractor;
    private Context mcContext;

    public FavorisPresenter(FavorisView favorisView, Context mcContext) {
        this.favorisView = favorisView;
        this.mcContext = mcContext;
        favorisInteractor = new FavorisInteractor();
    }


    void loadAllPosts(int id) {
        favorisView.showProgress();
        favorisInteractor.loadAllPosts(id,this,mcContext);
    }
    void deletPost(int idUser,int idPost) {
        favorisView.showProgress();
        favorisInteractor.deletFavoris(idUser,idPost,this,mcContext);
    }


    @Override
    public void onSuccess(ArrayList<Post> posts) {
        favorisView.hideProgress();
        if (!posts.isEmpty())
            favorisView.loadAllPosts(posts);
        else favorisView.loadNoPost();
    }

    @Override
    public void onError(Object error) {
        favorisView.hideProgress();
        favorisView.loadNoPost();
    }

    @Override
    public void onSuccessDelet() {
        loadAllPosts(User.getCurrentUser(mcContext).getId());
    }

    @Override
    public void onErrorDelet(Object error) {
        favorisView.hideProgress();
    }
}
