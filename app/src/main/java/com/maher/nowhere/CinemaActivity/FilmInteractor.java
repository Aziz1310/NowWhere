package com.maher.nowhere.CinemaActivity;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Film;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.providers.AccueilManager;
import com.maher.nowhere.providers.EventManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class FilmInteractor {



    public interface OnAccueilFinishedListener{
        void onSuccess(ArrayList<Film> films);
        void onError();
    }



    public void getListFilm(int isNow, final OnAccueilFinishedListener listener, final Context context) {
        EventManager accueilManager=new EventManager(context);
        accueilManager.getListFilm(isNow, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {


                try {
                    ArrayList<Film> films=new JsonToObjectParser().parseFilms(((JSONObject)response).getJSONArray("film"));
                    listener.onSuccess(films);
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
