package com.maher.nowhere.ProfileActivity.fragments.page;

import android.content.Context;

import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class PagePresenter implements PageInteractor.OnPageFinishedListener {

    private PageView pageView;
    private PageInteractor pageInteractor;
    private Context mcContext;

    public PagePresenter(PageView pageView, Context mcContext) {
        this.pageView = pageView;
        this.mcContext = mcContext;
        pageInteractor = new PageInteractor();
    }


    public void getListPublication(int idUser) {
        pageView.showProgress();
        pageInteractor.getListPosts(idUser, this, mcContext);
    }

    public void addPost(int idUser, String description, byte[] image) {
        pageView.showProgress();
        pageInteractor.addPublication(idUser, description, image, this, mcContext);
    }


    @Override
    public void onSuccess(ArrayList<Publication> posts) {
        pageView.hideProgress();
        if (!posts.isEmpty())
            pageView.loadAllPosts(posts);
        else pageView.loadNoPosts(posts);

    }

    @Override
    public void onError() {
        pageView.hideProgress();
        pageView.networkError();
    }

    @Override
    public void onSuccesAddPublication() {
        pageView.hideProgress();
        pageView.onSuccesAddPublication();
    }

    @Override
    public void onErrorAddPublication() {
        pageView.hideProgress();
        pageView.onErrorAddPublication();
    }


}
