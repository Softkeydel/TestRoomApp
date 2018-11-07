package com.example.ali.roomapplication.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by satyabrata on 9/5/18.
 */

public class DateFormatter {

    public static String getDate(String ds){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.format(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").parse(ds));
        } catch (ParseException e) {
            e.printStackTrace();
            return ds;
        }

    }
}
