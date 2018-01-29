package com.maher.nowhere.SalleDeSportActivity.fragments.tryNow;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Pack;
import com.maher.nowhere.model.Product;
import com.maher.nowhere.providers.AccueilManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class TryNowInteractor {



    public interface OnFinishedListener{
        void onSuccess(ArrayList<Pack> packs);
        void onError();
    }



    public void getListPacks(int idprestataire, final OnFinishedListener listener, final Context context) {
        AccueilManager accueilManager=new AccueilManager(context);
        accueilManager.getPacks(idprestataire, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {


                try {
                    ArrayList<Pack> packs=new JsonToObjectParser().parsePacks(((JSONObject)response).getJSONArray("list_pack"));
                    listener.onSuccess(packs);
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
