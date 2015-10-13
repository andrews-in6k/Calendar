package study.test.calendar;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by employee on 10/12/15.
 */
public class CalendarMonth {
    private List<Week> weekList = new ArrayList<Week>();

    private LocalDate date;

    CalendarMonth(LocalDate date) {
        this.date = date;

        date = leadDateToStartingPosition(date);

        fillWeekList(date);
    }

    private LocalDate leadDateToStartingPosition(LocalDate date) {
        date = date.minusDays(date.getDayOfMonth() - 1);

        while (!isFirstDayOfWeek(date)) {
            date = date.minusDays(1);
        }

        return date;
    }

    private boolean isFirstDayOfWeek(LocalDate date) {
        return date.getDayOfWeek().equals(Week.FIRST_DAY_OF_WEEK);
    }

    private void fillWeekList(LocalDate date) {
        do {
            weekList.add(new Week(date));
            date = date.plusWeeks(1);
        } while (date.getMonth() == getMonth());
    }

    public List<Week> getWeekList() {
        return weekList;
    }

    public Month getMonth() {
        return date.getMonth();
    }

    public int getYear() {
        return date.getYear();
    }

}
