package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by RaniaH on 09/11/2017.
 */

public class MenuH implements Serializable{
    private String text;

    public MenuH (){

    }

    public MenuH(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
