package com.maher.nowhere.model;

/**
 * Created by RaniaH on 08/10/2017.
 */

public class Search {
    private int img1;
    private String tvDay, tvMonth, tvYear, tvTitle, tvName;

    public Search () {

    }

    public Search(int img1, String tvDay, String tvMonth, String tvYear, String tvTitle, String tvName) {
        this.img1 = img1;
        this.tvDay = tvDay;
        this.tvMonth = tvMonth;
        this.tvYear = tvYear;
        this.tvTitle = tvTitle;
        this.tvName = tvName;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public String getTvDay() {
        return tvDay;
    }

    public void setTvDay(String tvDay) {
        this.tvDay = tvDay;
    }

    public String getTvMonth() {
        return tvMonth;
    }

    public void setTvMonth(String tvMonth) {
        this.tvMonth = tvMonth;
    }

    public String getTvYear() {
        return tvYear;
    }

    public void setTvYear(String tvYear) {
        this.tvYear = tvYear;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }
}
