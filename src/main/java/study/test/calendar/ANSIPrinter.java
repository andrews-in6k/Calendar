package study.test.calendar;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 10/12/15.
 */
public class ANSIPrinter extends Printer {
    public static final String PRINTER_COLOR_FORMAT_CODING = "ANSI";

    ANSIPrinter(LocalDate currentDate){
        colorFormat = new ColorFormat(PRINTER_COLOR_FORMAT_CODING);

        this.currentDate = currentDate;
    }

    public void beginPrint(){
        resultText += "--------------------------------\n";
    }

    protected void printMonthAndYear(CalendarMonth calendarMonth){
        resultText += String.format("%s %d\n",
                calendarMonth.getMonthName(), calendarMonth.getYear());
    }

    protected void printWeekdayName(Day day, String format){
        resultText += String.format("%s%s ",
                format,day.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CANADA));
    }

    protected void printDayNumber(Day day, String format, String accentuationFormat){
        resultText += String.format("%s%s%3d %s",
                accentuationFormat, format, day.getDayOfMonth(), colorFormat.getDefaultFormat());
    }

    protected void printLine(){
        resultText += "\n";
    }

    public void endPrint(){
        resultText += "--------------------------------\n";
        System.out.print(resultText);
    }
}
