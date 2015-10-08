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

    private LocalDate date = LocalDate.now();

    MonthCalendar(LocalDate date){
        this.date = date;
    }

    public void printCalendar(){
        printMonthAndYear();
        printWeekDaysName();
        printDayNumbers();
    }

    private void printMonthAndYear() {
        System.out.println(date.getMonth() + " " + date.getYear());
    }

    private void printWeekDaysName() {
        for (int i = 0; i < MAX_WEEK_DAYS; i++) {
            if (isHolidayName(i)) {
                System.out.print(SET_HOLIDAY_COLOR + getDayOfWeekName(i).getDisplayName(TextStyle.SHORT, Locale.CANADA) + " ");
                resetPrintFormat();
            } else {
                System.out.print(getDayOfWeekName(i).getDisplayName(TextStyle.SHORT, Locale.CANADA) + " ");
            }
        }
        System.out.println();
    }

    private void printDayNumbers() {
        int firstDayOfMonthWeekDayId = initFirstDayOfMonthWeekDayId();
        int lastDayOfMonthWeekDayId = initLastDayOfMonthWeekDayId();
        int thisMonthLength = date.lengthOfMonth();
        int prevMonthLength = date.minusMonths(1).lengthOfMonth();
        int calendarSize = thisMonthLength + firstDayOfMonthWeekDayId + (MAX_WEEK_DAYS - lastDayOfMonthWeekDayId - 1);

        for (int i = 0; i < calendarSize; i++) {
            int dayNumberRelativeToThisMonth = i - firstDayOfMonthWeekDayId + 1;

            if(isEndOfWeek(i)){
                System.out.println();
            }
            if (isPrevMonth(dayNumberRelativeToThisMonth)) {
                printDayNumber(prevMonthLength + dayNumberRelativeToThisMonth, SET_DAY_FROM_OTHER_MONTH_COLOR);
            }
            if (isCurrentMonth(dayNumberRelativeToThisMonth, thisMonthLength)) {
                printCurrentMonthDayNumbers(dayNumberRelativeToThisMonth);
            }
            if (isNextMonth(dayNumberRelativeToThisMonth, thisMonthLength)) {
                printDayNumber(dayNumberRelativeToThisMonth - thisMonthLength, SET_DAY_FROM_OTHER_MONTH_COLOR);
            }
        }
    }

    private void printDayNumber(int dayNumber, String setFormat) {
        System.out.print(setFormat);
        System.out.format("%3d ", dayNumber);
        resetPrintFormat();
    }

    private boolean isHolidayName(int weekdayNumber) {
        return isWeekend(getDayOfWeekName(weekdayNumber));
    }

    private boolean isWeekend(DayOfWeek dayOfWeekName) {
        return dayOfWeekName.equals(DayOfWeek.SUNDAY) || dayOfWeekName.equals(DayOfWeek.SATURDAY);
    }

    private boolean isHolidayNumber(int monthDay) {
        return isWeekend(getCurrentDayOfWeek(monthDay));
    }

    private boolean isPrevMonth(int checkIfThisMonth) {
        return checkIfThisMonth <= 0;
    }

    private boolean isCurrentMonth(int dayNumberRelativeToThisMonth, int thisMonthLength){
        return !(isPrevMonth(dayNumberRelativeToThisMonth)) && !(isNextMonth(dayNumberRelativeToThisMonth, thisMonthLength));
    }

    private boolean isNextMonth(int checkIfThisMonth, int thisMonthLength) {
        return checkIfThisMonth > thisMonthLength;
    }

    private boolean isEndOfWeek(int iter) {
        return ((iter % MAX_WEEK_DAYS) == 0) && (iter != 0);
    }

    private void printCurrentMonthDayNumbers(int dayNumberRelativeToThisMonth) {
        ifToday(dayNumberRelativeToThisMonth);
        if (isHolidayNumber(dayNumberRelativeToThisMonth)) {
            printDayNumber(dayNumberRelativeToThisMonth, SET_HOLIDAY_COLOR);
        } else {
            printDayNumber(dayNumberRelativeToThisMonth, SET_DEFAULT_COLOR);
        }
    }

    private void ifToday(int iter) {
        if (iter == date.getDayOfMonth()) {
            System.out.print(SET_CURRENT_DAY_ACCENTUATION_COLOR);
        }
    }

    private DayOfWeek getDayOfWeekName(int iter) {
        return date.minusDays((date.getDayOfWeek().getValue()) - 1 - iter + WEEK_DAYS_SHIFT).getDayOfWeek();
    }

    private DayOfWeek getCurrentDayOfWeek(int monthDay) {
        return date.minusDays(date.getDayOfMonth() - monthDay).getDayOfWeek();
    }

    private int initFirstDayOfMonthWeekDayId() {
        int firstDayOfMonthWeekDayId = getCurrentDayOfWeek(1).getValue() - 1 + WEEK_DAYS_SHIFT;
        return normalizeFirrstMonthDayWeekId(firstDayOfMonthWeekDayId);
    }

    private int initLastDayOfMonthWeekDayId() {
        int lastDayOfMonthWeekDayId = date.plusDays(date.lengthOfMonth() - date.getDayOfMonth()).getDayOfWeek().getValue() - 1 + WEEK_DAYS_SHIFT;
        return normalizeLastMonthDayWeekId(lastDayOfMonthWeekDayId);
    }

    private int normalizeFirrstMonthDayWeekId(int firstDayOfMonthWeekDayId) {
        for (int i = 0; i < 1; ) {
            if (firstDayOfMonthWeekDayId >= MAX_WEEK_DAYS) {
                firstDayOfMonthWeekDayId = firstDayOfMonthWeekDayId - MAX_WEEK_DAYS;
            } else if (firstDayOfMonthWeekDayId < 0) {
                firstDayOfMonthWeekDayId = firstDayOfMonthWeekDayId + MAX_WEEK_DAYS;
            } else {
                i++;
            }
        }
        return firstDayOfMonthWeekDayId;
    }

    private int normalizeLastMonthDayWeekId(int lastDayOfMonthWeekDayId) {
        for (int i = 0; i < 1; ) {
            if (lastDayOfMonthWeekDayId >= MAX_WEEK_DAYS) {
                lastDayOfMonthWeekDayId = lastDayOfMonthWeekDayId - MAX_WEEK_DAYS;
            } else if (lastDayOfMonthWeekDayId < 0) {
                lastDayOfMonthWeekDayId = lastDayOfMonthWeekDayId + MAX_WEEK_DAYS;
            } else {
                i++;
            }
        }
        return lastDayOfMonthWeekDayId;
    }

    private void resetPrintFormat() {
        System.out.print(SET_DEFAULT_COLOR);
    }
}
