package com.maher.nowhere.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by maher on 27/12/2017.
 */

public class Film implements Serializable {
    private String nom;
    private String duree;
    private String dateSortie;
    private String realisateur;
    private String genre;
    private String nationalites;
    private String synopsis;
    private String dateDebut;
    private String dateFin;
    private String heureDefusin;
    private String acteurs;
    private String photo;
    private Owner owner;
    private String video;
    private Date cDate;
    private String monthNumber;//1-2-3....12
    private String month;//nov-oct...december
    private String year;//2015-2016...2020
    private String day;//lun-mar....dim
    private String dayOfWeek;//01-02-03...31
    private String hour;
    private String minute;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNationalites() {
        return nationalites;
    }

    public void setNationalites(String nationalites) {
        this.nationalites = nationalites;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getHeureDefusin() {
        return heureDefusin;
    }

    public void setHeureDefusin(String heureDefusin) {
        this.heureDefusin = heureDefusin;
    }

    public String getActeurs() {
        return acteurs;
    }

    public void setActeurs(String acteurs) {
        this.acteurs = acteurs;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date date) {
        this.cDate = date;

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE", Locale.FRANCE);
        SimpleDateFormat formatterMonth = new SimpleDateFormat("MMM", Locale.FRANCE);
        String formatedDay = formatter.format(date);
        String formatedMonth = formatterMonth.format(date);

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        setDay(formatedDay.substring(0, 3));//lun-mar

        String dayOfMonth = c.get(Calendar.DAY_OF_MONTH) + "";
        if (dayOfMonth.length() == 1)
            dayOfMonth = "0" + dayOfMonth;
        setDayOfWeek(dayOfMonth);//01/09...31

        if (formatedMonth.length() == 4)
            setMonth(formatedMonth.substring(0, (formatedMonth.length() - 1)));//nov-oct
        else
            setMonth(formatedMonth);//nov-oct

        setYear(c.get(Calendar.YEAR) + "");
        setMonthNumber((c.get(Calendar.MONTH) + 1) + "");
        setHour(c.get(Calendar.HOUR_OF_DAY) + "");
        setMinute(c.get(Calendar.MINUTE) + "");
    }

    public String getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(String monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }


}
