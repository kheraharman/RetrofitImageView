package com.test.mindvalley.ImageLoading;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
    public static String previous_day() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -1);
        Date sevendaysBeforeDate = cal.getTime();
        String previous_day = format.format(sevendaysBeforeDate);
        return previous_day;
    }

    public static String date_in_hh_mm_a(String date) {

        try {
            SimpleDateFormat spf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
            Date newDate = spf.parse(date);
            spf = new SimpleDateFormat("hh:mm a");
            date = spf.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static long[] current_date_working_hours(String from_time, String to_time) {
        long hours = 0, minutes = 0;
        Date sd, ed;
        long[] result=new long[2];

        try {

            SimpleDateFormat spf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

            sd = spf.parse(from_time);
            ed = spf.parse(to_time);


            long diff = ed.getTime() - sd.getTime();
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = diff / daysInMilli;
            diff = diff % daysInMilli;

            hours = diff / hoursInMilli;
            diff = diff % hoursInMilli;

            minutes = diff / minutesInMilli;
            diff = diff % minutesInMilli;
            long elapsedSeconds = diff / secondsInMilli;

            result[0]=hours;
            result[1]=minutes;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String current_date_in_MMddyyyyhhmmssa() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String curr_date = df.format(c);
        return curr_date;
    }

    public static long two_months_before() {
        long startDate = 12345678910L;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        String final_date = format.format(date);

        try {
            Date date1 = format.parse(final_date);
            startDate = date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }
    public static String one_week_before() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -7);
        Date sevendaysBeforeDate = cal.getTime();
        String one_week_before = format.format(sevendaysBeforeDate);

        return one_week_before;
    }
    public static String current_date()
    {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String curr_date = df.format(c);
        return curr_date;
    }

}