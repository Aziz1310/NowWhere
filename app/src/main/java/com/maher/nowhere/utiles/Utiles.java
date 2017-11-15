package com.maher.nowhere.utiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by maher on 02/11/2017.
 */

public class Utiles {

   public Date  parseDate(String input){
        SimpleDateFormat parser=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date date = parser.parse(input);

            SimpleDateFormat formatter = new SimpleDateFormat("EEEE", Locale.FRANCE);
            String formattedDate = formatter.format(date);
            System.out.println("date ff"+formattedDate);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
