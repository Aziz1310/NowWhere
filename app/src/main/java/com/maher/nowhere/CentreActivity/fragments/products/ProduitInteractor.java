package com.maher.nowhere.CentreActivity.fragments.products;

import android.content.Context;

import com.maher.nowhere.RestaurantProfileActivity.fragments.menu.MenuInteractor;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Product;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.providers.AccueilManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class ProduitInteractor {



    public interface OnFinishedListener{
        void onSuccess(ArrayList<Product> products);
        void onError();
    }



    public void getListProduit(int idprestataire, final OnFinishedListener listener, final Context context) {
        AccueilManager accueilManager=new AccueilManager(context);
        accueilManager.getProduits(idprestataire, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {


                try {
                    ArrayList<Product> products=new JsonToObjectParser().parseProduits(((JSONObject)response).getJSONArray("Produits"));
                    listener.onSuccess(products);
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
