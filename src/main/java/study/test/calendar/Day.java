package study.test.calendar;

import java.time.*;

/**
 * Created by employee on 10/12/15.
 */
public class Day {
    private LocalDate dateForOutput;

    public Day(LocalDate dateForOutput) {
        this.dateForOutput = dateForOutput;
    }

    public boolean isWeekend(){
        return getDayOfWeek().equals(DayOfWeek.SUNDAY) || getDayOfWeek().equals(DayOfWeek.SATURDAY);
    }

    public boolean isInMonth(Month month){
        return getMonth() == month;
    }

    public boolean isSameDate(LocalDate date) {
        return this.dateForOutput.equals(date);
    }

    public DayOfWeek getDayOfWeek() {
        return dateForOutput.getDayOfWeek();
    }

    public int getDayOfMonth() {
        return dateForOutput.getDayOfMonth();
    }

    public Month getMonth() {
        return dateForOutput.getMonth();
    }
}
