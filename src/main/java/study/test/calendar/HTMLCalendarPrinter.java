package study.test.calendar;

import java.io.*;
import java.time.LocalDate;

/**
 * Created by anri on 08.10.15.
 */
public class HTMLCalendarPrinter implements CalendarPrinter{
    private File htmlFile = new File("calendar.html");

    private String resultText = new String();

    private boolean isFirstCallPrintWeekdaysName = false;

    public void printToHTML(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(htmlFile));
            bufferedWriter.write(resultText);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printMonthAndYear(LocalDate date) {
        resultText +="<h1 align = \"center\">" + date.getMonth() + " " + date.getYear() + "</h1>\n";
        printNewLine();
    }

    public void printShortWeekDaysName(String weekdayName, String[] format){
        resultText += "<div><font color=\"" + format[1] + "\">" + weekdayName + " </font></div>";
    }

    public void printDayNumber(int dayNumber, String[] format) {

    }

    public void setPrintFormat(String[] format){

    }

    public void printNewLine(){
        resultText += "<br>\n";
    }

}
