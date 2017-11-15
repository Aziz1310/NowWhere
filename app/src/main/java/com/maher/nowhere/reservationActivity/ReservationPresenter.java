package com.maher.nowhere.reservationActivity;

import android.content.Context;

import com.maher.nowhere.model.Reservation;

/**
 * Created by maher on 15/11/2017.
 */

public class ReservationPresenter implements ReservationInteractor.onReservationFinish {


    ReservationActivityView reservationActivityView;
    ReservationInteractor reservationInteractor;
    Context mcContext;

    public ReservationPresenter(ReservationActivityView reservationActivityView, Context mcContext) {
        this.reservationActivityView = reservationActivityView;
        this.mcContext = mcContext;
        reservationInteractor = new ReservationInteractor();
    }


    public void reserver(int id, String nbrPersonnes, String hour) {
        reservationActivityView.showProgress();
        reservationInteractor.reserver(id, nbrPersonnes, hour, this, mcContext);
    }
    public void incrementHour(String hour, String heureDebut, String heureFin){
        reservationInteractor.changeHours(hour,30,heureDebut,heureFin,this);
    }
    public void decrementHour(String hour, String heureDebut, String heureFin){
        reservationInteractor.changeHours(hour,-30,heureDebut,heureFin,this);
    }
    public void incrementNbrPersonnes(String nbr){
        reservationInteractor.incrementNbrPersonne(nbr,this);
    }
    public void decrementNbrPersonnes(String nbr){
        reservationInteractor.decrementNbrPersonnes(nbr,this);
    }

    @Override
    public void onSuccess() {
        reservationActivityView.hideProgress();
        reservationActivityView.success();
    }

    @Override
    public void onError() {
        reservationActivityView.hideProgress();
        reservationActivityView.error();
    }

    @Override
    public void onHourChange(String hour) {
        reservationActivityView.getHour(hour);
    }

    @Override
    public void onNbrPersonnesChange(String nbr) {
        reservationActivityView.getNbrPersonnes(nbr);

    }


}
