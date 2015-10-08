package study.test.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 10/8/15.
 */
public class MonthCalendar {
    public static final String SET_HOLIDAY_COLOR = "\u001b[31m";
    public static final String SET_DAY_FROM_OTHER_MONTH_COLOR = "\u001b[34m";
    public static final String SET_DEFAULT_COLOR = "\u001b[0m";
    public static final String SET_CURRENT_DAY_ACCENTUATION_COLOR = "\u001b[42;30m";

    public static final int MAX_WEEK_DAYS = 7;
    public static final int WEEK_DAYS_SHIFT = 0;

    private LocalDate sourceDate = LocalDate.now();
    private LocalDate dateForOutput;

    MonthCalendar(LocalDate date) {
        this.sourceDate = date;
    }

    public void printCalendar() {
        printMonthAndYear();
        printWeekDaysNames();
        printDayNumbers();
    }

    private void printMonthAndYear() {
        System.out.println(sourceDate.getMonth() + " " + sourceDate.getYear());
    }

    private void printWeekDaysNames() {
        for (int weekDayIndex = 0; weekDayIndex < MAX_WEEK_DAYS; weekDayIndex++) {
            if (isHolidayName(weekDayIndex)) {
                printShortWeekDaysName(weekDayIndex, SET_HOLIDAY_COLOR);
            } else {
                printShortWeekDaysName(weekDayIndex, SET_DEFAULT_COLOR);
            }
        }
        System.out.println();
    }

    private void printDayNumbers() {
        initDateForOutput();
        for (int dayIndex = 0; isNotNextMonthFirstWeekEnd(dayIndex); dayIndex++) {
            if (isEndOfWeek(dayIndex)) {
                System.out.println();
            }
            if (isPrevMonth(dayIndex)) {
                printDayNumber(getCurrentDayNumber(dayIndex).getDayOfMonth(), SET_DAY_FROM_OTHER_MONTH_COLOR);
            }
            if (isCurrentMonth(dayIndex)) {
                printCurrentMonthDayNumbers(dayIndex);
            }
            if (isNextMonth(dayIndex)) {
                printDayNumber(getCurrentDayNumber(dayIndex).getDayOfMonth(), SET_DAY_FROM_OTHER_MONTH_COLOR);
            }
        }
    }

    private void printDayNumber(int dayNumber, String setFormat) {
        System.out.print(setFormat);
        System.out.format("%3d ", dayNumber);
        resetPrintFormat();
    }

    private void printShortWeekDaysName(int weekdayIndex, String format) {
        System.out.print(format + getWeekdayName(weekdayIndex).getDisplayName(TextStyle.SHORT, Locale.CANADA) + " ");
        resetPrintFormat();
    }

    private void initDateForOutput() {
        int sourceDateShiftRelativeToDayOfMonth = sourceDate.getDayOfMonth() - 1 + calculateSourceDateShift();
        dateForOutput = sourceDate.minusDays(sourceDateShiftRelativeToDayOfMonth);
    }

    private boolean isWeekend(DayOfWeek dayOfWeekName) {
        return dayOfWeekName.equals(DayOfWeek.SUNDAY) || dayOfWeekName.equals(DayOfWeek.SATURDAY);
    }

    private boolean isHolidayName(int weekdayIndex) {
        return isWeekend(getWeekdayName(weekdayIndex));
    }

    private boolean isHolidayNumber(int dayIndex) {
        return isWeekend(getCurrentDayNumber(dayIndex).getDayOfWeek());
    }

    private boolean isPrevMonth(int dayIndex) {
        return getCurrentDayNumber(dayIndex).getMonthValue() == sourceDate.minusMonths(1).getMonthValue();
    }

    private boolean isNextMonth(int dayIndex) {
        return getCurrentDayNumber(dayIndex).getMonthValue() == sourceDate.plusMonths(1).getMonthValue();
    }

    private boolean isCurrentMonth(int dayIndex) {
        return !(isPrevMonth(dayIndex)) && !(isNextMonth(dayIndex));
    }

    private boolean isNotNextMonthFirstWeekEnd(int dayIndex) {
        return !((getCurrentDayNumber(dayIndex).getMonthValue() == sourceDate.plusMonths(1).getMonthValue()) &&
                ((causeToWeekdayId(dayIndex)) == 0));
    }

    private boolean isEndOfWeek(int dayIndex) {
        return ((causeToWeekdayId(dayIndex)) == 0) && (dayIndex != 0);
    }

    private boolean isToday(int monthDay) {
        return monthDay == sourceDate.getDayOfMonth();
    }

    private void printCurrentMonthDayNumbers(int dayIndex) {
        if (isToday(getCurrentDayNumber(dayIndex).getDayOfMonth())) {
            setPrintFormat(SET_CURRENT_DAY_ACCENTUATION_COLOR);
        }
        if (isHolidayNumber(dayIndex)) {
            printDayNumber(getCurrentDayNumber(dayIndex).getDayOfMonth(), SET_HOLIDAY_COLOR);
        } else {
            printDayNumber(getCurrentDayNumber(dayIndex).getDayOfMonth(), SET_DEFAULT_COLOR);
        }
    }

    private int calculateSourceDateShift() {
        int sourceDateShift = getFirstMonthWeekdayId() + WEEK_DAYS_SHIFT;
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

    private LocalDate getCurrentDayNumber(int dayIndex) {
        return dateForOutput.plusDays(dayIndex);
    }

    private DayOfWeek getWeekdayName(int indexNumber) {
        return sourceDate.minusDays((sourceDate.getDayOfWeek().getValue()) - 1 - indexNumber + WEEK_DAYS_SHIFT).getDayOfWeek();
    }

    private void setPrintFormat(String format) {
        System.out.print(format);
    }

    private void resetPrintFormat() {
        System.out.print(SET_DEFAULT_COLOR);
    }
}
