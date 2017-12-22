package com.maher.nowhere.model;

import java.util.ArrayList;

/**
 * Created by maher on 22/12/2017.
 */

public class Menu {

    private String nom;
    private ArrayList<SubMenu>subMenus;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<SubMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(ArrayList<SubMenu> subMenus) {
        this.subMenus = subMenus;
    }
}
