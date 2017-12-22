package com.maher.nowhere.mainActivity.fragments.acceuil;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccueilManager;
import com.maher.nowhere.providers.ContactsManager;
import com.maher.nowhere.utiles.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class AccueilInteractor {



    public interface OnAccueilFinishedListener{
        void onSuccess(ArrayList<Publication> posts);
        void onError();
    }



    public void getListPosts(int iduser, final OnAccueilFinishedListener listener, final Context context) {
        AccueilManager accueilManager=new AccueilManager(context);
        accueilManager.getPublications(iduser, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {


                try {
                    ArrayList<Publication> publications=new JsonToObjectParser().parsePublications(((JSONObject)response).getJSONArray("list_publications"));
                    listener.onSuccess(publications);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Object error) {
                listener.onError();
            }
        });
    }
}
