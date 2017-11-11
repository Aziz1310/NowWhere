package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by RaniaH on 09/11/2017.
 */

public class MenuV implements Serializable {
    private String menuNom;
    private String menuPrst;
    private String menuPrix;

    public MenuV (){

    }

    public MenuV(String menuNom, String menuPrst, String menuPrix) {
        this.menuNom = menuNom;
        this.menuPrst = menuPrst;
        this.menuPrix = menuPrix;
    }

    public String getMenuNom() {
        return menuNom;
    }

    public void setMenuNom(String menuNom) {
        this.menuNom = menuNom;
    }

    public String getMenuPrst() {
        return menuPrst;
    }

    public void setMenuPrst(String menuPrst) {
        this.menuPrst = menuPrst;
    }

    public String getMenuPrix() {
        return menuPrix;
    }

    public void setMenuPrix(String menuPrix) {
        this.menuPrix = menuPrix;
    }
}
