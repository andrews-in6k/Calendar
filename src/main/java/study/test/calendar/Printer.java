package study.test.calendar;

import java.io.PrintStream;
import java.time.LocalDate;

/**
 * Created by employee on 10/12/15.
 */
public abstract class Printer {
    protected LocalDate currentDate;

    protected ColorFormat colorFormat;

    protected PrintStream output;

    CalendarMonth calendarMonth;

    String textFormat;
    String backgroundFormat;

    Printer(LocalDate currentDate, PrintStream output){
        this.output = output;
        this.currentDate = currentDate;
    }

    abstract void beginPrint();

    public void printCalendar(CalendarMonth calendarMonth) {
        this.calendarMonth = calendarMonth;
        
        printMonthAndYear();
        printWeekdayNames();
        printDayNumbers();
    }

    abstract void printMonthAndYear();

    protected void printWeekdayNames() {
        beginWeek();

        for (Day day : calendarMonth.getWeekList().get(0).getDayList()) {
            if (day.isWeekend()) {
                textFormat = colorFormat.getHolidayFormat();
            } else {
                textFormat = colorFormat.getDefaultFormat();
            }

            printWeekdayName(day);
        }

        endWeek();
    }

    protected void printDayNumbers() {
        for (Week week : calendarMonth.getWeekList()) {
            beginWeek();

            for (Day day : week.getDayList()) {
                setDefaultColorFormat();

                if (day.isSameDate(currentDate)) {
                    backgroundFormat = colorFormat.getCurrentDayBackgroundFormat();
                }

                if (day.isWeekend()) {
                    textFormat = colorFormat.getHolidayFormat();
                }

                if (!day.isInMonth(calendarMonth.getMonth())) {
                    textFormat = colorFormat.getFromOtherMonthFormat();
                }

                printDayNumber(day);
            }

            endWeek();
        }
    }

    protected void setDefaultColorFormat(){
        textFormat = colorFormat.getDefaultFormat();
        backgroundFormat = colorFormat.getDefaultBackgroundFormat();
    }

    abstract void printWeekdayName(Day day);

    abstract void printDayNumber(Day day);

    abstract void beginWeek();

    abstract void endWeek();

    abstract void endPrint();
}
