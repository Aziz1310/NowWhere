package com.maher.nowhere.commentsActivity;

import android.content.Context;

import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Comment;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.providers.AccueilManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class CommentInteractor {



    public interface OnCommentFinishedListener{
        void onSuccess(ArrayList<Comment> comments);
        void onError();
    }



    public void addComment(int iduser,int idPublication,String comment, final OnCommentFinishedListener listener, final Context context) {
        AccueilManager accueilManager=new AccueilManager(context);
        accueilManager.addComment(iduser,idPublication,comment, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {


                try {
                    ArrayList<Comment> comments=new JsonToObjectParser().parseComments(((JSONObject)response).getJSONArray("list_commentaire"));
                    listener.onSuccess(comments);
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
}
