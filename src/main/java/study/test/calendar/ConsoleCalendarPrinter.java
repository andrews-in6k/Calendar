package study.test.calendar;

import java.time.LocalDate;

/**
 * Created by anri on 08.10.15.
 */
public class ConsoleCalendarPrinter implements CalendarPrinter{
    private String defaultFormat = "\u001b[0m";

    ConsoleCalendarPrinter(String defaultFormat){
        this.defaultFormat = defaultFormat;
    }

    public void printMonthAndYear(LocalDate date) {
        System.out.println(date.getMonth() + " " + date.getYear());
    }

    public void printShortWeekDayName(String weekdayName, PrintFormat format) {
        System.out.print(format.getCurrentANSIFormat() +weekdayName + " ");
        resetPrintFormat();
    }

    public void printDayNumber(int dayNumber, PrintFormat format) {
        System.out.print(format.getCurrentANSIFormat());
        System.out.format("%3d ", dayNumber);
        resetPrintFormat();
    }

    public void setPrintFormat(PrintFormat format) {
        System.out.print(format.getCurrentANSIFormat());
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
