package com.maher.nowhere.commentsActivity;

import android.content.Context;

import com.maher.nowhere.model.Comment;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class CommentPresenter implements CommentInteractor.OnCommentFinishedListener {

    private CommentView commentView;
    private CommentInteractor commentInteractor;
    private Context mcContext;

    public CommentPresenter(CommentView commentView, Context mcContext) {
        this.commentView = commentView;
        this.mcContext = mcContext;
        commentInteractor = new CommentInteractor();
    }

    public void addComment(int idUser,int idPublication,String contenu) {
        commentView.showProgress();
        commentInteractor.addComment(idUser,idPublication,contenu, this, mcContext);
    }

    @Override
    public void onSuccess(ArrayList<Comment> comments) {
        commentView.hideProgress();
        commentView.loadAllComments(comments);
    }

    @Override
    public void onError() {
        commentView.hideProgress();
        commentView.networkError();
    }
}
