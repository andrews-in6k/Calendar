package study.test.calendar;

import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 10/12/15.
 */
public class ANSIPrinter extends Printer {

    public void beginPrint(){
        resultText += "--------------------------------\n";
    }

    protected void printMonthAndYear(CalendarMonth calendarMonth){
        resultText += String.format("%s %d\n",
                calendarMonth.getMonthName(), calendarMonth.getYear());
    }

    protected void printWeekdayName(Day day){
        resultText += String.format("%s ",
                day.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CANADA));
    }

    protected void printDayNumber(Day day){
        resultText += String.format("%3d ",
                day.getDayOfMonth());
    }

    protected void printLine(){
        resultText += "\n";
    }

    public void endPrint(){
        resultText += "--------------------------------\n";
        System.out.print(resultText);
    }
}
