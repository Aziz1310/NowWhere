package com.maher.nowhere.ProfileActivity.fragments.page;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.providers.AccountManager;
import com.maher.nowhere.providers.AccueilManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class PageInteractor {



    public interface OnPageFinishedListener{
        void onSuccess(ArrayList<Publication> posts);
        void onError();
        void onSuccesAddPublication();
        void onErrorAddPublication();
    }



    public void getListPosts(int iduser, final OnPageFinishedListener listener, final Context context) {
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
    public void addPublication(int iduser,String description,byte[] image, final OnPageFinishedListener listener, final Context context) {
        AccountManager accountManager=new AccountManager(context);
        accountManager.addPublication(iduser,description,image, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {

                    listener.onSuccesAddPublication();
            }

            @Override
            public void onError(Object error) {
                listener.onErrorAddPublication();
            }
        });
    }
}
