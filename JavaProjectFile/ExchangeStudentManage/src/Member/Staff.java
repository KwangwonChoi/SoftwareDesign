package Member;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import DataManage.JsonFormat.MemberInfo;

import DataManage.JsonFormat.ProgramInfo;
import DataManage.JsonFormat.StaffInfo;
import Posts.PROGRAMSTATE;
import Posts.Program;

public class Staff extends Member{
	private String department;
	private List<Program> pList;
	
	public Staff(String id, String pw, String name, String number, int age, String department) {
		super(id, pw, name, number, age);
	
		this.department = department;
		pList = new ArrayList<Program>();
	}
	
	public Staff(StaffInfo stff) {
		super((MemberInfo) stff);
		
		this.department = stff.department;
		
		pList = new ArrayList<Program>();
		for(ProgramInfo p : stff.pList) {
			pList.add(Program.GetProgramFromProgramInfo(p));
		}	
	}
	
	public Program MakeProgram(String name, PROGRAMSTATE state, String submitdue, String university, String country, float lowestGrade, String useLang) {
		Program p = new Program(name, state, submitdue, university, country, lowestGrade, useLang); 
		p.SetStaff(this);
		pList.add(p);
		
		return p;
	}
	
	public Program MakeProgram(Program pro) {
		pro.SetStaff(this);
		pList.add(pro);
		
		return pro;
	}
	
	public static boolean IsValidLowestGrade(String lowestGrade) {
		double grade;
		try {
			grade = Double.parseDouble(lowestGrade);
			if(grade < 0 || grade > 4.5) { 
				System.err.println("학점 범위 : 0.0 ~ 4.5");
				return false;
			}
			
		} catch (NumberFormatException e) {
			return false;
		} catch( NullPointerException e) {
			System.err.println("입력 형식이 다릅니다.(Ex. 3.3)");
			return false;
		}
		return true;
	}
	
	public static boolean IsSubmitDueValidCheck(String submitdue) {
		
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
						
						if(IsValidMonth(submitdueMonth) == false){
							
							System.out.println("month error");
							return false;
							
						}
						
						submitdueDay = Integer.parseInt(str[2]);
						
						if(IsValidDay(submitdueMonth, submitdueDay) == false) {
							
							System.out.println("day error");
							return false;
							
						}
					}
					else
						return false;
				
			} catch(NumberFormatException e) {
				System.out.println("년, 월, 일은 숫자를 입력해주세요.");
				return false;
			}
			
		}
		else {
			System.err.println("yyyy-mm-dd 형식으로 입력해주세요.");
			return false;
		}
		return true;
	}
	
	private static boolean IsValidMonth(int month) {
		if(month < Calendar.MONTH)
			return false;
		if(month < 1 || month > 12)
			return false;
		return true;
	}
	
	private static boolean IsValidDay(int month, int day) {
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
	
	public String GetId() {
		return _id;
	}
}
