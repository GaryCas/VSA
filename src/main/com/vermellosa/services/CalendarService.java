package com.vermellosa.services;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by rd019985 on 13/11/2016.
 */
public class CalendarService {
    private static Calendar calendar;

    public static Date addDays(int days){
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date minusDays(int days){
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }
}
