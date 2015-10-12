package study.test.calendar;

/**
 * Created by anri on 12.10.15.
 */
public class ColorFormat {
    private String defaultAccentuationFormat;
    private String defaultFormat;
    private String holidayFormat;
    private String fromOtherMonthFormat;
    private String currentDayAccentuationFormat;

    ColorFormat(String format) {
        if (format.equals("ANSI")) {
            generateANSIFormats();
        } else if (format.equals("TEXT")) {
            generateTEXTFormats();
        }
    }

    private void generateANSIFormats(){
        defaultFormat = "\u001b[0m";
        holidayFormat = "\u001b[31m";
        fromOtherMonthFormat = "\u001b[34m";
        currentDayAccentuationFormat = "\u001b[42;30m";
        defaultAccentuationFormat = "\u001b[0m";
    }

    private void generateTEXTFormats(){
        defaultFormat = "black";
        holidayFormat = "red";
        fromOtherMonthFormat = "blue";
        currentDayAccentuationFormat = "LawnGreen";
        defaultAccentuationFormat = "white";
    }

    public String getCurrentDayAccentuationFormat() {
        return currentDayAccentuationFormat;
    }

    public String getHolidayFormat() {
        return holidayFormat;
    }

    public String getDefaultFormat() {
        return defaultFormat;
    }

    public String getFromOtherMonthFormat() {
        return fromOtherMonthFormat;
    }

    public String getDefaultAccentuationFormat() {
        return defaultAccentuationFormat;
    }
}

