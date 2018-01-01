package com.maher.nowhere.ProfileFriendActivity.fragments.photos;

import com.maher.nowhere.model.Photo;
import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface PhotoView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllPosts(ArrayList<Publication> posts);
    void loadNoPosts(ArrayList<Publication> posts);
    void loadAllPhotos(ArrayList<Photo> photos);
    void loadNoPhotos(ArrayList<Photo> photos);

}
