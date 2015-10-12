package study.test.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 10/8/15.
 */
public class MonthCalendar {

    public static final int MAX_WEEK_DAYS = 7;
    public static final DayOfWeek FIRST_DAY_OF_WEEK = DayOfWeek.MONDAY;

    private LocalDate sourceDate = LocalDate.now();
    private LocalDate dateForOutput;

    private CalendarPrinter calendarPrinter;

    private PrintFormat printFormat = new PrintFormat();

    MonthCalendar(CalendarPrinter calendarPrinter, LocalDate date) {
        this.calendarPrinter = calendarPrinter;
        this.sourceDate = date;
    }

    public void printCalendar() {
        initDateForOutput();
        calendarPrinter.startPrint();
        calendarPrinter.printMonthAndYear(sourceDate);
        printWeekDaysNames();
        printDayNumbers();
        calendarPrinter.endPrint();
    }

    private void initDateForOutput() {
        int sourceDateShiftRelativeToDayOfMonth = sourceDate.getDayOfMonth() - 1 + calculateWeekdayShift();
        dateForOutput = sourceDate.minusDays(sourceDateShiftRelativeToDayOfMonth);
    }

    private int calculateWeekdayShift() {
        int weekdayShift = FIRST_DAY_OF_WEEK.getValue() - getFirstMonthWeekdayId() - 1;
        if (weekdayShift <= 0) {
            weekdayShift = Math.abs(weekdayShift);
        } else {
            weekdayShift = MAX_WEEK_DAYS - weekdayShift;
        }
        return weekdayShift;
    }

    private void printWeekDaysNames() {
        LocalDate date = dateForOutput;
        do {
            String displayWeekdayName = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CANADA);

            if (isWeekend(date.getDayOfWeek()))
                printFormat.setFormatAsHoliday();
            else
                printFormat.setFormatAsDefault();

            calendarPrinter.printShortWeekDayName(displayWeekdayName, printFormat);

            date = date.plusDays(1);
        } while (!date.getDayOfWeek().equals(FIRST_DAY_OF_WEEK));
        calendarPrinter.printNewLine();
    }

    private void printDayNumbers() {
        for (; !isFirstWeekendOfNextMonth(); dateForOutput = dateForOutput.plusDays(1)) {

            if (isPrevMonth())
                printFormat.setFormatAsDayFromOtherMonth();

            if (isCurrentMonth())
                setCurrentDayFormat();

            if (isNextMonth())
                printFormat.setFormatAsDayFromOtherMonth();

            calendarPrinter.printDayNumber(dateForOutput.getDayOfMonth(), printFormat);

            if (isEndOfWeek())
                calendarPrinter.printNewLine();
        }
    }

    private boolean isFirstWeekendOfNextMonth() {
        return (dateForOutput.getMonthValue() == getChangedMonthValue(1)) &&
                dateForOutput.getDayOfWeek().equals(FIRST_DAY_OF_WEEK);
    }

    private boolean isWeekend(DayOfWeek dayOfWeekName) {
        return dayOfWeekName.equals(DayOfWeek.SUNDAY) || dayOfWeekName.equals(DayOfWeek.SATURDAY);
    }

    private boolean isPrevMonth() {
        return dateForOutput.getMonthValue() == getChangedMonthValue(-1);
    }

    private boolean isNextMonth() {
        return dateForOutput.getMonthValue() == getChangedMonthValue(1);
    }

    private boolean isCurrentMonth() {
        return !(isPrevMonth()) && !(isNextMonth());
    }

    private boolean isEndOfWeek() {
        return dateForOutput.minusDays(FIRST_DAY_OF_WEEK.getValue() - 1).getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    private boolean isToday(int monthDay) {
        return monthDay == sourceDate.getDayOfMonth();
    }

    private void setCurrentDayFormat() {
        printFormat.setFormatAsDefault();

        if (isWeekend(dateForOutput.getDayOfWeek()))
            printFormat.setFormatAsHoliday();

        if (isToday(dateForOutput.getDayOfMonth()))
            printFormat.setFormatAsCurrentDay();
    }

    private int getFirstMonthWeekdayId() {
        return sourceDate.minusDays(sourceDate.getDayOfMonth() - 1).getDayOfWeek().getValue() - 1;
    }

    private int getChangedMonthValue(int change) {
        return sourceDate.plusMonths(change).getMonthValue();
    }
}
