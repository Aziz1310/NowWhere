package com.maher.nowhere.model;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by RaniaH on 01/12/2017.
 */

public class Feedback implements Serializable {


    private String globalNote;
    private User user;
    private String contenu;
    private String userNote;

    public String getGlobalNote() {
        return globalNote;
    }

    public void setGlobalNote(String globalNote) {
        this.globalNote = globalNote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    ImageView prof_img;
    TextView nomProf, comment_avis, time_avis;

    public Feedback() {
    }

    public Feedback(ImageView prof_img, TextView nomProf, TextView comment_avis, TextView time_avis) {
        this.prof_img = prof_img;
        this.nomProf = nomProf;
        this.comment_avis = comment_avis;
        this.time_avis = time_avis;
    }

    public ImageView getProf_img() {
        return prof_img;
    }

    public void setProf_img(ImageView prof_img) {
        this.prof_img = prof_img;
    }

    public TextView getNomProf() {
        return nomProf;
    }

    public void setNomProf(TextView nomProf) {
        this.nomProf = nomProf;
    }

    public TextView getComment_avis() {
        return comment_avis;
    }

    public void setComment_avis(TextView comment_avis) {
        this.comment_avis = comment_avis;
    }

    public TextView getTime_avis() {
        return time_avis;
    }

    public void setTime_avis(TextView time_avis) {
        this.time_avis = time_avis;
    }
}
