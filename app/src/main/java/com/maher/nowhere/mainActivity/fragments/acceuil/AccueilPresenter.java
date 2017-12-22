package com.maher.nowhere.mainActivity.fragments.acceuil;

import android.content.Context;

import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class AccueilPresenter implements AccueilInteractor.OnAccueilFinishedListener {

    private AccueilView accueilView;
    private AccueilInteractor accueilInteractor;
    private Context mcContext;

    public AccueilPresenter(AccueilView accueilView, Context mcContext) {
        this.accueilView = accueilView;
        this.mcContext = mcContext;
        accueilInteractor = new AccueilInteractor();
    }


    public void getListPublication(int idUser) {
            accueilView.showProgress();
            accueilInteractor.getListPosts(idUser, this,mcContext);
        }



    @Override
    public void onSuccess(ArrayList<Publication> posts) {
        accueilView.hideProgress();
        if(!posts.isEmpty())
            accueilView.loadAllPosts(posts);
        else accueilView.loadNoPosts(posts);

    }

    @Override
    public void onError() {
        accueilView.hideProgress();
        accueilView.networkError();
    }


}
