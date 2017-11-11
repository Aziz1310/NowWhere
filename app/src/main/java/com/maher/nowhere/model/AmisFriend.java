package com.maher.nowhere.model;

/**
 * Created by RaniaH on 31/10/2017.
 */

public class AmisFriend {
    private int profileImage;
    private String nom;
    private String disponibility;
    private boolean status;

    public AmisFriend() {
    }

    public AmisFriend(int profileImage, String nom, String disponibility) {
        this.profileImage = profileImage;
        this.nom = nom;
        this.disponibility = disponibility;
    }


    public AmisFriend(int profileImage, String nom, String disponibility, boolean status) {
        this.profileImage = profileImage;
        this.nom = nom;
        this.disponibility = disponibility;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
