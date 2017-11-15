package com.maher.nowhere.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by maher on 07/10/2017.
 */

public class User {
    private int id;
    private String name;
    private String image;
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

    public static void setCurrentUser(User user, Context a){

        SharedPreferences sharedpreferences = a.getSharedPreferences("user_credentials",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.putString("image", user.getImage());
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

        return u;

    }


    public static boolean logOut(Activity a){
        SharedPreferences sharedpreferences = a.getSharedPreferences("user_credentials",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        return editor.clear().commit();
        // editor.apply();
    }

}
