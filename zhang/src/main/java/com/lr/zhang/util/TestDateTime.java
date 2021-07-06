package com.lr.zhang.util;

import org.joda.time.DateTime;

import java.util.Date;

public class TestDateTime {

    public static void main(String[] args) {

        // DateTime的主要目的是替换JDK中的Calendar类，用来处理那些时区信息比较重要的场景。
        DateTime dateTime1 = new DateTime();
        System.out.println(dateTime1.parse("yyyy-MM-dd")); //out : 2021-06-22T11:36:18.792+08:00
        DateTime dateTime2 = new DateTime(new Date());
        System.out.println(dateTime2); //out : 2021-06-22T11:36:18.862+08:00
        DateTime dateTime3 = new DateTime("2021-06-22").toDateTime();
        System.out.println(dateTime3); //out : 2021-06-22T00:00:00.000+08:00

        int year = dateTime3.getYear();
        int month = dateTime3.getMonthOfYear();
        int day = dateTime3.getDayOfMonth();
        System.out.println(year + " == " + month + " == " + day ); //out : 2021 == 6 == 22




    }
}
