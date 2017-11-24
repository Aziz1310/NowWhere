package com.maher.nowhere.model;

import java.io.Serializable;

/**
 * Created by RaniaH on 24/11/2017.
 */

public class Trynow implements Serializable {

    private String tv_pack, pack_prst, pack_prix;

    public Trynow() {
    }

    public Trynow(String tv_pack, String pack_prst, String pack_prix) {
        this.tv_pack = tv_pack;
        this.pack_prst = pack_prst;
        this.pack_prix = pack_prix;
    }

    public String getTv_pack() {
        return tv_pack;
    }

    public void setTv_pack(String tv_pack) {
        this.tv_pack = tv_pack;
    }

    public String getPack_prst() {
        return pack_prst;
    }

    public void setPack_prst(String pack_prst) {
        this.pack_prst = pack_prst;
    }

    public String getPack_prix() {
        return pack_prix;
    }

    public void setPack_prix(String pack_prix) {
        this.pack_prix = pack_prix;
    }
}
