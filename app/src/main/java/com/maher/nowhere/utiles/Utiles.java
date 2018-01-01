package com.maher.nowhere.utiles;

import android.net.Uri;

import com.maher.nowhere.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by maher on 02/11/2017.
 */

public class Utiles {

    public static final String DEVELOPER_KEY = "AIzaSyDdBFMfx6pDz-arQdB8wIixt4sP90r2Ti0";



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
    public Date  parseDate2(String input){
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+'SS:SS",Locale.FRANCE);
        try {
            Date date = parser.parse(input);

            SimpleDateFormat formatter = new SimpleDateFormat("EEEE", Locale.FRANCE);
            String formattedDate = formatter.format(date);
            System.out.println("date 2"+formattedDate);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getYouTubeVideoId(String video_url) {

        if (video_url != null && video_url.length() > 0) {

            Uri video_uri = Uri.parse(video_url);
            String video_id = video_uri.getQueryParameter("v");

            if (video_id == null)
                video_id = parseYoutubeVideoId(video_url);

            return video_id;
        }
        return null;
    }
    private static String parseYoutubeVideoId(String youtubeUrl)
    {
        String video_id = null;
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 &&
                youtubeUrl.startsWith("http"))
        {
            // ^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/
            String expression = "^.*((youtu.be" + "\\/)"
                    + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*";
            CharSequence input = youtubeUrl;
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches())
            {
                // Regular expression some how doesn't work with id with "v" at
                // prefix
                String groupIndex1 = matcher.group(7);
                if (groupIndex1 != null && groupIndex1.length() == 11)
                    video_id = groupIndex1;
                else if (groupIndex1 != null && groupIndex1.length() == 10)
                    video_id = "v" + groupIndex1;
            }
        }
        return video_id;
    }

    public int getMapIcon(String type) {
        switch (type) {
            case "Réstaurant":
                return R.drawable.icon_map_restorant;
            case "magic places":
                return R.drawable.icon_map_happy_hour;
            case "Discos":
                return R.drawable.icon_map_parties;
            case "Caffées":
                return R.drawable.icon_map_coffe;
            case "Cinémas":
                return R.drawable.icon_map_cinema;
            case "Centres":
                return R.drawable.icon_map_mind;
            case "Art":
                return R.drawable.icon_map_cinema;
        }
        return R.drawable.icon_map_coffe;
    }


}
