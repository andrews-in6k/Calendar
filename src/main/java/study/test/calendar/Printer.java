package study.test.calendar;

import java.time.LocalDate;

/**
 * Created by employee on 10/12/15.
 */
public abstract class Printer {
    protected LocalDate currentDate;

    protected String resultText = "";

    protected ColorFormat colorFormat;

    String textFormat;
    String accentuationFormat;

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
                textFormat = colorFormat.getHolidayFormat();
            } else {
                textFormat = colorFormat.getDefaultFormat();
            }

            printWeekdayName(day);
        }

        printLine();
    }

    protected void printDayNumbers(CalendarMonth calendarMonth) {
        for (Week week : calendarMonth.getWeekList()) {
            for (Day day : week.getDayList()) {
                setDefaultColorFormat();

                if (day.isDayEqual(currentDate.getMonthValue(), currentDate.getDayOfMonth())) {
                    accentuationFormat = colorFormat.getCurrentDayAccentuationFormat();
                }

                if (day.isWeekend()) {
                    textFormat = colorFormat.getHolidayFormat();
                }

                if (!day.isInMonth(calendarMonth.getMonthValue())) {
                    textFormat = colorFormat.getFromOtherMonthFormat();
                }

                printDayNumber(day);
            }

            printLine();
        }
    }

    protected void setDefaultColorFormat(){
        textFormat = colorFormat.getDefaultFormat();
        accentuationFormat = colorFormat.getDefaultAccentuationFormat();
    }

    abstract void printWeekdayName(Day day);

    abstract void printDayNumber(Day day);

    abstract void printLine();

    abstract void endPrint();
}
