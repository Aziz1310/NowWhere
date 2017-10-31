package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by RaniaH on 31/10/2017.
 */

public class Mur implements Serializable {

    private int Image;
    private String title;
    private String month;
    private String year;
    private String day;
    private String name;
    private User user;
    private String heure;

    public Mur(){

    }

    public Mur(int image, String title, String month, String year, String day, String name, User user, String heure) {
        Image = image;
        this.title = title;
        this.month = month;
        this.year = year;
        this.day = day;
        this.name = name;
        this.user = user;
        this.heure = heure;
    }

    public Mur(int image) {

    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
