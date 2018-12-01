package DataManage;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.spi.CalendarNameProvider;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.plaf.TextUI;

import DataManage.Recruitment;
import bin.Member;

public class Staff extends Member{
	private String department;
	private List<Program> pList;
	
	public Staff(String id, String pw, String number, int age, String department) {
		super(id, pw, number, age);
	
		this.department = department;
		pList = new ArrayList<Program>();
	}
	
	public void MakeProgram(Program pro, Recruitment recu) {
		pro.setRecru(recu);
		pList.add(pro);
	}
	
	public boolean isLowestGradeValidCheck(String lowestGrade) {
		double grade;
		try {
			grade = Double.parseDouble(lowestGrade);
			if(grade < 0 || grade > 4.5) { 
				System.err.println("���� ���� : 0.0 ~ 4.5");
				return false;
			}
			
		} catch (NumberFormatException e) {
			return false;
		} catch( NullPointerException e) {
			System.err.println("�Է� ������ �ٸ��ϴ�.(Ex. 3.3)");
			return false;
		}
		return true;
	}
	
	public static boolean isSubmitDueValidCheck(String submitdue) {
		
		Calendar cal = Calendar.getInstance();
		
		int curYear = cal.get(Calendar.YEAR);
		
		String pattern = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
		
		int submitdueYear, submitdueMonth, submitdueDay;
		
		System.out.println(Calendar.YEAR);
		
		if(Pattern.matches(pattern, submitdue)) {
			
			try {
					String[] str = submitdue.split("-");
					
					submitdueYear = Integer.parseInt(str[0]);
					
					if(submitdueYear == curYear || submitdueYear == curYear+1) {
						
						submitdueMonth = Integer.parseInt(str[1]);
						
						if(isValidMonth(submitdueMonth) == false){
							
							System.out.println("month error");
							return false;
							
						}
						
						submitdueDay = Integer.parseInt(str[2]);
						
						if(isValidDay(submitdueMonth, submitdueDay) == false) {
							
							System.out.println("day error");
							return false;
							
						}
					}
					else
						return false;
				
			} catch(NumberFormatException e) {
				System.out.println("��, ��, ���� ���ڸ� �Է����ּ���.");
				return false;
			}
			
		}
		else {
			System.err.println("yyyy-mm-dd �������� �Է����ּ���.");
			return false;
		}
		return true;
	}
	
	public static boolean isValidMonth(int month) {
		if(month < Calendar.MONTH)
			return false;
		if(month < 1 || month > 12)
			return false;
		return true;
	}
	
	public static boolean isValidDay(int month, int day) {
		if(day <= Calendar.DATE)
			return false;
		
		if(month == 1 && (day < 1 || day > 31))
			return false;
		else if(month == 2 && (day < 1 || day > 28))
			return false;
		else if(month == 3 && (day < 1 || day > 31))
			return false;
		else if(month == 4 && (day < 1 || day > 30))
			return false;
		else if(month == 5 && (day < 1 || day > 31))
			return false;
		else if(month == 6 && (day < 1 || day > 30))
			return false;
		else if(month == 7 && (day < 1 || day > 31))
			return false;
		else if(month == 8 && (day < 1 || day > 31))
			return false;
		else if(month == 9 && (day < 1 || day > 30))
			return false;
		else if(month == 10 && (day < 1 || day > 31))
			return false;
		else if(month == 11 && (day < 1 || day > 30))
			return false;
		else if(month == 12 && (day < 1 || day > 31))
			return false;
		return true;
	}
	
	/*
	public boolean isOthersValidCheck(String input) {
		if(input == null || input.equals(""))
		    return false;
		return true;
	}
	*/
}
