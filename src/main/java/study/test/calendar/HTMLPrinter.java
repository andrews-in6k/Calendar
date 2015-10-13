package study.test.calendar;

import java.io.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 10/12/15.
 */
public class HTMLPrinter extends Printer {
    public static final String CODING_IDENTIFICATION = "TEXT";

    HTMLPrinter(LocalDate currentDate, PrintStream output){
        super(currentDate, output);
        colorFormat = new ColorFormat(CODING_IDENTIFICATION);
    }

    public void beginPrint(){
        output.print("<table align=\"center\" border=\"0\">\n<tr>\n");
    }

    protected void printMonthAndYear(){
        output.printf("<th colspan=\"%s\"><h1 align = \"center\">%s %s</h1></th></tr>\n",
                Week.WEEK_LENGTH, calendarMonth.getMonth(), calendarMonth.getYear());
    }

    protected void printWeekdayName(Day day){
        output.printf("<td align=\"right\"><font color=\"%s\">%s</font></td>\n",
                textFormat, day.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CANADA));
    }

    protected void printDayNumber(Day day){
        output.printf("<td align=\"right\" bgcolor=\"%s\" style=\"border-radius:5px\"><font color=\"%s\">%s </font></td>\n",
                backgroundFormat, textFormat, day.getDayOfMonth());
    }

    protected void beginWeek(){
        output.print("<tr>");
    }

    protected void endWeek(){
        output.print("</tr>\n");
    }

    public void endPrint(){
        output.print("\n</table>");
    }
}
