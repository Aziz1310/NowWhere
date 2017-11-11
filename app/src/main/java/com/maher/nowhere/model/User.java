package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by maher on 07/10/2017.
 */

public class User implements Serializable{
    private String name;
    private int image;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
