package study.test.calendar;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 10/12/15.
 */
public class ANSIPrinter extends Printer {
    public static final String CODING_IDENTIFICATION = "ANSI";

    ANSIPrinter(LocalDate currentDate, PrintStream output) {
        super(currentDate, output);
        colorFormat = new ColorFormat(CODING_IDENTIFICATION);
    }

    public void beginPrint() {
        output.print("--------------------------------\n");
    }

    protected void printMonthAndYear() {
        output.printf("%s %d\n",
                calendarMonth.getMonth(), calendarMonth.getYear());
    }

    protected void printWeekdayName(Day day) {
        output.printf("%s%s ",
                textFormat, day.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CANADA));
    }

    protected void printDayNumber(Day day) {
        output.printf("%s%s%3d %s",
                backgroundFormat, textFormat, day.getDayOfMonth(), colorFormat.getDefaultFormat());
    }

    protected void beginWeek(){

    }

    protected void endWeek() {
        output.println();
    }

    public void endPrint() {
        output.printf("--------------------------------\n");
    }
}
