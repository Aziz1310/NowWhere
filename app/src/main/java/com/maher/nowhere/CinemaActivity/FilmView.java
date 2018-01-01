package com.maher.nowhere.CinemaActivity;

import com.maher.nowhere.model.Film;
import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface FilmView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllFilms(ArrayList<Film> films);
    void loadNoFilm(ArrayList<Film> films);

}
