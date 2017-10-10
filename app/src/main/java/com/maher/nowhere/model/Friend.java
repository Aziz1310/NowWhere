package com.maher.nowhere.model;

/**
 * Created by RaniaH on 09/10/2017.
 */

public class Friend {

    private int profileImage;
    private String nom;
    private String disponibility;

    public Friend(){

    }

    public Friend(int profileImage, String nom, String disponibility) {
        this.profileImage = profileImage;
        this.nom = nom;
        this.disponibility = disponibility;
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

    public String getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(String disponibility) {
        this.disponibility = disponibility;
    }
}
