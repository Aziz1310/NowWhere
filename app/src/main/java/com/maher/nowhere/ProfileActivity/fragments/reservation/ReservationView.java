package com.maher.nowhere.ProfileActivity.fragments.reservation;

import com.android.volley.VolleyError;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.model.Reservation;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface ReservationView {

    void showProgress();
    void hideProgress();
    void networkError(VolleyError error);
    void loadAllReservation(ArrayList<Reservation> reservations);
    void loadNoReservation(ArrayList<Reservation> reservations);

}
