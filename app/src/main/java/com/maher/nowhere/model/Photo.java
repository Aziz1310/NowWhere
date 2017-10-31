package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by RaniaH on 31/10/2017.
 */

public class Photo implements Serializable {
    private int Image;

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
}
