package com.maher.nowhere.ProfileActivity.fragments.reservation;

import android.content.Context;

import com.android.volley.VolleyError;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.model.Reservation;
import com.maher.nowhere.providers.AccountManager;
import com.maher.nowhere.providers.AccueilManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class ReservationInteractor {



    public interface OnReservationFinishedListener{
        void onSuccess(ArrayList<Reservation> reservations);
        void onError(VolleyError error);
    }



    public void getListReservation(int iduser, final OnReservationFinishedListener listener, final Context context) {
        AccountManager accountManager=new AccountManager(context);
        accountManager.getReservation(iduser, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {



                    ArrayList<Reservation> reservations=new JsonToObjectParser().parseReservations(((JSONArray)response));
                    listener.onSuccess(reservations);

            }

            @Override
            public void onError(Object error) {
                listener.onError((VolleyError)error);
            }
        });
    }
}
