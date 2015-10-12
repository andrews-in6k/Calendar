package study.test.calendar;

import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 10/12/15.
 */
public class ANSIPrinter extends Printer {

    public void beginPrint(){

    }

    protected void printMonthAndYear(CalendarMonth calendarMonth){
        resultText += calendarMonth.getMonthName() + " " + calendarMonth.getYear() + "\n";
    }

    protected void printWeekdayName(Day day){
        resultText += day.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CANADA) + " ";
    }

    public void endPrint(){
        System.out.print(resultText);
    }
}
