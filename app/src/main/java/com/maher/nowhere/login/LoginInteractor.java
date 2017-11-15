package com.maher.nowhere.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccountManager;

import org.json.JSONObject;

/**
 * Created by maher on 14/11/2017.
 */

public class LoginInteractor {

    public final static int EMPTY_PASSWORD=1;
    public final static int PASSWORD_LESS_THAN_4=3;


    public interface OnLoginFinishedListener{
        void onUsernameError();
        void onPasswordError(int code);
        void onSuccess();
        void onError();
    }


    public boolean checkInput(String  etUsername, String  etPassword,final OnLoginFinishedListener listener) {
        if(etUsername.isEmpty()){
            listener.onUsernameError();
            return false;
        }
        if(etPassword.isEmpty()){
            listener.onPasswordError(EMPTY_PASSWORD);
            return false;
        }
        if(etPassword.length()<4){
            listener.onPasswordError(PASSWORD_LESS_THAN_4);
            return false;
        }
        return true;
    }

    public void login(String username, String password, final OnLoginFinishedListener listener, final Context context) {
        AccountManager accountManager=new AccountManager(context);
        accountManager.signin(username, password, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                User user= new JsonToObjectParser().parseUser((JSONObject)response);
                User.setCurrentUser(user,context);
                listener.onSuccess();
            }

            @Override
            public void onError(Object error) {
                listener.onError();
            }
        });
    }


}
