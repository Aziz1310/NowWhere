package com.maher.nowhere.ContactsActivity.views;

import com.maher.nowhere.model.User;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface AmisView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllFrinds(ArrayList<User> users);
    void loadNoFrind(ArrayList<User> users);
    void deleteFrindSuccess();
    void deleteFrindError();

}
