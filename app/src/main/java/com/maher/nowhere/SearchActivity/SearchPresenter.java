package com.maher.nowhere.SearchActivity;

import android.content.Context;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Post;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class SearchPresenter implements SearchInteractor.OnSearchFinishedListener {

    private SearchView searchView;
    private SearchInteractor searchInteractor;
    private Context mcContext;

    public SearchPresenter( SearchView searchView, Context mcContext) {
        this.searchView = searchView;
        this.mcContext = mcContext;
        searchInteractor = new SearchInteractor();
    }


    public void loadAllPosts(String type) {
        searchView.showProgress();
        searchInteractor.loadAllPosts(type,this,mcContext);
    }
    public void loadAllEvents(int idUser) {
        searchView.showProgress();
        searchInteractor.loadAllEvents(idUser,this,mcContext);
    }


    @Override
    public void onSuccess(ArrayList<Owner> posts) {
        searchView.hideProgress();
        if (!posts.isEmpty())
            searchView.loadAllPosts(posts);
        else searchView.loadNoPosts();
    }

    @Override
    public void onError() {
        searchView.hideProgress();
        searchView.loadNoPosts();
    }

    @Override
    public void onSuccessEvent(ArrayList<Post> posts) {
        searchView.hideProgress();
        if (!posts.isEmpty())
            searchView.loadAllEvents(posts);
        else searchView.loadNoEvents(posts);
    }
}
