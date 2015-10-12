package study.test.calendar;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by employee on 10/12/15.
 */
public class CalendarMonth {
    private List<Week> weekList = new ArrayList<Week>();

    private Month monthName;
    private int monthValue;

    private int year;

    CalendarMonth(LocalDate date){
        this.monthName = date.getMonth();
        this.monthValue = date.getMonthValue();
        this.year = date.getYear();

        date = date.minusDays(date.getDayOfMonth() - 1);


        while(date.getDayOfWeek() != Week.FIRST_DAY_OF_WEEK){
            date = date.minusDays(1);
        }

        int weekValue = 1;
        do{
            weekList.add(new Week(weekValue, date));
            weekValue++;
            date = date.plusWeeks(1);
        }while (date.getMonth() == monthName);

    }

    public List<Week> getWeekList() {
        return weekList;
    }

    public Month getMonthName() {
        return monthName;
    }

    public int getMonthValue() {
        return monthValue;
    }

    public int getYear() {
        return year;
    }

}
