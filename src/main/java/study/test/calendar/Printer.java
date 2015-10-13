package study.test.calendar;

import java.time.LocalDate;

/**
 * Created by employee on 10/12/15.
 */
public abstract class Printer {
    protected LocalDate currentDate;

    protected String resultText = "";

    protected ColorFormat colorFormat;

    CalendarMonth calendarMonth;

    String textFormat;
    String accentuationFormat;

    abstract void beginPrint();

    public void printCalendar(CalendarMonth calendarMonth) {
        this.calendarMonth = calendarMonth;
        
        printMonthAndYear();
        printWeekdayNames();
        printDayNumbers();
    }

    abstract void printMonthAndYear();

    protected void printWeekdayNames() {
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

    protected void printDayNumbers() {
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
