package com.maher.nowhere.ContactsActivity.views;

import com.maher.nowhere.model.User;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface InvitationView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllInvitation(ArrayList<User> users);
    void loadNoInvitation(ArrayList<User> users);
    void invitationAcceptSuccess();
    void invitationAcceptError();
    void invitationDeclineSuccess();
    void invitationDeclineError();

}
