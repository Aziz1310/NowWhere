package com.maher.nowhere.model;



import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public class Publication implements Serializable{
    private int id;
    private int nbrJaime;
    private String description;
    private String image;
    private ArrayList<Comment> comments;
    private String date;
    private String ownerImage;
    private int ownerId;
    private String ownerName;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrJaime() {
        return nbrJaime;
    }

    public void setNbrJaime(int nbrJaime) {
        this.nbrJaime = nbrJaime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
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
