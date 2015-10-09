package study.test.calendar;

import java.time.LocalDate;

/**
 * Created by anri on 08.10.15.
 */
public class ConsoleCalendarPrinter implements CalendarPrinter{
    private String defaultFormat = "\u001b[0m";

    ConsoleCalendarPrinter(String[] defaultFormat){
        this.defaultFormat = defaultFormat[0];
    }

    public void printMonthAndYear(LocalDate date) {
        System.out.println(date.getMonth() + " " + date.getYear());
    }

    public void printShortWeekDayName(String weekdayName, String[] format) {
        System.out.print(format[0] +weekdayName + " ");
        resetPrintFormat();
    }

    public void printDayNumber(int dayNumber, String[] format) {
        System.out.print(format[0]);
        System.out.format("%3d ", dayNumber);
        resetPrintFormat();
    }

    public void setPrintFormat(String[] format) {
        System.out.print(format[0]);
    }

    public void printNewLine(){
        System.out.println();
    }

    private void resetPrintFormat() {
        System.out.print(defaultFormat);
    }

    public void endPrint(){
        System.out.println("___________________________");
    }
}
