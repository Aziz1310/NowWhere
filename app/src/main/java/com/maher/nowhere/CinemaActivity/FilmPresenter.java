package com.maher.nowhere.CinemaActivity;

import android.content.Context;

import com.maher.nowhere.mainActivity.fragments.acceuil.AccueilInteractor;
import com.maher.nowhere.mainActivity.fragments.acceuil.AccueilView;
import com.maher.nowhere.model.Film;
import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class FilmPresenter implements FilmInteractor.OnAccueilFinishedListener {

    private FilmView accueilView;
    private FilmInteractor accueilInteractor;
    private Context mcContext;

    public FilmPresenter(FilmView accueilView, Context mcContext) {
        this.accueilView = accueilView;
        this.mcContext = mcContext;
        accueilInteractor = new FilmInteractor();
    }


    public void getListPublication(int isNow) {
            accueilView.showProgress();
            accueilInteractor.getListFilm(isNow, this,mcContext);
        }



    @Override
    public void onSuccess(ArrayList<Film> films) {
        accueilView.hideProgress();
        if(!films.isEmpty())
            accueilView.loadAllFilms(films);
        else accueilView.loadNoFilm(films);

    }

    @Override
    public void onError() {
        accueilView.hideProgress();
        accueilView.networkError();
    }


}
