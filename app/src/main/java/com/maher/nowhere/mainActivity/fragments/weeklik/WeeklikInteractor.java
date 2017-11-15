package com.maher.nowhere.mainActivity.fragments.weeklik;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;

import com.maher.nowhere.R;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccountManager;
import com.maher.nowhere.providers.EventManager;
import com.maher.nowhere.utiles.InputValidator;
import com.maher.nowhere.utiles.TinderCard;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class WeeklikInteractor {

    public interface OnSignUpFinishedListener{
       void onSuccess(ArrayList<Post> posts);
        void onError();

    }

    public void loadAllPosts(final OnSignUpFinishedListener listener, final Context context) {
        EventManager eventManager=new EventManager(context);
        eventManager.getPosts(new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                listener.onSuccess(new JsonToObjectParser().parsePosts((JSONArray) response));
            }

            @Override
            public void onError(Object error) {
                listener.onError();
            }
        });
    }

}
