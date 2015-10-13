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

    private LocalDate dateForCurrentMonth;
    private LocalDate dateForOutput;

    CalendarMonth(LocalDate dateForOutput) {
        this.dateForCurrentMonth = dateForOutput;
        this.dateForOutput = dateForOutput;

        this.dateForOutput = leadDateToStartingPosition();

        fillWeekList();
    }

    private LocalDate leadDateToStartingPosition() {
        dateForOutput = dateForOutput.minusDays(dateForOutput.getDayOfMonth() - 1);

        while (!isFirstDayOfWeek()) {
            dateForOutput = dateForOutput.minusDays(1);
        }

        return dateForOutput;
    }

    private boolean isFirstDayOfWeek() {
        return dateForOutput.getDayOfWeek().equals(Week.FIRST_DAY_OF_WEEK);
    }

    private void fillWeekList() {
        do {
            weekList.add(new Week(dateForOutput));
            dateForOutput = dateForOutput.plusWeeks(1);
        } while (dateForOutput.getMonth() == getMonth());
    }

    public List<Week> getWeekList() {
        return weekList;
    }

    public Month getMonth() {
        return dateForCurrentMonth.getMonth();
    }

    public int getYear() {
        return dateForCurrentMonth.getYear();
    }

}
