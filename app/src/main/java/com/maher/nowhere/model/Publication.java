package com.maher.nowhere.model;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by maher on 05/12/2017.
 */

public class Publication implements Serializable{
    private int id;
    private int nbrJaime;
    private String description;
    private String image;
    private ArrayList<Comment> comments;
    private String date;
    private String ownerImage;
    private int ownerId;
    private String ownerName;


    private Date cDate;
    private String monthNumber;//1-2-3....12
    private String month;//nov-oct...december
    private String year;//2015-2016...2020
    private String day;//lun-mar....dim
    private String dayOfWeek;//01-02-03...31
    private String hour;
    private String minute;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrJaime() {
        return nbrJaime;
    }

    public void setNbrJaime(int nbrJaime) {
        this.nbrJaime = nbrJaime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getOwnerImage() {
        return ownerImage;
    }

    public void setOwnerImage(String ownerImage) {
        this.ownerImage = ownerImage;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

        setDay(formatedDay.substring(0,3));//lun-mar

        String dayOfMonth=c.get(Calendar.DAY_OF_MONTH)+"";
        if(dayOfMonth.length()==1)
            dayOfMonth="0"+dayOfMonth;
        setDayOfWeek(dayOfMonth);//01/09...31

        if(formatedMonth.length()==4)
            setMonth(formatedMonth.substring(0,(formatedMonth.length()-1)));//nov-oct
        else
            setMonth(formatedMonth);//nov-oct

        setYear(c.get(Calendar.YEAR)+"");
        setMonthNumber((c.get(Calendar.MONTH)+1)+"");
        setHour(c.get(Calendar.HOUR_OF_DAY)+"");
        setMinute(c.get(Calendar.MINUTE)+"");
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
