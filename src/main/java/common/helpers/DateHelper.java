package common.helpers;


import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

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
}
