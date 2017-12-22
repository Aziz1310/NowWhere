package com.maher.nowhere.SearchActivity;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.providers.EventManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class SearchInteractor {

    public interface OnSearchFinishedListener{
       void onSuccess(ArrayList<Owner> owners);
        void onError();

    }

    private String getType(String type){
        switch (type){
            case "Réstaurant":
                return EventManager.TYPE_RESAUTANTS;
            case "magic places":
                return EventManager.TYPE_HAPPY_HOUR;
            case "Discos":
                return EventManager.TYPE_LOUNGES;
            case "Caffées":
                return EventManager.TYPE_COFFE;
            case "Cinémas":
                return EventManager.TYPE_CINEMA;
            case "Centres":
                return EventManager.TYPE_MIND;
            case "Art":
                return EventManager.TYPE_ART;
        }
        return "null";
    }

    public void loadAllPosts(String type,final OnSearchFinishedListener listener, final Context context) {

        EventManager eventManager=new EventManager(context);
        eventManager.getPrestataires(getType(type),new VolleyCallback() {
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
