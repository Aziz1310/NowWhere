package com.maher.nowhere.ProfileFriendActivity.fragments.photos;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Photo;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.providers.AccountManager;
import com.maher.nowhere.providers.AccueilManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class PhotoInteractor {



    public interface OnPhotoFinishedListener{
        void onSuccess(ArrayList<Publication> posts);
        void onError();
        void onSuccesPhoto(ArrayList<Photo>photos);
        void onErrorPhotos();
    }



    public void getListPosts(int iduser, final OnPhotoFinishedListener listener, final Context context) {
        AccountManager accountManager=new AccountManager(context);
        accountManager.myPublications(iduser, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {


                try {
                    ArrayList<Publication> publications=new JsonToObjectParser().parsePublications(((JSONObject)response).getJSONArray("list_publications"));
                    listener.onSuccess(publications);
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onError();
                }
            }

            @Override
            public void onError(Object error) {
                listener.onError();
            }
        });
    }

    public void getListPhotos(int iduser, final OnPhotoFinishedListener listener, final Context context) {
        AccueilManager accueilManager=new AccueilManager(context);
        accueilManager.getPhotos(iduser, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {

                    ArrayList<Photo> photos=new JsonToObjectParser().parsePhotos(((JSONObject)response));
                    listener.onSuccesPhoto(photos);
            }

            @Override
            public void onError(Object error) {
                listener.onErrorPhotos();
            }
        });
    }

}
