package com.maher.nowhere.RestaurantProfileActivity.fragments.menu;

import android.content.Context;

import com.maher.nowhere.RestaurantProfileActivity.fragments.feedback.FeedbackInteractor;
import com.maher.nowhere.RestaurantProfileActivity.fragments.feedback.FeedbackView;
import com.maher.nowhere.model.Feedback;
import com.maher.nowhere.model.Menu;
import com.maher.nowhere.model.MenuV;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class MenuPresenter implements MenuInteractor.OnFinishListener {

    private MenuView menuView;
    private MenuInteractor menuInteractor;
    private Context mcContext;

    public MenuPresenter(MenuView menuView, Context mcContext) {
        this.menuView = menuView;
        this.mcContext = mcContext;
        menuInteractor = new MenuInteractor();
    }


    public void getListMenus(int idprestataire) {
        menuView.showProgress();
        menuInteractor.getListMenu(idprestataire, this, mcContext);
    }

    @Override
    public void onSuccess(ArrayList<Menu> menus) {
        menuView.hideProgress();
        if (!menus.isEmpty())
            menuView.loadAllMenus(menus);
        else menuView.loadNoMenu(menus);
    }

    @Override
    public void onError() {
        menuView.hideProgress();
        menuView.networkError();
    }

}
