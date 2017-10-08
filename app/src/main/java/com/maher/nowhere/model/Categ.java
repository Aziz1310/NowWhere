package com.maher.nowhere.model;

/**
 * Created by RaniaH on 08/10/2017.
 */

public class Categ {
    private int img1,img2;
    private String text;

    public Categ (){

    }

    public Categ(int img1, int img2, String text) {
        this.img1 = img1;
        this.img2 = img2;
        this.text = text;
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
}
