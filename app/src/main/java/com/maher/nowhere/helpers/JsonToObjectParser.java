package com.maher.nowhere.helpers;


import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Utiles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;


public class JsonToObjectParser {


    public User parseUser(JSONObject user) {
        JSONObject userJson;
        User user1 = new User();

        try {
            userJson = new JSONObject(user.getJSONObject("user").toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return new User();
        }
        try {
            user1.setName(userJson.getString("username"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            user1.setEmail(userJson.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            user1.setPassword(userJson.getString("password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            user1.setImage(userJson.getString("photo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            user1.setId(userJson.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return user1;

    }

    public Post parsePost(JSONObject postJson) {
        Post post = new Post();

        try {
            post.setTitle(postJson.getString("_groupe_nom"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setId(postJson.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setName(postJson.getString("_evenement_nom"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setDescription(postJson.getString("descreption"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setUrlImage(postJson.getString("photo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setNbrPlace(postJson.getInt("nbr_place"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setHeureDebut(postJson.getString("heure_debut"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setHeureFin(postJson.getString("heure_fin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setDate(new Utiles().parseDate(postJson.getString("date")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            Owner owner = parseOwner(postJson.getJSONObject("prestataire"));
            post.setOwner(owner);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            post.setId(postJson.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return post;

    }

    public ArrayList<Post> parsePosts(JSONArray postsJson) {
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 0; i < postsJson.length(); i++) {
            try {
                Post post = parsePost(postsJson.getJSONObject(i));
                posts.add(post);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return posts;
    }

    private Owner parseOwner(JSONObject ownerJson) {
        Owner owner = new Owner();
        try {
            owner.setGouvernorat(ownerJson.getString("gouvernorat"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setAdresse(ownerJson.getString("adresse"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setDescription(ownerJson.getString("description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setNom(ownerJson.getString("nom"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setTelFix(ownerJson.getString("tel_fix"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setTelMobile(ownerJson.getString("tel_mobile"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setLongitude(ownerJson.getDouble("longetude"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setLatitude(ownerJson.getDouble("latitude"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setUrlImage(ownerJson.getString("photo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setId(ownerJson.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return owner;

    }


}
