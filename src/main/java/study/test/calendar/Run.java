package study.test.calendar;

import java.io.PrintStream;
import java.time.LocalDate;

public class Run {

    private static LocalDate currentDate = LocalDate.now();

    public static void main(String[] args) {
        CalendarMonth calendarMonth = new CalendarMonth(currentDate);

        PrintStream printStream = new PrintStream(System.out);

        Printer printer = new ANSIPrinter(currentDate, printStream);

        printer.beginPrint();
        printer.printCalendar(calendarMonth);
        printer.endPrint();

        printStream.close();
    }

}
