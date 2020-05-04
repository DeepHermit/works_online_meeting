package com.deephermit.online_meeting.util;

import com.deephermit.online_meeting.model.CountDown;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
        public static String getDate(){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(new Date());
        }
        public static String getyMdHmDate(){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.format(new Date());
        }
        public static CountDown TimeDifference(String meetingId,String orderTime) {
            Long now = new Date().getTime();
            Date orderdate;
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                 orderdate = sdf.parse(orderTime);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
            Long orderLong =  orderdate.getTime();
            if(now>=orderLong){
                return null;
            }else{
                long between = orderLong - now;
                long day = between / (24 * 60 * 60 * 1000);
                long hour = (between / (60 * 60 * 1000) - day * 24);
                long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
                long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
                return new CountDown(meetingId,String.valueOf(day),String.valueOf(hour),String.valueOf(min),String.valueOf(s));
            }
        }
        public static String getDate(java.sql.Date date){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return date.toString();
        }
        public static String getDateNumber(){
            return String.valueOf(System.currentTimeMillis()+15*86400000);
        }
        public static String getDateAfter15() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(new Date(System.currentTimeMillis() + 15 * 86400000));
        }
//        public static void main(String[] args){
//            System.out.print(new Date());
//            System.out.print(new Date().getTime());
//            CountDown countDown = TimeDifference("0123","2020-04-28 13:00");
//            if(countDown==null){
//                System.out.print("false");
//            }else{
//                System.out.print(countDown.toString());
//            }
//        }
}
