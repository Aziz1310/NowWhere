package com.maher.nowhere.model;

import com.maher.nowhere.utiles.Urls;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by maher on 02/11/2017.
 */

public class Owner implements Serializable{
    private int id;
    private String nom;
    private String description;
    private String adresse;
    private  String gouvernorat;
    private String telFix;
    private String telMobile;
    private Double latitude;
    private Double longitude;
    private String urlImage;

    private String mot_cles;
    private String heure_overture;
    private String heure_fermeture;
    private String couverture;
    private boolean bannee;
    private boolean valide;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlImage() {
        return Urls.IMG_URL_PRESTATAIRE+urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getTelFix() {
        return telFix;
    }

    public void setTelFix(String telFix) {
        this.telFix = telFix;
    }

    public String getTelMobile() {
        return telMobile;
    }

    public void setTelMobile(String telMobile) {
        this.telMobile = telMobile;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMot_cles() {
        return mot_cles;
    }

    public void setMot_cles(String mot_cles) {
        this.mot_cles = mot_cles;
    }

    public String getHeure_overture() {
        return heure_overture;
    }

    public void setHeure_overture(String heure_overture) {
        this.heure_overture = heure_overture;
    }

    public String getHeure_fermeture() {
        return heure_fermeture;
    }

    public void setHeure_fermeture(String heure_fermeture) {
        this.heure_fermeture = heure_fermeture;
    }

    public String getCouverture() {
        return couverture;
    }

    public void setCouverture(String couverture) {
        this.couverture = Urls.IMG_URL_PRESTATAIRE_COVER+couverture;
    }

    public boolean isBannee() {
        return bannee;
    }

    public void setBannee(boolean bannee) {
        this.bannee = bannee;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
}
