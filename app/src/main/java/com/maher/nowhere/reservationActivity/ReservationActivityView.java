package com.maher.nowhere.reservationActivity;

/**
 * Created by maher on 14/11/2017.
 */

public interface ReservationActivityView {
    void getHour(String hour);
    void getNbrPersonnes(String nbr);
    void showProgress();
    void hideProgress();
    void success();
    void error();
}
