package study.test.calendar;

import java.util.List;

/**
 * Created by employee on 10/12/15.
 */
public abstract class Printer {
    protected String resultText = "";

    abstract void beginPrint();

    public void printCalendar(CalendarMonth calendarMonth){
        printMonthAndYear(calendarMonth);
        printWeekdayNames(calendarMonth);
    }

    abstract void printMonthAndYear(CalendarMonth calendarMonth);

    protected void printWeekdayNames(CalendarMonth calendarMonth){
        for (Day day : calendarMonth.getWeekList().get(0).getDayList()){
            if(day.isWeekend()){
                printWeekdayName(day);
            }else {
                printWeekdayName(day);
            }
        }
    }

    abstract void printWeekdayName(Day day);

    abstract void endPrint();
}
