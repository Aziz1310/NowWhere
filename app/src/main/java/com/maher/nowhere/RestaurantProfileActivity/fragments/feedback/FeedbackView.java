package com.maher.nowhere.RestaurantProfileActivity.fragments.feedback;

import com.maher.nowhere.model.Feedback;
import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface FeedbackView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllFeedbacks(ArrayList<Feedback> feedbacks);
    void loadNoFeedbacks(ArrayList<Feedback> feedbacks);
    void addFeedbackSuccess(String message);
    void addFeedbackError(String message);

}
