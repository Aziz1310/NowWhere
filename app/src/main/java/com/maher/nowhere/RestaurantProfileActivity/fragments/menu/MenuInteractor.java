package com.maher.nowhere.RestaurantProfileActivity.fragments.menu;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Feedback;
import com.maher.nowhere.model.Menu;
import com.maher.nowhere.providers.RestaurantManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class MenuInteractor {

    public interface OnFinishListener{
        void onSuccess(ArrayList<Menu> menus);
        void onError();

    }

    public void getListMenu(int idprestataire, final OnFinishListener listener, final Context context) {
        RestaurantManager restaurantManager=new RestaurantManager(context);
        restaurantManager.getPrestataireMenu(idprestataire, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                try {
                    ArrayList<Menu> menus=new JsonToObjectParser().parseMenus(((JSONObject)response).getJSONArray("Menu"));
                    listener.onSuccess(menus);

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
