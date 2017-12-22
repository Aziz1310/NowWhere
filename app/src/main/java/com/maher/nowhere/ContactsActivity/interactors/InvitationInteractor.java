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

public class InvitationInteractor {



    public interface OnInvitationFinishedListener{
        void onSuccess(ArrayList<User> users);
        void onError();
        void onAcceptInvitationSucces();
        void onAcceptInvitationError();
        void onDeclineInvitationSucces();
        void onDeclineInvitationError();

    }


    public void getListInvitation(int iduser, final OnInvitationFinishedListener listener, final Context context) {
        ContactsManager contactsManager=new ContactsManager(context);
        contactsManager.listInvitation(iduser, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {


                try {
                    ArrayList<User> users=new JsonToObjectParser().parseUsers(((JSONObject)response).getJSONArray("list_invitation"));
                    listener.onSuccess(users);
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onError();
                }
            }

            @Override
            public void onError(Object error) {
                listener.onError();
            }
        });
    }

    public void acceptInvitation(int currentUser,int idFrind, final OnInvitationFinishedListener listener, final Context context){
        ContactsManager contactsManager=new ContactsManager(context);
        contactsManager.acceptDeclineInvitation(currentUser,idFrind, Urls.acceptInvitation, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {

                   listener.onAcceptInvitationSucces();

            }

            @Override
            public void onError(Object error) {
                listener.onAcceptInvitationError();
            }
        });
    }

    public void declineInvitation(int currentUser,int idFrind, final OnInvitationFinishedListener listener, final Context context){
        ContactsManager contactsManager=new ContactsManager(context);
        contactsManager.acceptDeclineInvitation(currentUser,idFrind,Urls.declineInvitation, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {

                listener.onDeclineInvitationSucces();

            }

            @Override
            public void onError(Object error) {
                listener.onDeclineInvitationError();
            }
        });
    }
}
