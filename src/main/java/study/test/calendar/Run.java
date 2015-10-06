package study.test.calendar;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class Run{

	public static final int MON_ID = 0;
	public static final int TUE_ID = 1;
	public static final int WED_ID = 2;
	public static final int THU_ID = 3;
	public static final int FRI_ID = 4;
	public static final int SAT_ID = 5;
	public static final int SUN_ID = 6;

	public static final String[] WEEK_DAYS_STANDART = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
	public static final int[] NUMBERS31 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
		16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
	public static final int[] NUMBERS30 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
		16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
	public static final int[] NUMBERS28 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
		16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
	public static final int[] NUMBERS29 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
		16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};		

	public static final int MAX_WEEK_DAYS = 7;

	private static LocalDate date= LocalDate.now();

	public static void main (String[] args){
		int firstDayOfMonthWeekDayId = date.minusDays(date.getDayOfMonth()-1).getDayOfWeek().getValue() - 1;
		int lastDayOfMonthWeekDayId = date.plusDays(date.lengthOfMonth()-date.getDayOfMonth()).getDayOfWeek().getValue() - 1;
		int thisMonthLength = date.lengthOfMonth();
		int prevMonthLength = date.minusMonths(1).lengthOfMonth();
		int nextMonthLength = date.plusMonths(1).lengthOfMonth();

		printMonthAndYear();
		printWeekDaysName();
		printDayNumbers(firstDayOfMonthWeekDayId, lastDayOfMonthWeekDayId,  NUMBERS31, NUMBERS30, NUMBERS30);
	}

	private static void printMonthAndYear(){
		System.out.println(date.getMonth()+" "+date.getYear());
	}

	public static void printWeekDaysName(){
		for (int i = 0; i < MAX_WEEK_DAYS; i++){
			if (getDayOfWeek(i).equals(DayOfWeek.SUNDAY)||getDayOfWeek(i).equals(DayOfWeek.SATURDAY)){
				System.out.print("\u001b[31m" + getDayOfWeek(i).getDisplayName(TextStyle.SHORT, Locale.CANADA) + " " + "\u001b[0m");
			}
			else{
				System.out.print(getDayOfWeek(i).getDisplayName(TextStyle.SHORT, Locale.CANADA) + " ");
			}
		}
		System.out.println();
	}
	private static DayOfWeek getDayOfWeek(int iter){
		return date.minusDays((date.getDayOfWeek().getValue())-1-iter).getDayOfWeek();
	}

	public static void printDayNumbers(int firstDayID, int lastDayID, int[] dayNumbersArray, int[] prevMonthArr, int[] nextMonthArr){
		for (int i = 0, line = 0; i < (dayNumbersArray.length + firstDayID + (7 - lastDayID - 1)); i++){
			int j = i - firstDayID;
			if (j < 0){
				printPrevMonthDayNum(prevMonthArr, j);
			}
			else if (j >= dayNumbersArray.length){
					printNextMonthDayNum(dayNumbersArray,nextMonthArr, j);
				}
				else{
					ifToday(dayNumbersArray, j);
					if((((i+1) % 6) - line) == 0){
						 printWeekEnds(dayNumbersArray, j);
					}else if(((i+1) % 7)==0){
							printWeekEnds(dayNumbersArray, j);
							System.out.println();
							line++;
						}
						else{
							 printWeekDays(dayNumbersArray, j);
						}
			}
		}
		System.out.println();
	}

	private static void printPrevMonthDayNum(int[] dayNumbersArray, int iter){
		System.out.print("\u001b[01;38;05;252m");
		System.out.format("%3d ",dayNumbersArray[dayNumbersArray.length+iter]);
		System.out.print("\u001b[0m");
	}
	private static void printNextMonthDayNum(int[] dayNumbersArray, int[] nextMonthArr, int iter){
		System.out.print("\u001b[01;38;05;252m");
		System.out.format("%3d ",dayNumbersArray[iter-dayNumbersArray.length]);
		System.out.print("\u001b[0m");
	}
	private static void printWeekEnds(int[] dayNumbersArray, int iter){
		System.out.print("\u001b[31m");
		System.out.format("%3d ",dayNumbersArray[iter]);
		System.out.print("\u001b[0m");
	}
	private static void printWeekDays(int[] dayNumbersArray, int iter){
		System.out.format("%3d ",dayNumbersArray[iter]);
		System.out.print("\u001b[0m");
	}

	private static void ifToday(int[] dayNumbersArray, int iter){
		Calendar calendar = Calendar.getInstance();
		if (dayNumbersArray[iter] == calendar.get(Calendar.DAY_OF_MONTH)){
			System.out.print("\u001b[42m");
		}
	}

}
