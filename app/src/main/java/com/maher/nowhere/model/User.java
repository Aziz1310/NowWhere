package com.maher.nowhere.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

/**
 * Created by maher on 07/10/2017.
 */

public class User implements Serializable{
    private int id;
    private String name;
    private String image;
    private String coverPhoto;
    private String password;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public static void setCurrentUser(User user, Context a){

        SharedPreferences sharedpreferences = a.getSharedPreferences("user_credentials",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.putString("image", user.getImage());
        editor.putString("cover_photo", user.getCoverPhoto());
        editor.putInt("id", user.getId());
        editor.apply();

    }

    public static User getCurrentUser(Context a){
        SharedPreferences sharedpreferences = a.getSharedPreferences("user_credentials", Activity.MODE_PRIVATE);
        User u =new User();
        u.setEmail(sharedpreferences.getString("email", ""));
        u.setPassword(sharedpreferences.getString("password", ""));
        u.setName(sharedpreferences.getString("name",""));
        u.setImage(sharedpreferences.getString("image",""));
        u.setId(sharedpreferences.getInt("id",0));
        u.setCoverPhoto(sharedpreferences.getString("cover_photo",""));

        return u;

    }


    public static boolean logOut(Activity a){
        SharedPreferences sharedpreferences = a.getSharedPreferences("user_credentials",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        return editor.clear().commit();
        // editor.apply();
    }

}
