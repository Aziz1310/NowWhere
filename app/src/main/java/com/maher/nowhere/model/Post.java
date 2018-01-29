package com.maher.nowhere.model;

import com.maher.nowhere.utiles.Urls;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by maher on 07/10/2017.
 */

public class Post implements Serializable {
    private int id;
    private int Image;
    private String title;
    private String monthNumber;//1-2-3....12
    private String month;//nov-oct...december
    private String year;//2015-2016...2020
    private String day;//lun-mar....dim
    private String dayOfWeek;//01-02-03...31
    private String name;
    private User user;
    private String heureFin;
    private String heureDebut;
    private String heureDispo;
    private String description;
    private String apropos;
    private Date date;
    private int nbrPlace;
    private String urlImage;
    private Owner owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Post() {
    }

    public Post(int image) {
        Image = image;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE", Locale.FRANCE);
        SimpleDateFormat formatterMonth = new SimpleDateFormat("MMM", Locale.FRANCE);
        String formatedDay = formatter.format(date);
        String formatedMonth = formatterMonth.format(date);

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        setDay(formatedDay.substring(0,3));//lun-mar

        String dayOfMonth=c.get(Calendar.DAY_OF_MONTH)+"";
        if(dayOfMonth.length()==1)
            dayOfMonth="0"+dayOfMonth;
        setDayOfWeek(dayOfMonth);//01/09...31

        if(formatedMonth.length()>3)
            setMonth(formatedMonth.substring(0,3));//nov-oct
        else
            setMonth(formatedMonth);//nov-oct

        setYear(c.get(Calendar.YEAR)+"");
        setMonthNumber((c.get(Calendar.MONTH)+1)+"");
        System.out.println("year"+getYear());

    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public String getUrlImage() {
        return Urls.IMG_URL+urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(String monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getHeureDispo() {
        return heureDispo;
    }

    public void setHeureDispo(String heureDispo) {
        this.heureDispo = heureDispo;
    }

    public String getApropos() {
        return apropos;
    }

    public void setApropos(String apropos) {
        this.apropos = apropos;
    }
}
