package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by maher on 05/12/2017.
 */

public class Comment implements Serializable{
    private int id;
    private String contenus;
    private String date;
    private String ownerImage;
    private int ownerId;
    private String ownerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenus() {
        return contenus;
    }

    public void setContenus(String contenus) {
        this.contenus = contenus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOwnerImage() {
        return ownerImage;
    }

    public void setOwnerImage(String ownerImage) {
        this.ownerImage = ownerImage;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
