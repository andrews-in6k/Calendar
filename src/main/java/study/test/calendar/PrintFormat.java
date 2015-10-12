package study.test.calendar;

/**
 * Created by anri on 11.10.15.
 */
public class PrintFormat {
    private static final String ANSI_HOLIDAY_COLOR = "\u001b[31m";
    private static final String ANSI_DAY_FROM_OTHER_MONTH_COLOR = "\u001b[34m";
    private static final String ANSI_DEFAULT_COLOR = "\u001b[0m";
    private static final String ANSI_CURRENT_DAY_ACCENTUATION_COLOR = "\u001b[42;30m";

    private static final String TEXT_HOLIDAY_COLOR = "red";
    private static final String TEXT_DAY_FROM_OTHER_MONTH_COLOR = "blue";
    private static final String TEXT_DEFAULT_COLOR = "black";
    private static final String TEXT_CURRENT_DAY_ACCENTUATION_COLOR = "LawnGreen";

    private String currentANCIFormat;
    private String currentTEXTFormat;

    public void setFormatAsHoliday(){
        currentANCIFormat = ANSI_HOLIDAY_COLOR;
        currentTEXTFormat = TEXT_HOLIDAY_COLOR;
    }
    public void setFormatAsDayFromOtherMonth(){
        currentANCIFormat = ANSI_DAY_FROM_OTHER_MONTH_COLOR;
        currentTEXTFormat = TEXT_DAY_FROM_OTHER_MONTH_COLOR;
    }
    public void setFormatAsDefault(){
        currentANCIFormat = ANSI_DEFAULT_COLOR;
        currentTEXTFormat = TEXT_DEFAULT_COLOR;
    }

    public void setFormatAsCurrentDay(){
        currentANCIFormat = ANSI_CURRENT_DAY_ACCENTUATION_COLOR;
        currentTEXTFormat = TEXT_CURRENT_DAY_ACCENTUATION_COLOR;
    }

    public String getCurrentANSIFormat() {
        return currentANCIFormat;
    }

    public String getCurrentTEXTFormat() {
        return currentTEXTFormat;
    }

    public static String getAnsiHolidayColor() {
        return ANSI_HOLIDAY_COLOR;
    }

    public static String getAnsiDayFromOtherMonthColor() {
        return ANSI_DAY_FROM_OTHER_MONTH_COLOR;
    }

    public static String getAnsiDefaultColor() {
        return ANSI_DEFAULT_COLOR;
    }

    public static String getAnsiCurrentDayAccentuationColor() {
        return ANSI_CURRENT_DAY_ACCENTUATION_COLOR;
    }

    public static String getTextHolidayColor() {
        return TEXT_HOLIDAY_COLOR;
    }

    public static String getTextDayFromOtherMonthColor() {
        return TEXT_DAY_FROM_OTHER_MONTH_COLOR;
    }

    public static String getTextDefaultColor() {
        return TEXT_DEFAULT_COLOR;
    }

    public static String getTextCurrentDayAccentuationColor() {
        return TEXT_CURRENT_DAY_ACCENTUATION_COLOR;
    }
}
