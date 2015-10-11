package study.test.calendar;

import java.time.LocalDate;

/**
 * Created by anri on 09.10.15.
 */
public interface CalendarPrinter {
    public void printMonthAndYear(LocalDate date);

    public void printShortWeekDayName(String weekdayName, PrintFormat format);

    public void printDayNumber(int dayNumber, PrintFormat format) ;

    public void setPrintFormat(PrintFormat format);

    public void printNewLine();

    public void endPrint();
}
