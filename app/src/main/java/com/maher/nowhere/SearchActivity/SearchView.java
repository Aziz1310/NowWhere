package com.maher.nowhere.SearchActivity;

import com.maher.nowhere.model.Owner;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface SearchView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllPosts(ArrayList<Owner> owners);
    void loadNoPosts();

}
