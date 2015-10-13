package study.test.calendar;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by employee on 10/12/15.
 */
public class Week {
    public static final DayOfWeek FIRST_DAY_OF_WEEK = DayOfWeek.MONDAY;
    public static final int MAX_WEEK_DAYS = 7;

    private List<Day> dayList = new ArrayList<Day>();

    private int weekValue;

    Week(int weekValue, LocalDate date){
        this.weekValue = weekValue;

        fillDayList(date);
    }

    private void fillDayList(LocalDate date){
        for(int i = 0; i < MAX_WEEK_DAYS; i++){
            dayList.add(new Day(date.getDayOfMonth(), date.getDayOfWeek(), date.getMonthValue()));
            date = date.plusDays(1);
        }
    }

    public List<Day> getDayList() {
        return dayList;
    }

    public int getWeekValue() {
        return weekValue;
    }
}
