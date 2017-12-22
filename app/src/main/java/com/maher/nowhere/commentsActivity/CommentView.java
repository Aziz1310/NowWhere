package com.maher.nowhere.commentsActivity;

import com.maher.nowhere.model.Comment;
import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface CommentView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllComments(ArrayList<Comment> comments);

}
