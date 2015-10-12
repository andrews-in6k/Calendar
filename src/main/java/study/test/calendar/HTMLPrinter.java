package study.test.calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 10/12/15.
 */
public class HTMLPrinter extends Printer {
    public static final String PRINTER_COLOR_FORMAT_CODING = "TEXT";

    HTMLPrinter(LocalDate currentDate){
        colorFormat = new ColorFormat(PRINTER_COLOR_FORMAT_CODING);

        this.currentDate = currentDate;
    }

    public void beginPrint(){
        resultText += "<table align=\"center\" border=\"0\">\n<tr>\n";
    }

    protected void printMonthAndYear(CalendarMonth calendarMonth){
        resultText += String.format(
                "<th colspan=\"%s\"><h1 align = \"center\">%s %s</h1></th>",
                Week.MAX_WEEK_DAYS, calendarMonth.getMonthName(), calendarMonth.getYear());
        printLine();
    }

    protected void printWeekdayName(Day day, String format){
        resultText += String.format(
                "<td align=\"right\"><font color=\"%s\">%s</font></td>\n",
                format, day.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CANADA));
    }

    protected void printDayNumber(Day day, String format, String accentuationFormat){
        resultText += String.format("<td align=\"right\" bgcolor=\"%s\" style=\"border-radius:5px\"><font color=\"%s\">%s </font></td>\n",
                accentuationFormat, format, day.getDayOfMonth());
    }

    protected void printLine(){
        resultText += "$newLine";
    }

    public void endPrint(){
        File htmlFile = new File("calendar.html");

        try {
            resultText +="\n</tr></table>";
            resultText = resultText.replace("$newLine", "</tr>\n<tr>");

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(htmlFile));
            bufferedWriter.write(resultText);
            bufferedWriter.close();

            System.out.println("HTML created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
