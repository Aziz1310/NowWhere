package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by RaniaH on 25/11/2017.
 */

public class Product implements Serializable {

    private int produitImage;
    private String nomProduit;
    private String titleProduit;
    private String prixProduit;

    public Product() {
    }

    public Product(int produitImage, String nomProduit, String titleProduit, String prixProduit) {
        this.produitImage = produitImage;
        this.nomProduit = nomProduit;
        this.titleProduit = titleProduit;
        this.prixProduit = prixProduit;
    }

    public int getProduitImage() {
        return produitImage;
    }

    public void setProduitImage(int produitImage) {
        this.produitImage = produitImage;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getTitleProduit() {
        return titleProduit;
    }

    public void setTitleProduit(String titleProduit) {
        this.titleProduit = titleProduit;
    }

    public String getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(String prixProduit) {
        this.prixProduit = prixProduit;
    }
}
