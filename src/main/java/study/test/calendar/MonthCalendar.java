package study.test.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 10/8/15.
 */
public class MonthCalendar {
    public static final String SET_HOLYDAY_COLOR = "\u001b[31m";
    public static final String SET_DAY_FROM_OTHER_MONTH_COLOR = "\u001b[01;38;05;252m";
    public static final String SET_DEFAULT_COLOR = "\u001b[0m";
    public static final String SET_CURRENT_DAY_ACCENTUATION_COLOR = "\u001b[42;30m";

    public static final int MAX_WEEK_DAYS = 7;
    public static final int WEEK_DAYS_SHIFT = 0;

    private LocalDate date = LocalDate.now();

    MonthCalendar(LocalDate date){
        this.date = date;
    }

    public void printCalendar(){
        int thisMonthLength = date.lengthOfMonth();
        int prevMonthLength = date.minusMonths(1).lengthOfMonth();

        printMonthAndYear();
        printWeekDaysName();
        printDayNumbers(initFirstDayOfMonthWeekDayId(), initLastDayOfMonthWeekDayId(), thisMonthLength, prevMonthLength);
    }

    private void printMonthAndYear() {
        System.out.println(date.getMonth() + " " + date.getYear());
    }

    private void printWeekDaysName() {
        for (int i = 0; i < MAX_WEEK_DAYS; i++) {
            if (isItHolydayWeekDaysNamer(i)) {
                System.out.print(SET_HOLYDAY_COLOR + getDayOfWeekName(i).getDisplayName(TextStyle.SHORT, Locale.CANADA) + " ");
                resetPrintFormat();
            } else {
                System.out.print(getDayOfWeekName(i).getDisplayName(TextStyle.SHORT, Locale.CANADA) + " ");
            }
        }
        System.out.println();
    }

    private void printDayNumbers(int firstDayOfMonthWeekDayId, int lastDayOfMonthWeekDayId, int thisMonthLength, int prevMonthLength) {
        int calendarSize = thisMonthLength + firstDayOfMonthWeekDayId + (MAX_WEEK_DAYS - lastDayOfMonthWeekDayId - 1);
        for (int i = 0; i < calendarSize; i++) {
            int dayNumberRelativeToThisMonth = i - firstDayOfMonthWeekDayId + 1;
            ifEndOfWeek(i);
            ifPrevMonth(dayNumberRelativeToThisMonth, prevMonthLength);
            ifNextMonth(dayNumberRelativeToThisMonth, thisMonthLength);
            ifThisMonth(dayNumberRelativeToThisMonth, thisMonthLength);
        }
    }

    private void printPrevOrNextMonthDayNum(int result) {
        System.out.print(SET_DAY_FROM_OTHER_MONTH_COLOR);
        System.out.format("%3d ", result);
        resetPrintFormat();
    }

    private void printWeekEnds(int iter) {
        System.out.print(SET_HOLYDAY_COLOR);
        System.out.format("%3d ", iter);
        resetPrintFormat();
    }

    private void printWeekDays(int iter) {
        System.out.format("%3d ", iter);
        resetPrintFormat();
    }

    private boolean isItHolydayWeekDaysNamer(int iter) {
        return (getDayOfWeekName(iter).equals(DayOfWeek.SUNDAY) || getDayOfWeekName(iter).equals(DayOfWeek.SATURDAY));
    }

    private boolean isItHolydayDayNumber(int checkIfThisMonth) {
        DayOfWeek thisDayOfWeek = (date.minusDays(date.getDayOfMonth() - checkIfThisMonth).getDayOfWeek());
        return ((thisDayOfWeek.equals(DayOfWeek.SATURDAY)) ||
                (thisDayOfWeek.equals(DayOfWeek.SUNDAY)));
    }

    private boolean isItPrevMonth(int checkIfThisMonth) {
        return checkIfThisMonth <= 0;
    }

    private boolean isItNextMonth(int checkIfThisMonth, int thisMonthLength) {
        return checkIfThisMonth > thisMonthLength;
    }

    private void ifEndOfWeek(int iter) {
        if (((iter % 7) == 0) && (iter != 0)) {
            System.out.println();
        }
    }

    private void ifPrevMonth(int checkIfThisMonth, int prevMonthLength) {
        if (isItPrevMonth(checkIfThisMonth)) {
            printPrevOrNextMonthDayNum(prevMonthLength + checkIfThisMonth);
        }
    }

    private void ifThisMonth(int checkIfThisMonth, int thisMonthLength) {
        if (!(isItPrevMonth(checkIfThisMonth)) && !(isItNextMonth(checkIfThisMonth, thisMonthLength))) {
            ifToday(checkIfThisMonth);
            if (isItHolydayDayNumber(checkIfThisMonth)) {
                printWeekEnds(checkIfThisMonth);
            } else {
                printWeekDays(checkIfThisMonth);
            }
        }
    }

    private void ifToday(int iter) {
        if (iter == date.getDayOfMonth()) {
            System.out.print(SET_CURRENT_DAY_ACCENTUATION_COLOR);
        }
    }

    private void ifNextMonth(int checkIfThisMonth, int thisMonthLength) {
        if (isItNextMonth(checkIfThisMonth, thisMonthLength)) {
            printPrevOrNextMonthDayNum(checkIfThisMonth - thisMonthLength);
        }
    }

    private DayOfWeek getDayOfWeekName(int iter) {
        return date.minusDays((date.getDayOfWeek().getValue()) - 1 - iter + WEEK_DAYS_SHIFT).getDayOfWeek();
    }

    private int initFirstDayOfMonthWeekDayId() {
        int firstDayOfMonthWeekDayId = date.minusDays(date.getDayOfMonth() - 1).getDayOfWeek().getValue() - 1 + WEEK_DAYS_SHIFT;
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
