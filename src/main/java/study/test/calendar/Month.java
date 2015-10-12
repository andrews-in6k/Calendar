package study.test.calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by employee on 10/12/15.
 */
public class Month {
    private List<Week> weekList = new ArrayList<Week>();

    private int monthValue;

    Month(LocalDate date){
        this.monthValue = date.getMonthValue();

        date = date.minusDays(date.getDayOfMonth() - 1);


        while(date.getDayOfWeek() != Week.FIRST_DAY_OF_WEEK){
            date = date.minusDays(1);
        }

        int weekValue = 1;
        do{
            weekList.add(new Week(weekValue, date));
            weekValue++;
            date = date.plusWeeks(1);
        }while (date.getMonthValue() == monthValue);

    }

    public List<Week> getWeekList() {
        return weekList;
    }

    public int getMonthValue() {
        return monthValue;
    }

}
