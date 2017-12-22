package com.maher.nowhere.providers;

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

public class RestaurantManager {

    private final Context context;


    public RestaurantManager(Context context) {
        this.context = context;
    }


    public void getFeedbacks(int idUser, int idprestataire, final VolleyCallback volleyCallback) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idprestataire", idprestataire);
            jsonObject.put("iduser", idUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.feedback_list, jsonObject, new Response.Listener<JSONObject>() {
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

    public void addFeedback(int idUser, int idprestataire, String contenu, String note, final VolleyCallback volleyCallback) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idprestataire", idprestataire);
            jsonObject.put("iduser", idUser);
            jsonObject.put("contenu", contenu);
            jsonObject.put("note", note);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.feedback_add, jsonObject, new Response.Listener<JSONObject>() {
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

    public void getPrestataireMenu(String id, final VolleyCallback volleyCallback) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idprestataire", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.menu, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                volleyCallback.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                volleyCallback.onError(error);

            }
        });

        ConnectionSingleton.getInstance(context).addToRequestque(req);
    }
}
