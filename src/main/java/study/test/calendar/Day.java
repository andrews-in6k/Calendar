package study.test.calendar;

import java.time.*;

/**
 * Created by employee on 10/12/15.
 */
public class Day {
    private int dayOfMonth;
    private DayOfWeek dayOfWeek;
    private int monthValue;

    Day(int dayOfMonth, DayOfWeek dayOfWeek, int monthValue){
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
        this.monthValue = monthValue;
    }

    public boolean isWeekend(){
        return dayOfWeek.equals(DayOfWeek.SUNDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY);
    }

    public boolean isInMonth(int monthValue){
        return this.monthValue == monthValue;
    }

    public boolean isDayEqual(int monthValue, int dayOfMonth){
        return (this.monthValue == monthValue) && (this.dayOfMonth == dayOfMonth);
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(int monthValue) {
        this.monthValue = monthValue;
    }
}
