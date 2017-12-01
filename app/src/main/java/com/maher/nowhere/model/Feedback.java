package com.maher.nowhere.model;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by RaniaH on 01/12/2017.
 */

public class Feedback implements Serializable {

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
