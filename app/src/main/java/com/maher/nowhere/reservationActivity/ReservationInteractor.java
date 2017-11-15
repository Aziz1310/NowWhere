package com.maher.nowhere.reservationActivity;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.providers.EventManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by maher on 15/11/2017.
 */

public class ReservationInteractor {
    public interface onReservationFinish{
        void onSuccess();
        void onError();
        void onHourChange(String hour);
        void onNbrPersonnesChange(String nbr);
    }

    public void reserver(int id, String nbrPersonne, String  hour, final onReservationFinish onReservationFinish,Context mcContext){
        EventManager eventManager=new EventManager(mcContext);
        eventManager.reservation(id, nbrPersonne, hour, new VolleyCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        onReservationFinish.onSuccess();
                    }
                    @Override
                    public void onError(Object error) {
                        onReservationFinish.onError();
                    }
                });
    }

    public void changeHours(String hour, int minutes, String heureDebut, String heureFin, ReservationPresenter reservationPresenter){

        SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.FRANCE);
        Date d = null;
        Date dateDebut=null;
        Date dateFin=null;
        try {
            d = df.parse(hour);
            dateDebut=df.parse(heureDebut);
            dateFin=df.parse(heureFin);

            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MINUTE, minutes);
            String newTime = df.format(cal.getTime());

            if (cal.getTime().compareTo(dateDebut)>=0 && cal.getTime().compareTo(dateFin)<=0)
            reservationPresenter.onHourChange(newTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void incrementNbrPersonne(String nbr, ReservationPresenter reservationPresenter){
        reservationPresenter.onNbrPersonnesChange(String.valueOf((Integer.parseInt(nbr)+1)));

    }
    public void decrementNbrPersonnes(String nbr, ReservationPresenter reservationPresenter){
        int num=Integer.parseInt(nbr);
        if(num>1)
            reservationPresenter.onNbrPersonnesChange((num-1)+"");

    }




}
