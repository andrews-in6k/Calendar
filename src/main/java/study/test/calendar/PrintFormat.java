package study.test.calendar;

/**
 * Created by anri on 11.10.15.
 */
public class PrintFormat {
    private static final String ANCI_HOLIDAY_COLOR = "\u001b[31m";
    private static final String ANCI_DAY_FROM_OTHER_MONTH_COLOR = "\u001b[34m";
    private static final String ANCI_DEFAULT_COLOR = "\u001b[0m";
    private static final String ANCI_CURRENT_DAY_ACCENTUATION_COLOR = "\u001b[42;30m";

    private static final String TEXT_HOLIDAY_COLOR = "red";
    private static final String TEXT_DAY_FROM_OTHER_MONTH_COLOR = "blue";
    private static final String TEXT_DEFAULT_COLOR = "black";
    private static final String TEXT_CURRENT_DAY_ACCENTUATION_COLOR = "LawnGreen";

    private String currentANCIFormat;
    private String currentTEXTFormat;

    public void setFormatAsHoliday(){
        currentANCIFormat = ANCI_HOLIDAY_COLOR;
        currentTEXTFormat = TEXT_HOLIDAY_COLOR;
    }
    public void setFormatAsDayFromOtherMonth(){
        currentANCIFormat = ANCI_DAY_FROM_OTHER_MONTH_COLOR;
        currentTEXTFormat = TEXT_DAY_FROM_OTHER_MONTH_COLOR;
    }
    public void setFormatAsDefault(){
        currentANCIFormat = ANCI_DEFAULT_COLOR;
        currentTEXTFormat = TEXT_DEFAULT_COLOR;
    }

    public void setFormatAsCurrentDay(){
        currentANCIFormat = ANCI_CURRENT_DAY_ACCENTUATION_COLOR;
        currentTEXTFormat = TEXT_CURRENT_DAY_ACCENTUATION_COLOR;
    }

    public String getCurrentANCIFormat() {
        return currentANCIFormat;
    }

    public String getCurrentTEXTFormat() {
        return currentTEXTFormat;
    }

    public static String getAnciHolidayColor() {
        return ANCI_HOLIDAY_COLOR;
    }

    public static String getAnciDayFromOtherMonthColor() {
        return ANCI_DAY_FROM_OTHER_MONTH_COLOR;
    }

    public static String getAnciDefaultColor() {
        return ANCI_DEFAULT_COLOR;
    }

    public static String getAnciCurrentDayAccentuationColor() {
        return ANCI_CURRENT_DAY_ACCENTUATION_COLOR;
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
