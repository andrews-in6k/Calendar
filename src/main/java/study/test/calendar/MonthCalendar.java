package study.test.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 10/8/15.
 */
public class MonthCalendar {

    public static final String[] SET_HOLIDAY_COLOR = {"\u001b[31m", "red"};
    public static final String[] SET_DAY_FROM_OTHER_MONTH_COLOR = {"\u001b[34m", "blue"};
    public static final String[] SET_DEFAULT_COLOR = {"\u001b[0m", "black"};
    public static final String[] SET_CURRENT_DAY_ACCENTUATION_COLOR = {"\u001b[42;30m","LawnGreen"};

    public static final int MAX_WEEK_DAYS = 7;
    public static final DayOfWeek FIRST_WEEK_DAY_NAME = DayOfWeek.MONDAY;

    private CalendarPrinter calendarPrinter;
    private LocalDate sourceDate = LocalDate.now();
    private LocalDate dateForOutput;

    MonthCalendar(CalendarPrinter calendarPrinter, LocalDate date) {
        this.calendarPrinter = calendarPrinter;
        this.sourceDate = date;
    }

    public void printCalendar() {
        initDateForOutput();
        calendarPrinter.printMonthAndYear(sourceDate);
        printWeekDaysNames();
        printDayNumbers();
        calendarPrinter.endPrint();
    }

    private void printWeekDaysNames() {
        LocalDate date = dateForOutput;
        for (int i = 0; i < MAX_WEEK_DAYS ; i++, date = dateForOutput.plusDays(i)) {
            String displayWeekdayName = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CANADA);
            if (isWeekend(date.getDayOfWeek())) {
                calendarPrinter.printShortWeekDayName(displayWeekdayName, SET_HOLIDAY_COLOR);
            } else {
                calendarPrinter.printShortWeekDayName(displayWeekdayName, SET_DEFAULT_COLOR);
            }
        }
        calendarPrinter.printNewLine();
    }

    private void printDayNumbers() {
        for (; isNotNextMonthFirstWeekEnd(); dateForOutput = dateForOutput.plusDays(1)) {
            if (isPrevMonth()) {
                calendarPrinter.printDayNumber(dateForOutput.getDayOfMonth(), SET_DAY_FROM_OTHER_MONTH_COLOR);
            }
            if (isCurrentMonth()) {
                printCurrentMonthDayNumbers();
            }
            if (isNextMonth()) {
                calendarPrinter.printDayNumber(dateForOutput.getDayOfMonth(), SET_DAY_FROM_OTHER_MONTH_COLOR);
            }
            if (isEndOfWeek()) {
                calendarPrinter.printNewLine();
            }
        }
    }

    private void initDateForOutput() {
        int sourceDateShiftRelativeToDayOfMonth = sourceDate.getDayOfMonth() - 1 + getWeekdayShift();
        dateForOutput = sourceDate.minusDays(sourceDateShiftRelativeToDayOfMonth);
    }

    private boolean isWeekend(DayOfWeek dayOfWeekName) {
        return dayOfWeekName.equals(DayOfWeek.SUNDAY) || dayOfWeekName.equals(DayOfWeek.SATURDAY);
    }

    private boolean isHolidayNumber() {
        return isWeekend(dateForOutput.getDayOfWeek());
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

    private boolean isNotNextMonthFirstWeekEnd() {
        return !((dateForOutput.getMonthValue() == getChangedMonthValue(1)) &&
                dateForOutput.getDayOfWeek().equals(FIRST_WEEK_DAY_NAME));
    }

    private boolean isEndOfWeek() {
        return dateForOutput.minusDays(FIRST_WEEK_DAY_NAME.getValue() - 1).getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    private boolean isToday(int monthDay) {
        return monthDay == sourceDate.getDayOfMonth();
    }

    private void printCurrentMonthDayNumbers() {
        if (isToday(dateForOutput.getDayOfMonth())) {
            calendarPrinter.setPrintFormat(SET_CURRENT_DAY_ACCENTUATION_COLOR);
        }
        if (isHolidayNumber()) {
            calendarPrinter.printDayNumber(dateForOutput.getDayOfMonth(), SET_HOLIDAY_COLOR);
        } else {
            calendarPrinter.printDayNumber(dateForOutput.getDayOfMonth(), SET_DEFAULT_COLOR);
        }
    }

    private int getFirstMonthWeekdayId() {
        return sourceDate.minusDays(sourceDate.getDayOfMonth() - 1).getDayOfWeek().getValue() - 1;
    }

    private int getWeekdayShift(){
        int weekdayShift = FIRST_WEEK_DAY_NAME.getValue() - getFirstMonthWeekdayId()-1;
        if (weekdayShift <= 0){
            weekdayShift = Math.abs(weekdayShift);
        }else{
            weekdayShift = MAX_WEEK_DAYS - weekdayShift;
        }
        return weekdayShift;
    }

    private int getChangedMonthValue(int change) {
        return sourceDate.plusMonths(change).getMonthValue();
    }
}
