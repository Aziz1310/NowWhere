package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by RaniaH on 31/10/2017.
 */

public class Photo implements Serializable {
    private int Image;
    private String url;
    private String description;

    public Photo(){}

    public Photo(int image) {
        Image = image;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
