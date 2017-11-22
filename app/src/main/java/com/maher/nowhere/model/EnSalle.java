package com.maher.nowhere.model;

/**
 * Created by RaniaH on 22/11/2017.
 */

public class EnSalle {

    private int image;
    private String day, month, year, title, lieu;

    public EnSalle() {
    }

    public EnSalle(int image, String day, String month, String year, String title, String lieu) {
        this.image = image;
        this.day = day;
        this.month = month;
        this.year = year;
        this.title = title;
        this.lieu = lieu;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
