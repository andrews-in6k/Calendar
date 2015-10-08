package study.test.calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by anri on 08.10.15.
 */
public class HTMLCalendarPrinter implements CalendarPrinter{
    public void printMonthAndYear(LocalDate date) {
        String htmlText ="<body><h1 align = \"center\">" + date.getMonth() + " " + date.getYear() + "</h1></body>";
        File htmlFile = new File("calendar.html");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(htmlFile));
            bufferedWriter.write(htmlText);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printShortWeekDaysName(String weekdayName, String[] format){

    }

    public void printDayNumber(int dayNumber, String[] format) {

    }

    public void setPrintFormat(String[] format){

    }

}
