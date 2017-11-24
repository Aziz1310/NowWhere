package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by maher on 30/10/2017.
 */

public class Reservation implements Serializable{
    private String resNum;
    private String date;
    private boolean status;

    public Reservation(String resNum, String date, boolean status) {
        this.resNum = resNum;
        this.date = date;
        this.status = status;
    }

    public String getResNum() {
        return resNum;
    }

    public void setResNum(String resNum) {
        this.resNum = resNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
