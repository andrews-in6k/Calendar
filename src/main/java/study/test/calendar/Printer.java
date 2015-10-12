package study.test.calendar;

import java.time.LocalDate;

/**
 * Created by employee on 10/12/15.
 */
public abstract class Printer {
    protected LocalDate currentDate;

    protected String resultText = "";

    protected ColorFormat colorFormat;

    abstract void beginPrint();

    public void printCalendar(CalendarMonth calendarMonth) {
        printMonthAndYear(calendarMonth);
        printWeekdayNames(calendarMonth);
        printDayNumbers(calendarMonth);
    }

    abstract void printMonthAndYear(CalendarMonth calendarMonth);

    protected void printWeekdayNames(CalendarMonth calendarMonth) {
        for (Day day : calendarMonth.getWeekList().get(0).getDayList()) {
            if (day.isWeekend()) {
                printWeekdayName(day, colorFormat.getHolidayFormat());
            } else {
                printWeekdayName(day, colorFormat.getDefaultFormat());
            }
        }

        printLine();
    }

    protected void printDayNumbers(CalendarMonth calendarMonth) {
        for (Week week : calendarMonth.getWeekList()) {
            for (Day day : week.getDayList()) {
                String format = colorFormat.getDefaultFormat();
                String accentuationFormat = colorFormat.getDefaultAccentuationFormat();

                if (day.isDayEqual(currentDate.getMonthValue(), currentDate.getDayOfMonth())) {
                    accentuationFormat = colorFormat.getCurrentDayAccentuationFormat();
                }

                if (day.isWeekend()) {
                    format = colorFormat.getHolidayFormat();
                }

                if (!day.isInMonth(calendarMonth.getMonthValue())) {
                    format = colorFormat.getFromOtherMonthFormat();
                }

                printDayNumber(day, format, accentuationFormat);
            }

            printLine();
        }
    }

    abstract void printWeekdayName(Day day, String format);

    abstract void printDayNumber(Day day, String format, String accentuationFormat);

    abstract void printLine();

    abstract void endPrint();
}
