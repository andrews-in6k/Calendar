package study.test.calendar;

import java.time.*;

/**
 * Created by employee on 10/12/15.
 */
public class Day {
    private final LocalDate date;

    public Day(LocalDate date) {
        this.date = date;
    }

    public boolean isWeekend(){
        return getDayOfWeek().equals(DayOfWeek.SUNDAY) || getDayOfWeek().equals(DayOfWeek.SATURDAY);
    }

    public boolean isInMonth(Month month){
        return getMonth() == month;
    }

    public boolean isSameDate(LocalDate date) {
        return this.date.equals(date);
    }

    public DayOfWeek getDayOfWeek() {
        return date.getDayOfWeek();
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }

    public Month getMonth() {
        return date.getMonth();
    }
}
