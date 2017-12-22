package com.maher.nowhere.MapActivity;

import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface MapView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllPosts(ArrayList<Owner> owners);
    void loadNoPosts();

}
