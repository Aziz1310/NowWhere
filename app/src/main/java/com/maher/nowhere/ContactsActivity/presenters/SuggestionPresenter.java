package com.maher.nowhere.ContactsActivity.presenters;

import android.content.Context;

import com.maher.nowhere.ContactsActivity.interactors.SuggestionInteractor;
import com.maher.nowhere.ContactsActivity.views.SuggestionView;
import com.maher.nowhere.login.LoginInteractor;
import com.maher.nowhere.login.LoginView;
import com.maher.nowhere.model.User;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class SuggestionPresenter implements SuggestionInteractor.OnSuggestionFinishedListener {

    private SuggestionView suggestionView;
    private SuggestionInteractor suggestionInteractor;
    private Context mcContext;

    public SuggestionPresenter(SuggestionView suggestionView, Context mcContext) {
        this.suggestionView = suggestionView;
        this.mcContext = mcContext;
        suggestionInteractor = new SuggestionInteractor();
    }


    public void getListSuggestion(int idUser) {
        suggestionView.showProgress();
        suggestionInteractor.getListSuggestions(idUser, this, mcContext);
    }

    public void sendInvitation(int currentUser,int idamis) {
        suggestionView.showProgress();
        suggestionInteractor.sendInvitation(currentUser, idamis, this, mcContext);
    }


    @Override
    public void onSuccess(ArrayList<User> users) {
        suggestionView.hideProgress();
        if (!users.isEmpty())
            suggestionView.loadAllSuggestion(users);
        else suggestionView.loadNoSuggestion(users);

    }

    @Override
    public void onError() {
        suggestionView.hideProgress();

    }

    @Override
    public void onInvitationSucces() {
        suggestionView.hideProgress();
        suggestionView.invitationSendSuccess();
    }

    @Override
    public void onInvitationError() {
        suggestionView.hideProgress();
        suggestionView.invitationSendError();
    }
}
