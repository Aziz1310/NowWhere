package com.maher.nowhere.ProfileActivity.fragments.reservation;

import android.content.Context;

import com.android.volley.VolleyError;
import com.maher.nowhere.model.Reservation;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class ReservationPresenter implements ReservationInteractor.OnReservationFinishedListener {

    private ReservationView reservationView;
    private ReservationInteractor reservationInteractor;
    private Context mcContext;

    public ReservationPresenter(ReservationView reservationView, Context mcContext) {
        this.reservationView = reservationView;
        this.mcContext = mcContext;
        reservationInteractor = new ReservationInteractor();
    }


    public void getListReservation(int idUser) {
        reservationView.showProgress();
        reservationInteractor.getListReservation(idUser, this,mcContext);
        }



    @Override
    public void onSuccess(ArrayList<Reservation> reservations) {
        reservationView.hideProgress();
        if(!reservations.isEmpty())
            reservationView.loadAllReservation(reservations);
        else reservationView.loadNoReservation(reservations);

    }

    @Override
    public void onError(VolleyError error) {
        reservationView.hideProgress();
        reservationView.networkError(error);
    }


}
