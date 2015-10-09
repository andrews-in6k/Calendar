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

    private CalendarPrinter calendarPrinter;
    private LocalDate sourceDate = LocalDate.now();
    private LocalDate dateForOutput;

    MonthCalendar(CalendarPrinter calendarPrinter, LocalDate date) {
        this.calendarPrinter = calendarPrinter;
        this.sourceDate = date;
    }

    public void printCalendar() {
        calendarPrinter.printMonthAndYear(sourceDate);
        printWeekDaysNames();
        printDayNumbers();
    }

    private void printWeekDaysNames() {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            String displayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.CANADA);
            if (isWeekend(dayOfWeek)) {
                calendarPrinter.printShortWeekDayName(displayName, SET_HOLIDAY_COLOR);
            } else {
                calendarPrinter.printShortWeekDayName(displayName, SET_DEFAULT_COLOR);
            }
        }
        calendarPrinter.printNewLine();
    }

    private void printDayNumbers() {
        LocalDate date = initDateForOutput();
        for (int dayIndex = 0; isNotNextMonthFirstWeekEnd(date); dayIndex++, date = date.plusDays(1)) {
            if (isPrevMonth(date)) {
                calendarPrinter.printDayNumber(date.getDayOfMonth(), SET_DAY_FROM_OTHER_MONTH_COLOR);
            }
            if (isCurrentMonth(date)) {
                printCurrentMonthDayNumbers(date, calendarPrinter);
            }
            if (isNextMonth(date)) {
                calendarPrinter.printDayNumber(date.getDayOfMonth(), SET_DAY_FROM_OTHER_MONTH_COLOR);
            }
            if (isEndOfWeek(date)) {
                calendarPrinter.printNewLine();
            }
        }
    }

    private LocalDate initDateForOutput() {
        int sourceDateShiftRelativeToDayOfMonth = sourceDate.getDayOfMonth() - 1 + calculateSourceDateShift();
        dateForOutput = sourceDate.minusDays(sourceDateShiftRelativeToDayOfMonth);
        return LocalDate.from(dateForOutput);
    }

    private boolean isWeekend(DayOfWeek dayOfWeekName) {
        return dayOfWeekName.equals(DayOfWeek.SUNDAY) || dayOfWeekName.equals(DayOfWeek.SATURDAY);
    }

    private boolean isHolidayNumber(LocalDate date) {
        return isWeekend(date.getDayOfWeek());
    }

    private boolean isPrevMonth(LocalDate date) {
        return date.getMonthValue() == sourceDate.minusMonths(1).getMonthValue();
    }

    private boolean isNextMonth(LocalDate date) {
        return date.getMonthValue() == sourceDate.plusMonths(1).getMonthValue();
    }

    private boolean isCurrentMonth(LocalDate date) {
        return !(isPrevMonth(date)) && !(isNextMonth(date));
    }

    private boolean isNotNextMonthFirstWeekEnd(LocalDate date) {
        return !((date.getMonthValue() == sourceDate.plusMonths(1).getMonthValue()) &&
                date.getDayOfWeek().equals(DayOfWeek.MONDAY));
    }

    private boolean isEndOfWeek(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    private boolean isToday(int monthDay) {
        return monthDay == sourceDate.getDayOfMonth();
    }

    private void printCurrentMonthDayNumbers(LocalDate date,CalendarPrinter calendarPrinter) {
        if (isToday(date.getDayOfMonth())) {
            calendarPrinter.setPrintFormat(SET_CURRENT_DAY_ACCENTUATION_COLOR);
        }
        if (isHolidayNumber(date)) {
            calendarPrinter.printDayNumber(date.getDayOfMonth(), SET_HOLIDAY_COLOR);
        } else {
            calendarPrinter.printDayNumber(date.getDayOfMonth(), SET_DEFAULT_COLOR);
        }
    }

    private int calculateSourceDateShift() {
        int sourceDateShift = getFirstMonthWeekdayId();
        if ((sourceDateShift >= 0) || (causeToWeekdayId(sourceDateShift) == 0)) {
            sourceDateShift = causeToWeekdayId(sourceDateShift);
        } else {
            sourceDateShift = MAX_WEEK_DAYS - Math.abs(causeToWeekdayId(sourceDateShift));
        }
        return sourceDateShift;
    }

    private int causeToWeekdayId(int increasedWeekdayIndex) {
        return increasedWeekdayIndex % MAX_WEEK_DAYS;
    }

    private int getFirstMonthWeekdayId() {
        return sourceDate.minusDays(sourceDate.getDayOfMonth() - 1).getDayOfWeek().getValue() - 1;
    }

}
