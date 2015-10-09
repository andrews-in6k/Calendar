package study.test.calendar;

import java.io.*;
import java.time.LocalDate;

/**
 * Created by anri on 08.10.15.
 */
public class HTMLCalendarPrinter implements CalendarPrinter{
    public static final int MAX_WEEK_DAYS = MonthCalendar.MAX_WEEK_DAYS;
    public static final String SET_DEFAULT_BG_COLOR = "white";

    private File htmlFile = new File("calendar.html");

    private String resultText = new String();
    private String functionalResultText = new String();

    private boolean isFirstCallPrintMonthAndYear = false;

    private String printFormat = SET_DEFAULT_BG_COLOR;

    public void printToHTML(){
        try {
            initResultText();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(htmlFile));
            bufferedWriter.write(resultText);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printMonthAndYear(LocalDate date) {
        if (isFirstCallPrintMonthAndYear){
            functionalResultText += "<tr>\n";
            isFirstCallPrintMonthAndYear = true;
        }
        functionalResultText +="<th colspan=\"" + MAX_WEEK_DAYS + "\"><h1 align = \"center\">" + date.getMonth() + " " + date.getYear() + "</h1></th>\n";
        printNewLine();
    }

    public void printShortWeekDaysName(String weekdayName, String[] format){
        functionalResultText += "<td align=\"right\"><font color=\"" + format[1] + "\">" + weekdayName + " </font></td>\n";
    }

    public void printDayNumber(int dayNumber, String[] format) {
        functionalResultText += "<td align=\"right\" bgcolor=\"" + printFormat + "\"><font color=\"" + format[1] + "\">" + dayNumber + " </font></td>\n";
        resetPrintFormat();
    }

    public void setPrintFormat(String[] format){
        printFormat = format[1];
    }

    private void resetPrintFormat(){
        printFormat = SET_DEFAULT_BG_COLOR;
    }

    public void printNewLine(){
        functionalResultText += "$newLine";
    }

    private void initResultText(){
        functionalResultText = functionalResultText.replace("$newLine","</tr>\n<tr>");
        resultText = "<table align=\"center\" border=\"0\">\n" +
                "<tr>\n" +
                functionalResultText +
                "\n</tr>" +
                "</table>";
    }

}
