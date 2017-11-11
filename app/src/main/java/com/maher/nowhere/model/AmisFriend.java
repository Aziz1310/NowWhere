package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by RaniaH on 31/10/2017.
 */

public class AmisFriend implements Serializable{
    private int profileImage;
    private String nom;
    private String disponibility;

    public AmisFriend(){}

    public AmisFriend(int profileImage, String nom, String disponibility) {
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
