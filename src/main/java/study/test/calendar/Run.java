package study.test.calendar;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Run{

	public static final int MAX_WEEK_DAYS = 7;
	public static final int WEEK_DAYS_SHIFT = 0;

	private static LocalDate date= LocalDate.now();

	public static void main (String[] args){
		int firstDayOfMonthWeekDayId = date.minusDays(date.getDayOfMonth()-1).getDayOfWeek().getValue() - 1 + WEEK_DAYS_SHIFT;
		int lastDayOfMonthWeekDayId = date.plusDays(date.lengthOfMonth()-date.getDayOfMonth()).getDayOfWeek().getValue() - 1 + WEEK_DAYS_SHIFT;

		firstDayOfMonthWeekDayId = normalizeFirrstMonthDayWeekId(firstDayOfMonthWeekDayId);
		lastDayOfMonthWeekDayId = normalizeLastMonthDayWeekId(lastDayOfMonthWeekDayId);

		int thisMonthLength = date.lengthOfMonth();
		int prevMonthLength = date.minusMonths(1).lengthOfMonth();
		int nextMonthLength = date.plusMonths(1).lengthOfMonth();

		printMonthAndYear();
		printWeekDaysName();
		printDayNumbers(firstDayOfMonthWeekDayId, lastDayOfMonthWeekDayId,  thisMonthLength, prevMonthLength, nextMonthLength);
	}

	private static void printMonthAndYear(){
		System.out.println(date.getMonth() + " " + date.getYear());
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
		return date.minusDays((date.getDayOfWeek().getValue()) - 1 - iter + WEEK_DAYS_SHIFT).getDayOfWeek();
	}

	public static void printDayNumbers(int firstDayOfMonthWeekDayId, int lastDayOfMonthWeekDayId, int thisMonthLength, int prevMonthLength, int nextMonthLength){
		int calendarSize = thisMonthLength + firstDayOfMonthWeekDayId + (MAX_WEEK_DAYS - lastDayOfMonthWeekDayId - 1);
		for (int i = 0; i < calendarSize; i++){
			int checkIfThisMonth = i - firstDayOfMonthWeekDayId + 1;
			if (((i % 7) == 0)&&(i != 0)){
				System.out.println();
			}
			if (checkIfThisMonth <= 0){
				printPrevOrNextMonthDayNum(prevMonthLength + checkIfThisMonth);
			}
			else
				if(checkIfThisMonth > thisMonthLength){
					printPrevOrNextMonthDayNum(checkIfThisMonth - thisMonthLength);
				}
				else{
					ifToday(checkIfThisMonth);
					if(((date.minusDays(date.getDayOfMonth() - checkIfThisMonth).getDayOfWeek()) == DayOfWeek.SATURDAY)||
							((date.minusDays(date.getDayOfMonth() - checkIfThisMonth).getDayOfWeek()) == DayOfWeek.SUNDAY)){
						 printWeekEnds(checkIfThisMonth);
					}
					else{
						printWeekDays(checkIfThisMonth);
					}
				}
		}
	}

	private static void printPrevOrNextMonthDayNum(int result){
		System.out.print("\u001b[01;38;05;252m");
		System.out.format("%3d ",result);
		System.out.print("\u001b[0m");
	}
	private static void printWeekEnds(int iter){
		System.out.print("\u001b[31m");
		System.out.format("%3d ",iter);
		System.out.print("\u001b[0m");
	}
	private static void printWeekDays(int iter){
		System.out.format("%3d ",iter);
		System.out.print("\u001b[0m");
	}

	private static void ifToday(int iter){
		if (iter == date.getDayOfMonth()){
			System.out.print("\u001b[42m");
		}
	}
	private static int normalizeFirrstMonthDayWeekId (int firstDayOfMonthWeekDayId){
		for (int i = 0;i < 2;){
			firstDayOfMonthWeekDayId = Math.abs(firstDayOfMonthWeekDayId);
			if (firstDayOfMonthWeekDayId >= MAX_WEEK_DAYS){
				firstDayOfMonthWeekDayId = firstDayOfMonthWeekDayId - MAX_WEEK_DAYS;
			}
			else {
				i++;
			}
		}
		return firstDayOfMonthWeekDayId;
	}
	private static int normalizeLastMonthDayWeekId (int lastDayOfMonthWeekDayId){
		for (int i = 0;i < 2;){
			lastDayOfMonthWeekDayId = Math.abs(lastDayOfMonthWeekDayId);
			if (lastDayOfMonthWeekDayId >= MAX_WEEK_DAYS){
				lastDayOfMonthWeekDayId = lastDayOfMonthWeekDayId - MAX_WEEK_DAYS;
			}
			else {
				i++;
			}
		}
		return lastDayOfMonthWeekDayId;
	}

}
