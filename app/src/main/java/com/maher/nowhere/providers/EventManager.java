package com.maher.nowhere.providers;

import android.app.Activity;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.PostRequest;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.ConnectionSingleton;
import com.maher.nowhere.utiles.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maher on 10/30/17.
 */

public class EventManager {

    private final Context context;

    public EventManager(Context context) {
        this.context = context;
    }


    public void getPosts(final VolleyCallback volleyCallback) {

        final PostRequest req = new PostRequest(Request.Method.GET, Urls.getPosts,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                    volleyCallback.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallback.onError(error);

            }
        });

        ConnectionSingleton.getInstance(context).addToRequestque(req);
    }

    public void reservation(int idEvent, String nbrPersonne,String heure, final VolleyCallback volleyCallback) {

        JSONObject reservation = new JSONObject();
        try {
            reservation.put("idEvent",idEvent);
            reservation.put("iduser", User.getCurrentUser(context).getId());
            reservation.put("nbrPersonne", nbrPersonne);
            reservation.put("heureArrivee", heure);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.reservation, reservation, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                volleyCallback.onSuccess(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallback.onError(error);

            }
        });

        ConnectionSingleton.getInstance(context).addToRequestque(req);
    }



}
