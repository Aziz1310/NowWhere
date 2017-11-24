package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by RaniaH on 24/11/2017.
 */

public class Suggestions implements Serializable {

    private int profileImage;
    private String nom;
    private String amisCommun;

    public Suggestions() {
    }

    public Suggestions(int profileImage, String nom, String amisCommun) {
        this.profileImage = profileImage;
        this.nom = nom;
        this.amisCommun = amisCommun;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAmisCommun() {
        return amisCommun;
    }

    public void setAmisCommun(String amisCommun) {
        this.amisCommun = amisCommun;
    }
}
