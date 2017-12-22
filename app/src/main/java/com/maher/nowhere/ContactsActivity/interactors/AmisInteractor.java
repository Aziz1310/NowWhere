package com.maher.nowhere.ContactsActivity.interactors;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.ContactsManager;
import com.maher.nowhere.utiles.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class AmisInteractor {



    public interface OnSuggestionFinishedListener{
        void onSuccess(ArrayList<User> users);
        void onError();
        void onDeleteSuccess();
        void onDeleteError();
    }



    public void getListFrinds(int iduser, final OnSuggestionFinishedListener listener, final Context context) {
        ContactsManager contactsManager=new ContactsManager(context);
        contactsManager.listAmis(iduser, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {


                try {
                    ArrayList<User> users=new JsonToObjectParser().parseUsers(((JSONObject)response).getJSONArray("list_amis"));
                    listener.onSuccess(users);
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
    public void deleteFrind(int currentUser,int iduser, final OnSuggestionFinishedListener listener, final Context context) {
        ContactsManager contactsManager=new ContactsManager(context);
        contactsManager.acceptDeclineInvitation(currentUser,iduser, Urls.deleteFrind, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                    listener.onDeleteError();

            }

            @Override
            public void onError(Object error) {
                listener.onDeleteError();
            }
        });
    }


}
