package com.maher.nowhere.ProfileFriendActivity.fragments.photos;

import android.content.Context;

import com.maher.nowhere.ProfileActivity.fragments.page.PageInteractor;
import com.maher.nowhere.ProfileActivity.fragments.page.PageView;
import com.maher.nowhere.model.Photo;
import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class PhotoPresenter implements PhotoInteractor.OnPhotoFinishedListener {

    private PhotoView photoView;
    private PhotoInteractor photoInteractor;
    private Context mcContext;

    public PhotoPresenter(PhotoView photoView, Context mcContext) {
        this.photoView = photoView;
        this.mcContext = mcContext;
        photoInteractor = new PhotoInteractor();
    }


    public void getListPublication(int idUser) {
        photoView.showProgress();
        photoInteractor.getListPosts(idUser, this,mcContext);
        }
    public void getListPhotos(int idUser) {
        photoView.showProgress();
        photoInteractor.getListPhotos(idUser, this,mcContext);
    }



    @Override
    public void onSuccess(ArrayList<Publication> posts) {
        photoView.hideProgress();
        if(!posts.isEmpty())
            photoView.loadAllPosts(posts);
        else photoView.loadNoPosts(posts);

    }

    @Override
    public void onError() {
        photoView.hideProgress();
        photoView.networkError();
    }

    @Override
    public void onSuccesPhoto(ArrayList<Photo> photos) {
        photoView.hideProgress();
        if(!photos.isEmpty())
            photoView.loadAllPhotos(photos);
        else photoView.loadNoPhotos(photos);

    }

    @Override
    public void onErrorPhotos() {
        photoView.hideProgress();
        photoView.networkError();
    }


}
