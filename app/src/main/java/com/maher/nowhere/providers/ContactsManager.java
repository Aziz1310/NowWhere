package com.maher.nowhere.providers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.utiles.ConnectionSingleton;
import com.maher.nowhere.utiles.Urls;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maher on 10/30/17.
 */

public class ContactsManager {

    private final Context context;

    public ContactsManager(Context context) {
        this.context = context;
    }


    public void suggestion(int idUser, final VolleyCallback volleyCallback) {

        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("iduser", idUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.suggestion, jsonUser, new Response.Listener<JSONObject>() {
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

    public void listAmis(int idUser, final VolleyCallback volleyCallback) {

        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("iduser", idUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.amis, jsonUser, new Response.Listener<JSONObject>() {
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

    public void sendInvitation(int currentUser,int idAmis, final VolleyCallback volleyCallback) {

        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("iduser_source", currentUser);
            jsonUser.put("iduser_target", idAmis);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.sendInvitation, jsonUser, new Response.Listener<JSONObject>() {
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


    public void listInvitation(int idUser, final VolleyCallback volleyCallback) {

        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("iduser", idUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.listInvitation, jsonUser, new Response.Listener<JSONObject>() {
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
    public void acceptDeclineInvitation(int currentUser,int idamis,String url, final VolleyCallback volleyCallback) {

        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("iduser_source", currentUser);
            jsonUser.put("iduser_target", idamis);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, jsonUser, new Response.Listener<JSONObject>() {
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
