package com.maher.nowhere.model;

/**
 * Created by RaniaH on 08/10/2017.
 */

public class Search {
    private int image;
    private String day, month, year, title, name;

    public Search () {

    }

    public Search(int image, String day, String month, String year, String title, String name) {
        this.image = image;
        this.day = day;
        this.month = month;
        this.year = year;
        this.title = title;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
