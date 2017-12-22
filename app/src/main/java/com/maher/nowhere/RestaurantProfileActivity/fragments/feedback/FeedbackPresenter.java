package com.maher.nowhere.RestaurantProfileActivity.fragments.feedback;

import android.content.Context;

import com.maher.nowhere.mainActivity.fragments.acceuil.AccueilView;
import com.maher.nowhere.model.Feedback;
import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class FeedbackPresenter implements FeedbackInteractor.OnFeedbackFinishedListener {

    private FeedbackView feedbackView;
    private FeedbackInteractor feedbackInteractor;
    private Context mcContext;

    public FeedbackPresenter(FeedbackView feedbackView, Context mcContext) {
        this.feedbackView = feedbackView;
        this.mcContext = mcContext;
        feedbackInteractor = new FeedbackInteractor();
    }


    public void getListFeedback(int idUser, int idprestataire) {
        feedbackView.showProgress();
        feedbackInteractor.getListFeedback(idUser, idprestataire, this, mcContext);
    }

    public void addFeedback(int idUser, int idprestataire, String contenu, String note) {
        feedbackView.showProgress();
        feedbackInteractor.addFeedback(idUser, idprestataire, contenu, note, this, mcContext);
    }


    @Override
    public void onSuccess(ArrayList<Feedback> feedbacks) {
        feedbackView.hideProgress();
        if (!feedbacks.isEmpty())
            feedbackView.loadAllFeedbacks(feedbacks);
        else feedbackView.loadNoFeedbacks(feedbacks);

    }

    @Override
    public void onError() {
        feedbackView.hideProgress();
        feedbackView.networkError();
    }

    @Override
    public void onAddFeedBackSuccess(String message) {
        feedbackView.addFeedbackSuccess(message);
    }

    @Override
    public void onAddFeedBackError(String message) {
        feedbackView.addFeedbackError(message);
    }

}
