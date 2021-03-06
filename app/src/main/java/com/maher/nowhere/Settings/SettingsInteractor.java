package com.maher.nowhere.Settings;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccountManager;
import com.maher.nowhere.utiles.InputValidator;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by maher on 14/11/2017.
 */

public class SettingsInteractor {

    public final static int EMPTY_PASSWORD=1;
    public final static int PASSWORD_LESS_THAN_4=2;
    public final static int EMAIL_BADLY_FORMATED=3;
    public final static int EMPTY_EMAIL=4;


    public interface OnSignUpFinishedListener{
        void onUsernameError();
        void onPasswordError(int code);
        void onSuccess();
        void onError();
        void onEmailError(int code);
        void onImageEmpty();
    }


    public boolean checkInput(String  etUsername, String  etPassword, String etEmail, byte[]  etImage,byte[]  imgCover, final OnSignUpFinishedListener listener) {
        InputValidator inputValidator = new InputValidator();
        if(etUsername.isEmpty()){
            listener.onUsernameError();
            return false;
        }
        if(etEmail.isEmpty()){
            listener.onEmailError(EMPTY_EMAIL);
            return false;
        }
        if (!inputValidator.isEmailValid(etEmail)) {
            listener.onEmailError(EMAIL_BADLY_FORMATED);
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
        if(etImage==null){
            listener.onImageEmpty();
            return false;
        }
        if(imgCover==null){
            listener.onImageEmpty();
            return false;
        }
        return true;
    }

    public void settings(String username, String password,String email,byte[]  image,byte[]  imageCover, final OnSignUpFinishedListener listener, final Context context) {
        AccountManager accountManager=new AccountManager(context);

        accountManager.settings(email,username, password,image,imageCover, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                System.out.println(response.toString());
                User user = new JsonToObjectParser().parseUser( (JSONObject) response);
                User.setCurrentUser(user, context);
                listener.onSuccess();
            }

            @Override
            public void onError(Object error) {
                listener.onError();
            }
        });
    }

}
