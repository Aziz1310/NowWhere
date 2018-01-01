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
    public static final String TYPE_LOUNGES="Lounges";
    public static final String TYPE_RESAUTANTS="Restaurants";
    public static final String TYPE_HAPPY_HOUR="Happy-Hour";
    public static final String TYPE_COFFE="Coffee";
    public static final String TYPE_CINEMA="Cinemas";
    public static final String TYPE_MIND="Mind";
    public static final String TYPE_ART="art";


    public EventManager(Context context) {
        this.context = context;
    }


    public void getPosts(int iduser,final VolleyCallback volleyCallback) {

        final PostRequest req = new PostRequest(Request.Method.GET, Urls.getPosts+"/"+iduser+".json",null, new Response.Listener<JSONArray>() {
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
    public void addToFavorit(int idUser,int idEvent, final VolleyCallback volleyCallback) {

        JSONObject reservation = new JSONObject();
        try {
            reservation.put("idEvent",idEvent);
            reservation.put("iduser", idUser);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.addFavoris, reservation, new Response.Listener<JSONObject>() {
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

    public void eventArround(Double lat,Double lang,String type,int distance,final VolleyCallback volleyCallback) {

        JSONObject reservation = new JSONObject();
        try {
            reservation.put("lat",lat);
            reservation.put("lang", lang);
            reservation.put("maxDistance", distance);
            reservation.put("type", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.event_arround,reservation, new Response.Listener<JSONObject>() {
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
    public void getPrestataires(String type,final VolleyCallback volleyCallback) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.prestataires_list,jsonObject, new Response.Listener<JSONObject>() {
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
    public void getPrestataireById(String id,final VolleyCallback volleyCallback) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idprestataire", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.prestataire_by_id,jsonObject, new Response.Listener<JSONObject>() {
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
    public void getPrestataireMenu(String id,final VolleyCallback volleyCallback) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idprestataire", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.menu,jsonObject, new Response.Listener<JSONObject>() {
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

    public void getListFilm(int isNow,final VolleyCallback volleyCallback) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("isNow", isNow);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.film_list,jsonObject, new Response.Listener<JSONObject>() {
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
