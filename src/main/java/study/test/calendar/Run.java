package study.test.calendar;

import java.time.LocalDate;

public class Run {

    private static LocalDate date = LocalDate.now();

    public static void main(String[] args) {
        MonthCalendar calendar = new MonthCalendar(date);
        calendar.printCalendar();
    }


}
