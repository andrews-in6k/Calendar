package study.test.calendar;

import java.time.LocalDate;

public class Run {

    private static LocalDate date = LocalDate.now();

    public static void main(String[] args) {
        CalendarMonth calendarMonth = new CalendarMonth(date);

        Printer printer = new ANSIPrinter(date);

        printer.beginPrint();
        printer.printCalendar(calendarMonth);
        printer.endPrint();
    }

}
