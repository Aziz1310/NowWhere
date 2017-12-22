package com.maher.nowhere.ProfileActivity.fragments.favoris;

import com.maher.nowhere.model.Post;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public interface FavorisView {
    void showProgress();
    void hideProgress();
    void loadAllPosts(ArrayList<Post> posts);
    void loadNoPost();

}
