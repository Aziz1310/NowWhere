package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by RaniaH on 25/11/2017.
 */

public class Product implements Serializable {

    private String prix;
    private String nom;
    private String description;
    private String date;
    private String img;

    public Product() {
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
