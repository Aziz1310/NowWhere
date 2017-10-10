package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by RaniaH on 08/10/2017.
 */

public class Categorie implements Serializable{
    private int img1,img2;
    private String text;
    private String shortName;
    private String text2="";


    public Categorie(){

    }

    public Categorie(int img1, int img2, String text, String shortName) {
        this.img1 = img1;
        this.img2 = img2;
        this.text = text;
        this.shortName=shortName;
    }
    public Categorie(int img1, int img2, String text, String shortName, String text2) {
        this.img1 = img1;
        this.img2 = img2;
        this.text = text;
        this.shortName=shortName;
        this.text2=text2;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {

        img1 = img1;
    }

    public int getImg2() {

        return img2;
    }

    public void setImg2(int img1) {

        img2 = img2;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
