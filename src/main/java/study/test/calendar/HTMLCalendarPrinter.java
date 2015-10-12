package study.test.calendar;

import java.io.*;
import java.time.LocalDate;

/**
 * Created by anri on 08.10.15.
 */
public class HTMLCalendarPrinter implements CalendarPrinter {
    public static final String SET_DEFAULT_BG_COLOR = "white";

    private File htmlFile = new File("calendar.html");

    private String resultText = "";

    private boolean isFirstCallPrintMonthAndYear = false;

    private String printFormat = SET_DEFAULT_BG_COLOR;

    public void startPrint(){
        resultText += "<table align=\"center\" border=\"0\">\n<tr>\n";
    }

    public void printMonthAndYear(LocalDate date) {
        if (isFirstCallPrintMonthAndYear) {
            resultText += "<tr>\n";
            isFirstCallPrintMonthAndYear = true;
        }

        resultText += String.format(
                "<th colspan=\"%s\"><h1 align = \"center\">%s %s</h1></th>",
                MonthCalendar.MAX_WEEK_DAYS, date.getMonth(), date.getYear());
        printNewLine();
    }

    public void printShortWeekDayName(String weekdayName, PrintFormat format) {
        resultText += String.format(
                "<td align=\"right\"><font color=\"%s\">%s</font></td>\n",
                format.getCurrentTEXTFormat(), weekdayName);
    }

    // some problems here
    public void printDayNumber(int dayNumber, PrintFormat format) {
        resultText += String.format("<td align=\"right\" bgcolor=\"%s\" style=\"border-radius:5px\"><font color=\"%s\">%s </font></td>\n",
                format.getCurrentAccentuationTEXTFormat(), format.getCurrentTEXTFormat(), dayNumber);
        resetPrintFormat();
    }

    private void resetPrintFormat() {
        printFormat = SET_DEFAULT_BG_COLOR;
    }

    public void printNewLine() {
        resultText += "$newLine";
    }

    public void endPrint() {
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
