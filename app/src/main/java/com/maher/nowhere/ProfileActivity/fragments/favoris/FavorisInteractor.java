package com.maher.nowhere.ProfileActivity.fragments.favoris;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.providers.AccountManager;
import com.maher.nowhere.providers.EventManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class FavorisInteractor {

    public interface OnFavorisFinishedListener{
       void onSuccess(ArrayList<Post> posts);
        void onError(Object error);
        void onSuccessDelet();
        void onErrorDelet(Object error);

    }

    public void loadAllPosts(int id,final OnFavorisFinishedListener listener, final Context context) {
        AccountManager accountManager=new AccountManager(context);
        accountManager.getFavoris(id,new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                try {
                    JSONArray jsonArray =((JSONObject)response).getJSONArray("list_Favories");
                    listener.onSuccess(new JsonToObjectParser().parsePosts(jsonArray));
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onError(e);
                }

            }

            @Override
            public void onError(Object error) {
                listener.onError(error);
            }
        });
    }
    public void deletFavoris(final int idUser, final int idEvent, final OnFavorisFinishedListener listener, final Context context) {
        AccountManager accountManager=new AccountManager(context);
        accountManager.deletFavoris(idUser,idEvent,new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {

                    listener.onSuccessDelet();

            }

            @Override
            public void onError(Object error) {
                listener.onErrorDelet(error);
            }
        });
    }

}
