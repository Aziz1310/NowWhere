package com.maher.nowhere.ContactsActivity.interactors;

import android.content.Context;

import com.maher.nowhere.ContactsActivity.views.SuggestionView;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccountManager;
import com.maher.nowhere.providers.ContactsManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class SuggestionInteractor {



    public interface OnSuggestionFinishedListener{
        void onSuccess(ArrayList<User> users);
        void onError();
        void onInvitationSucces();
        void onInvitationError();
    }



    public void getListSuggestions(int iduser, final OnSuggestionFinishedListener listener, final Context context) {
        ContactsManager contactsManager=new ContactsManager(context);
        contactsManager.suggestion(iduser, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {


                try {
                    ArrayList<User> users=new JsonToObjectParser().parseUsers(((JSONObject)response).getJSONArray("list_suggestion"));
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

    public void sendInvitation(int currentUser,int idFrind, final OnSuggestionFinishedListener listener, final Context context){
        ContactsManager contactsManager=new ContactsManager(context);
        contactsManager.sendInvitation(currentUser,idFrind, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {

                   listener.onInvitationSucces();

            }

            @Override
            public void onError(Object error) {
                listener.onInvitationError();
            }
        });
    }
}
