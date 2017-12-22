package com.maher.nowhere.MapActivity;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.providers.EventManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class MapInteractor {

    public interface OnMapFinishedListener{
       void onSuccess(ArrayList<Owner> owners);
        void onError();

    }

    public void loadAllPosts(Double lat,Double lang,String type,int distance,final OnMapFinishedListener listener, final Context context) {
        EventManager eventManager=new EventManager(context);
        eventManager.eventArround( lat, lang, type, distance ,new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {

                try {
                    listener.onSuccess(new JsonToObjectParser().parsePrestaire(( ((JSONObject)response).getJSONArray("list_prestataire"))));
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
