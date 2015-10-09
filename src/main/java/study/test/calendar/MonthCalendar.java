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
    public static final int WEEK_DAYS_SHIFT = 0;

    private LocalDate sourceDate = LocalDate.now();
    private LocalDate dateForOutput;

    ConsoleCalendarPrinter consoleCalendarPrinter = new ConsoleCalendarPrinter(SET_DEFAULT_COLOR);
    HTMLCalendarPrinter htmlCalendarPrinter = new HTMLCalendarPrinter();

    MonthCalendar(LocalDate date) {
        this.sourceDate = date;
    }

    public void printCalendar() {
        consoleCalendarPrinter.printMonthAndYear(sourceDate);
        printWeekDaysNames(consoleCalendarPrinter);
        printDayNumbers(consoleCalendarPrinter);
        htmlCalendarPrinter.printMonthAndYear(sourceDate);
        printWeekDaysNames(htmlCalendarPrinter);
        printDayNumbers(htmlCalendarPrinter);
        htmlCalendarPrinter.printToHTML();
    }

    private void printWeekDaysNames(CalendarPrinter calendarPrinter) {
        for (int weekDayIndex = 0; weekDayIndex < MAX_WEEK_DAYS; weekDayIndex++) {
            if (isHolidayName(weekDayIndex)) {
                calendarPrinter.printShortWeekDaysName(getShortWeekdayName(weekDayIndex), SET_HOLIDAY_COLOR);
            } else {
                calendarPrinter.printShortWeekDaysName(getShortWeekdayName(weekDayIndex), SET_DEFAULT_COLOR);
            }
        }
        calendarPrinter.printNewLine();
    }

    private void printDayNumbers(CalendarPrinter calendarPrinter) {
        initDateForOutput();
        for (int dayIndex = 0; isNotNextMonthFirstWeekEnd(dayIndex); dayIndex++) {
            if (isEndOfWeek(dayIndex)) {
                calendarPrinter.printNewLine();
            }
            if (isPrevMonth(dayIndex)) {
                calendarPrinter.printDayNumber(getCurrentDayNumber(dayIndex).getDayOfMonth(), SET_DAY_FROM_OTHER_MONTH_COLOR);
            }
            if (isCurrentMonth(dayIndex)) {
                printCurrentMonthDayNumbers(dayIndex, calendarPrinter);
            }
            if (isNextMonth(dayIndex)) {
                calendarPrinter.printDayNumber(getCurrentDayNumber(dayIndex).getDayOfMonth(), SET_DAY_FROM_OTHER_MONTH_COLOR);
            }
        }
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

    private void printCurrentMonthDayNumbers(int dayIndex, CalendarPrinter calendarPrinter) {
        if (isToday(getCurrentDayNumber(dayIndex).getDayOfMonth())) {
            calendarPrinter.setPrintFormat(SET_CURRENT_DAY_ACCENTUATION_COLOR);
        }
        if (isHolidayNumber(dayIndex)) {
            calendarPrinter.printDayNumber(getCurrentDayNumber(dayIndex).getDayOfMonth(), SET_HOLIDAY_COLOR);
        } else {
            calendarPrinter.printDayNumber(getCurrentDayNumber(dayIndex).getDayOfMonth(), SET_DEFAULT_COLOR);
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

    private String getShortWeekdayName(int weekdayIndex) {
        return getWeekdayName(weekdayIndex).getDisplayName(TextStyle.SHORT, Locale.CANADA);
    }

}
