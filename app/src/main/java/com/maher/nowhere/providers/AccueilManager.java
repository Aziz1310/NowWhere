package com.maher.nowhere.providers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.PostRequest;
import com.maher.nowhere.utiles.ConnectionSingleton;
import com.maher.nowhere.utiles.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maher on 10/30/17.
 */

public class AccueilManager {

    private final Context context;

    public AccueilManager(Context context) {
        this.context = context;
    }



    public void getPublications(int idUser,final VolleyCallback volleyCallback) {

        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("iduser", idUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("iduser friend "+idUser);
        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.listPublication,jsonUser, new Response.Listener<JSONObject>() {
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
    public void getPhotos(int idUser,final VolleyCallback volleyCallback) {

        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("idprestataire", idUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.photo_list,jsonUser, new Response.Listener<JSONObject>() {
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

    public void addComment(int idUser, int idPublication,String contenu, final VolleyCallback volleyCallback) {

        JSONObject jsonUser = new JSONObject();
        try {

            jsonUser.put("iduser", idUser);
            jsonUser.put("idpublication", idPublication);
            jsonUser.put("contenu", contenu);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.addComment, jsonUser, new Response.Listener<JSONObject>() {
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

    public void getProduits(int idPrestataire,final VolleyCallback volleyCallback) {

        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("idPrestataire", idPrestataire);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.produit_list,jsonUser, new Response.Listener<JSONObject>() {
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
