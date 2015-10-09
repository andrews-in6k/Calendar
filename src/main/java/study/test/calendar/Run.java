package study.test.calendar;

import java.time.LocalDate;

public class Run {

    private static LocalDate date = LocalDate.now();

    public static void main(String[] args) {
        ConsoleCalendarPrinter consoleCalendarPrinter = new ConsoleCalendarPrinter(MonthCalendar.SET_DEFAULT_COLOR);
        MonthCalendar calendar = new MonthCalendar(consoleCalendarPrinter, date);
        calendar.printCalendar();
    }

}
