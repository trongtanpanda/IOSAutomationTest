package common.helpers;


import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateHelper {
    public static int monthToNumber(String month) {
        return Month.valueOf(month.toUpperCase()).getValue();
    }

    public static int getMonthOfDate(Date date){
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }
    public static int getYearOfDate(Date date){
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getYear();
    }
    public static int getDayOfDate(Date date){
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth();
    }
    public static long distanceBetweenTwoDays(Date start, Date end){
        LocalDate startDate = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Math.abs(ChronoUnit.DAYS.between(startDate,endDate));
    }
}