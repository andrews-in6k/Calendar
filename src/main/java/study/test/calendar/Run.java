package study.test.calendar;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

public class Run {

    private static LocalDate currentDate;

    private static Scanner scanner = new Scanner(System.in);

    private static CalendarMonth calendarMonth;

    private static PrintStream printStream;
    private static Printer printer;

    public static void main(String[] args) {
        setDate();
        choosePrintMode();
    }

    private static void setDate() {
        System.out.println("1 - output current month");
        System.out.println("2 - output entered month");

        char ch = scanner.next().charAt(0);

        switch (ch){
            case '1':
                currentDate = LocalDate.now();
                calendarMonth = new CalendarMonth(currentDate);
                break;
            case '2':
                int year = scanner.nextInt();
                int month = scanner.nextInt();
                int day = scanner.nextInt();
                currentDate = LocalDate.of(year, month, day);
                calendarMonth = new CalendarMonth(currentDate);
                break;
        }
    }

    private static void choosePrintMode() {
        System.out.println("1 - ANSI Printer (to console)");
        System.out.println("2 - HTML Printer (to file)");

        char ch = scanner.next().charAt(0);

        switch (ch){
            case '1':
                printToConsole();
                break;
            case '2':
                printToHTMLFile();
                break;
        }
    }

    private static void printToConsole() {
        printStream = new PrintStream(System.out);

        Printer printer = new ANSIPrinter(currentDate, printStream);

        printer.beginPrint();
        printer.printCalendar(calendarMonth);
        printer.endPrint();

        printStream.close();
    }

    private static void printToHTMLFile() {
        try {
            printStream = new PrintStream(new FileOutputStream("calendar.html"));

            printer = new HTMLPrinter(currentDate, printStream);

            printCalendarMonth();

            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void printCalendarMonth() {
        printer.beginPrint();
        printer.printCalendar(calendarMonth);
        printer.endPrint();
    }

}
