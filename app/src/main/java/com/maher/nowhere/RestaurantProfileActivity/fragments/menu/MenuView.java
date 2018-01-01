package com.maher.nowhere.RestaurantProfileActivity.fragments.menu;

import com.maher.nowhere.model.Feedback;
import com.maher.nowhere.model.Menu;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface MenuView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllMenus(ArrayList<Menu> menus);
    void loadNoMenu(ArrayList<Menu> menus);

}
