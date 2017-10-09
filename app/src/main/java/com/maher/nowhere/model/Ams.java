package com.maher.nowhere.model;

/**
 * Created by RaniaH on 09/10/2017.
 */

public class Ams {

    private int img_amis, img_suppAmis;
    private String tv_nameAmis, tv_dispoAmis;

    public Ams(){

    }

    public Ams(int img_amis, int img_suppAmis, String tv_nameAmis, String tv_dispoAmis) {
        this.img_amis = img_amis;
        this.img_suppAmis = img_suppAmis;
        this.tv_nameAmis = tv_nameAmis;
        this.tv_dispoAmis = tv_dispoAmis;
    }

    public int getImg_amis() {
        return img_amis;
    }

    public void setImg_amis(int img_amis) {
        this.img_amis = img_amis;
    }

    public int getImg_suppAmis() {
        return img_suppAmis;
    }

    public void setImg_suppAmis(int img_suppAmis) {
        this.img_suppAmis = img_suppAmis;
    }

    public String getTv_nameAmis() {
        return tv_nameAmis;
    }

    public void setTv_nameAmis(String tv_nameAmis) {
        this.tv_nameAmis = tv_nameAmis;
    }

    public String getTv_dispoAmis() {
        return tv_dispoAmis;
    }

    public void setTv_dispoAmis(String tv_dispoAmis) {
        this.tv_dispoAmis = tv_dispoAmis;
    }
}
