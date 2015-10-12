package study.test.calendar;

/**
 * Created by employee on 10/12/15.
 */
public abstract class Printer {
    protected String resultText = "";

    abstract void beginPrint();

    public void printCalendar(CalendarMonth calendarMonth){
        printMonthAndYear(calendarMonth);
        printWeekdayNames(calendarMonth);
        printDayNumbers(calendarMonth);
    }

    abstract void printMonthAndYear(CalendarMonth calendarMonth);

    //TODO Формат
    protected void printWeekdayNames(CalendarMonth calendarMonth){
        for (Day day : calendarMonth.getWeekList().get(0).getDayList()){
            if(day.isWeekend()){
                printWeekdayName(day);
            }else {
                printWeekdayName(day);
            }
        }

        printLine();
    }

    protected void printDayNumbers(CalendarMonth calendarMonth){
        for(Week week : calendarMonth.getWeekList()){
            for (Day day : week.getDayList()){
                printDayNumber(day);
            }

            printLine();
        }
    }

    abstract void printWeekdayName(Day day);

    abstract void printDayNumber(Day day);

    abstract void printLine();

    abstract void endPrint();
}
