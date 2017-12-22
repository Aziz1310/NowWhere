package com.maher.nowhere.ContactsActivity.presenters;

import android.content.Context;

import com.maher.nowhere.ContactsActivity.interactors.AmisInteractor;
import com.maher.nowhere.ContactsActivity.interactors.SuggestionInteractor;
import com.maher.nowhere.ContactsActivity.views.AmisView;
import com.maher.nowhere.ContactsActivity.views.SuggestionView;
import com.maher.nowhere.model.User;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class AmisPresenter implements AmisInteractor.OnSuggestionFinishedListener {

    private AmisView amisView;
    private AmisInteractor amisInteractor;
    private Context mcContext;

    public AmisPresenter(AmisView amisView, Context mcContext) {
        this.amisView = amisView;
        this.mcContext = mcContext;
        amisInteractor = new AmisInteractor();
    }


    public void getListFrinds(int idUser) {
            amisView.showProgress();
            amisInteractor.getListFrinds(idUser, this,mcContext);
        }
        public void deleteFrind(int currentUser,int idamis){
            amisView.showProgress();
            amisInteractor.deleteFrind(currentUser,idamis, this,mcContext);
        }


    @Override
    public void onSuccess(ArrayList<User> users) {
        amisView.hideProgress();
        if(!users.isEmpty())
            amisView.loadAllFrinds(users);
        else amisView.loadNoFrind(users);

    }

    @Override
    public void onError() {
        amisView.hideProgress();
    }

    @Override
    public void onDeleteSuccess() {
        amisView.hideProgress();
        amisView.deleteFrindSuccess();

    }

    @Override
    public void onDeleteError() {
        amisView.hideProgress();
        amisView.deleteFrindError();

    }
}
