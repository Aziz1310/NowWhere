package com.maher.nowhere.mainActivity.fragments.acceuil;

import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.model.User;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface AccueilView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllPosts(ArrayList<Publication> posts);
    void loadNoPosts(ArrayList<Publication> posts);

}
