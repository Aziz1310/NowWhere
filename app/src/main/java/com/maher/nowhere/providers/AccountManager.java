package com.maher.nowhere.providers;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.VolleyMultipartRequest;
import com.maher.nowhere.utiles.ConnectionSingleton;
import com.maher.nowhere.utiles.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Maher on 10/30/17.
 */

public class AccountManager {

    private final Context context;

    public AccountManager(Context context) {
        this.context = context;
    }


    public void signin(String userName, String password, final VolleyCallback volleyCallback) {

        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("password", password);
            jsonUser.put("email", userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.userLogin, jsonUser, new Response.Listener<JSONObject>() {
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


    public void getReservation(int idUser, final VolleyCallback volleyCallback) {


        final JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, Urls.listReservation + "/" + idUser + ".json", null, new Response.Listener<JSONArray>() {
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

    public void getFavoris(final int id, final VolleyCallback volleyCallback) {
        JSONObject jsonUser = new JSONObject();
        try {

            jsonUser.put("iduser", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.listFavoris, jsonUser, new Response.Listener<JSONObject>() {
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

    public void deletFavoris(final int idUser, final int idEvent, final VolleyCallback volleyCallback) {
        JSONObject jsonUser = new JSONObject();
        try {

            jsonUser.put("iduser", idUser);
            jsonUser.put("idEvent", idEvent);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.deletFavoris, jsonUser, new Response.Listener<JSONObject>() {
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

    public void register(final String email, final String name, final String password, final byte[] image, final byte[] imageCover, final VolleyCallback volleyCallback) {

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, Urls.userRegister, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                // parse success output
                volleyCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                volleyCallback.onError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();


                params.put("username", name);
                params.put("email", email);
                params.put("password", password);

                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws IOException {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                params.put("file", new DataPart(email + ".jpg", image, "application/jpg"));
                params.put("couverture", new DataPart(email + ".jpg", imageCover, "application/jpg"));

                return params;
            }
        };

        ConnectionSingleton.getInstance(context).addToRequestque(multipartRequest);

    }

    public void settings(final String email, final String name, final String password, final byte[] image, final byte[] imageCover, final VolleyCallback volleyCallback) {

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, Urls.settings, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                // parse success output
                volleyCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                volleyCallback.onError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();


                params.put("username", name);
                params.put("email", email);
                params.put("password", password);

                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws IOException {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                params.put("file", new DataPart(email + ".jpg", image, "application/jpg"));
                params.put("couverture", new DataPart(email + ".jpg", imageCover, "application/jpg"));

                return params;
            }
        };

        ConnectionSingleton.getInstance(context).addToRequestque(multipartRequest);

    }


    public void myPublications(int idUser, final VolleyCallback volleyCallback) {

        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("iduser", idUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Urls.myPublications, jsonUser, new Response.Listener<JSONObject>() {
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

    public void addPublication(final int idUser, final String description, final byte[] image, final VolleyCallback volleyCallback) {

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, Urls.addPublication, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                // parse success output
                volleyCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                volleyCallback.onError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();


                params.put("iduser", String.format(Locale.FRANCE, "%d", idUser));
                params.put("description", description);


                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws IOException {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                params.put("file", new DataPart(idUser + timeStamp + ".jpg", image, "application/jpg"));
                return params;
            }
        };

        ConnectionSingleton.getInstance(context).addToRequestque(multipartRequest);

    }
}
