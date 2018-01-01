package com.maher.nowhere.MapActivity;

import android.content.Context;

import com.maher.nowhere.R;
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
       void onSuccess(ArrayList<Owner> owners,int mapDrawable);
        void onError();

    }
    private String getType(String type) {
        switch (type) {
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
    private int getMapIcon(String type) {
        switch (type) {
            case "Réstaurant":
                return R.drawable.icon_map_restorant;
            case "magic places":
                return R.drawable.icon_map_happy_hour;
            case "Discos":
                return R.drawable.icon_map_parties;
            case "Caffées":
                return R.drawable.icon_map_coffe;
            case "Cinémas":
                return R.drawable.icon_map_cinema;
            case "Centres":
                return R.drawable.icon_map_mind;
            case "Art":
                return R.drawable.icon_map_cinema;
        }
        return R.drawable.icon_map_coffe;
    }

    public void loadAllPosts(Double lat, Double lang, final String type, int distance, final OnMapFinishedListener listener, final Context context) {
        EventManager eventManager=new EventManager(context);
        eventManager.eventArround( lat, lang, getType(type), distance ,new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {

                try {
                    listener.onSuccess(new JsonToObjectParser().parsePrestaire(( ((JSONObject)response).getJSONArray("list_prestataire"))),
                            getMapIcon(type));
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
