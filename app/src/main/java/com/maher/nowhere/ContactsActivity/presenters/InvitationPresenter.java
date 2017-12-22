package com.maher.nowhere.ContactsActivity.presenters;

import android.content.Context;

import com.maher.nowhere.ContactsActivity.interactors.InvitationInteractor;
import com.maher.nowhere.ContactsActivity.interactors.SuggestionInteractor;
import com.maher.nowhere.ContactsActivity.views.InvitationView;
import com.maher.nowhere.ContactsActivity.views.SuggestionView;
import com.maher.nowhere.model.User;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class InvitationPresenter implements InvitationInteractor.OnInvitationFinishedListener {

    private InvitationView invitationView;
    private InvitationInteractor invitationInteractor;
    private Context mcContext;

    public InvitationPresenter(InvitationView invitationView, Context mcContext) {
        this.invitationView = invitationView;
        this.mcContext = mcContext;
        invitationInteractor = new InvitationInteractor();
    }


    public void getListInvitation(int idUser) {
        invitationView.showProgress();
        invitationInteractor.getListInvitation(idUser, this, mcContext);
    }

    public void acceptInvitation(int currentUser, int idAmis) {
        invitationView.showProgress();
        invitationInteractor.acceptInvitation(currentUser, idAmis, this, mcContext);

    }

    public void declineIvitation(int currentUser, int idAmis) {
        invitationView.showProgress();
        invitationInteractor.declineInvitation(currentUser, idAmis, this, mcContext);

    }


    @Override
    public void onSuccess(ArrayList<User> users) {
        invitationView.hideProgress();
        if (!users.isEmpty())
            invitationView.loadAllInvitation(users);
        else invitationView.loadNoInvitation(users);

    }

    @Override
    public void onError() {
        invitationView.hideProgress();

    }

    @Override
    public void onAcceptInvitationSucces() {
        invitationView.hideProgress();
        invitationView.invitationAcceptSuccess();


    }

    @Override
    public void onAcceptInvitationError() {
        invitationView.hideProgress();
        invitationView.invitationAcceptError();

    }

    @Override
    public void onDeclineInvitationSucces() {
        invitationView.hideProgress();
        invitationView.invitationDeclineSuccess();
    }

    @Override
    public void onDeclineInvitationError() {
        invitationView.hideProgress();
        invitationView.invitationDeclineError();
    }


}
