package study.test.calendar;

import java.time.LocalDate;

/**
 * Created by anri on 09.10.15.
 */
public interface CalendarPrinter {
    void printMonthAndYear(LocalDate date);

    void printShortWeekDayName(String weekdayName, PrintFormat format);

    void printDayNumber(int dayNumber, PrintFormat format) ;

    void setPrintFormat(PrintFormat format);

    void printNewLine();

    void endPrint();
}
