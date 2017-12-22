package com.maher.nowhere.ProfileActivity.fragments.page;

import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface PageView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllPosts(ArrayList<Publication> posts);
    void loadNoPosts(ArrayList<Publication> posts);
    void onSuccesAddPublication();
    void onErrorAddPublication();

}
