package com.maher.nowhere.mainActivity.fragments.weeklik;

import com.maher.nowhere.model.Post;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public interface WeeklikView {
    void showProgress();
    void hideProgress();
    void loadAllPosts(ArrayList<Post> posts);
    void loadNoPost();

}
