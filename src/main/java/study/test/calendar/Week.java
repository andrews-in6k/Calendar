package study.test.calendar;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by employee on 10/12/15.
 */
public class Week {
    public static final DayOfWeek FIRST_DAY_OF_WEEK = DayOfWeek.MONDAY;
    public static final int WEEK_LENGTH = 7;

    private LocalDate dateForOutput;

    private List<Day> dayList = new ArrayList<>();

    Week(LocalDate dateForOutput) {
        this.dateForOutput = dateForOutput;
        fillDayList();
    }

    private void fillDayList() {
        do {
            dayList.add(new Day(dateForOutput));
            dateForOutput = dateForOutput.plusDays(1);
        } while (!dateForOutput.getDayOfWeek().equals(FIRST_DAY_OF_WEEK));
    }

    public List<Day> getDayList() {
        return dayList;
    }
}
