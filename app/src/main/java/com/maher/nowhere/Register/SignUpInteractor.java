package com.maher.nowhere.Register;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccountManager;
import com.maher.nowhere.utiles.InputValidator;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by maher on 14/11/2017.
 */

public class SignUpInteractor {

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


    public boolean checkInput(String  etUsername, String  etPassword, String etEmail, Bitmap etImage, final OnSignUpFinishedListener listener) {
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
        return true;
    }

    public void signUp(String username, String password,String email,Bitmap image, final OnSignUpFinishedListener listener, final Context context) {
        AccountManager accountManager=new AccountManager(context);
        String imageS=getStringImage(image);

        accountManager.register(email,username, password,imageS,"jpg", new VolleyCallback() {
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

    private String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }


}
