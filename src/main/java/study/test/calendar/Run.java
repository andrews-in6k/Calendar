package study.test.calendar;

import java.time.LocalDate;

public class Run {

    private static LocalDate date = LocalDate.now();
    private static PrintFormat printFormat= new PrintFormat();

    public static void main(String[] args) {
//        ConsoleCalendarPrinter consoleCalendarPrinter = new ConsoleCalendarPrinter(printFormat.getAnciDefaultColor());
        HTMLCalendarPrinter htmlCalendarPrinter = new HTMLCalendarPrinter();
        MonthCalendar calendar = new MonthCalendar(htmlCalendarPrinter, date);
        calendar.printCalendar();
    }

}
