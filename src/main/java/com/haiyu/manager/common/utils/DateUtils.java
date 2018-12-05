package com.haiyu.manager.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title: DateUtils
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/5/26 9:58
 */
public class DateUtils {

    /**
     *
     * 功能描述:
     *
     * @param: 获取当前系统时间 yyyy-MM-dd HH:mm:ss
     * @return:
     * @auther: youqing
     * @date: 2018/5/26 9:59
     */
    public static String getCurrentDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(System.currentTimeMillis());
        return date;
    }


    /**
     *
     * 功能描述: 
     *
     * @param: date类 获取当前系统时间 yyyy-MM-dd HH:mm:ss
     * @return: 
     * @auther: youqing
     * @date: 2018/5/26 10:39
     */
    public static Date getCurrentDateToDate () {
        DateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        String date = df.format(System.currentTimeMillis());
        Date d = null;
        try {
            d = df.parse( date.toString( ) );
        } catch ( ParseException e ) {
            e.printStackTrace( );
        }
        return d;
    }

    /**
     * 增加时间单位：天
     * @param day
     * @return
     */
    public static String getCurrentAddDay(int day) {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, day);
        return sdf.format(cal.getTime());
    }

    /**
     * 增加时间单位：分钟
     * @param minute
     * @return
     */
    public static String getCurrentAddMin(int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, minute);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowDateString (  ) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
        return sdf.format( d );
    }

    /**
     * 把Date转为String
     * @param date
     * @param format
     * @return
     */
    public static String getFormatTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 增加时间单位：天
     * @param day
     * @return
     */
    public static Date addDay(int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 增加时间单位：天
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 减去多少天
     * @param date
     * @param day
     * @return
     */
    public static Date minusDay(Date date, int day) {
        return addDay(date, -day);
    }

    public static void main(String[] args) {
        System.out.println(getCurrentAddDay(2));
    }
}
