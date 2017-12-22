package com.maher.nowhere.RestaurantProfileActivity.fragments.feedback;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Feedback;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.providers.AccueilManager;
import com.maher.nowhere.providers.RestaurantManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class FeedbackInteractor {



    public interface OnFeedbackFinishedListener{
        void onSuccess(ArrayList<Feedback> feedbacks);
        void onError();
        void onAddFeedBackSuccess(String message);
        void onAddFeedBackError(String message);
    }

    public void getListFeedback(int iduser,int idprestataire, final OnFeedbackFinishedListener listener, final Context context) {
        RestaurantManager restaurantManager=new RestaurantManager(context);
        restaurantManager.getFeedbacks(iduser,idprestataire, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                    ArrayList<Feedback> feedbacks=new JsonToObjectParser().parseFeedbacks(((JSONObject)response));
                    listener.onSuccess(feedbacks);
            }

            @Override
            public void onError(Object error) {
                listener.onError();
            }
        });
    }
    public void addFeedback(int iduser,int idprestataire,String contenu,String note, final OnFeedbackFinishedListener listener, final Context context) {
        RestaurantManager restaurantManager=new RestaurantManager(context);
        restaurantManager.addFeedback(iduser,idprestataire,contenu,note, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {

                JSONObject jsonObject=(JSONObject)response;
                try {
                    String etat=jsonObject.getString("status");
                    String message =jsonObject.getString("message");

                    if (etat.equals("success"))
                    listener.onAddFeedBackSuccess(message);
                    else if(etat.equals("error"))
                        listener.onAddFeedBackError(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Object error) {
                listener.onAddFeedBackError("Votre avis ne peut pas être ajoutée");
            }
        });
    }
}
