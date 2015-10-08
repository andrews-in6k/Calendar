package study.test.calendar;

import java.time.LocalDate;

/**
 * Created by anri on 09.10.15.
 */
public interface CalendarPrinter {
    public void printMonthAndYear(LocalDate date);

    public void printShortWeekDaysName(String weekdayName, String[] format);

    public void printDayNumber(int dayNumber, String[] format) ;

    public void setPrintFormat(String[] format);
}
