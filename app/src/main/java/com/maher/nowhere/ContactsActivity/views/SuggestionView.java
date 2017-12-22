package com.maher.nowhere.ContactsActivity.views;

import com.maher.nowhere.model.User;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface SuggestionView {

    void showProgress();
    void hideProgress();
    void sendInvitation();
    void networkError();
    void loadAllSuggestion(ArrayList<User> users);
    void loadNoSuggestion(ArrayList<User> users);
    void invitationSendSuccess();
    void invitationSendError();

}
