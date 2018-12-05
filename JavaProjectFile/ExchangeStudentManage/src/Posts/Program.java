package Posts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import DataManage.JsonFormat.ProgramInfo;
import Member.Staff;

public class Program {
	private Staff _staff;
	private String _name;					// valid check error number : 1
	private PROGRAMSTATE _state;
	private String _submitdue;  			// valid check error number : 2
	private String _university;				// valid check error number : 3 
	private String _country;				// valid check error number : 4 
	private String _useLang;				// valid check error number : 5	
	private float _lowestGrade;				// valid check error number : 6 
	private String _datetime;										
	private SimpleDateFormat _transFormat;
	private List<Application> _aList;
	
	public Program(String name, PROGRAMSTATE state, String submitdue, String university, String country, float lowestGrade, String useLang) {
		this._name = name;											
		this._state = state;
		this._submitdue = submitdue;
		this._university = university;
		this._country = country;
		this._lowestGrade = lowestGrade;
		this._useLang = useLang;
		this._transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this._datetime = _transFormat.format(new Date());
		this._aList = new ArrayList<Application>(); 
	}
	
	public Program(ProgramInfo pInfo) {
		this._name = pInfo.name;
		this._state = pInfo.state;
		this._submitdue = pInfo.submitdue;
		this._university = pInfo.university;
		this._country = pInfo.country;
		this._lowestGrade = pInfo.lowestGrade;
		this._useLang = pInfo.useLang;
		this._datetime = pInfo.datetime;
	}
	
	public ProgramInfo GetProgramInfo() {
		ProgramInfo pInfo = new ProgramInfo();
		pInfo.staffId = _staff.GetId();
		pInfo.name = this._name;
		pInfo.country = this._country;
		pInfo.datetime = this._datetime;
		pInfo.lowestGrade = this._lowestGrade;
		pInfo.state = this._state;
		pInfo.university = this._university;
		pInfo.submitdue = this._submitdue;
		pInfo.useLang = this._useLang;
		return pInfo;
	}
	
	public static Program GetProgramFromProgramInfo(ProgramInfo pInfo) {
		
		return new Program(pInfo.name, pInfo.state, pInfo.submitdue, pInfo.university, pInfo.country, pInfo.lowestGrade, pInfo.useLang); 
	}
	
	public static Program GetProgramFromProgramInfo(ProgramInfo pInfo, Staff staff) {
		
		Program p = new Program(pInfo.name, pInfo.state, pInfo.submitdue, pInfo.university, pInfo.country, pInfo.lowestGrade, pInfo.useLang);
		p.SetStaff(staff);
		return p;
	} 
	
	public static int isValidName(String name) {
		name = name.replaceAll(" ", "");
		if(name == null || name.equals("")) {
			System.err.println("ÀÌ¸§À» ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			return 1;
		}
		
		if(Pattern.matches("^[a-zA-Z°¡-ÆR]*$", name))
			return 0;
		
		if(name.matches("^[0-9]*$")) {
			System.err.println("ÀÌ¸§¿¡´Â ¼ýÀÚ°¡ Æ÷ÇÔµÉ ¼ö ¾ø½À´Ï´Ù.");
			return 1;
		}
		else {
			System.err.println("ÀÌ¸§¿¡´Â Æ¯¼ö¹®ÀÚ¸¦ Æ÷ÇÔÇÒ ¼ö ¾ø½À´Ï´Ù.");
			return 1;
		}
	}

	public static int isValidUniversity(String uni) {
		uni = uni.replaceAll(" ", "");
		if(uni == null || uni.equals("")) {
			System.err.println("´ëÇÐ±³ ÀÌ¸§À» ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			return 3;
		}
		
		if(Pattern.matches("^[a-zA-Z°¡-ÆR]*$", uni))
			return 0;
		
		if(uni.matches("^[0-9]*$")) {
			System.err.println("´ëÇÐ±³ ÀÌ¸§¿¡´Â ¼ýÀÚ°¡ Æ÷ÇÔµÉ ¼ö ¾ø½À´Ï´Ù.");
			return 3;
		}
		else {
			System.err.println("´ëÇÐ±³ ÀÌ¸§¿¡´Â Æ¯¼ö¹®ÀÚ¸¦ Æ÷ÇÔÇÒ ¼ö ¾ø½À´Ï´Ù.");
			return 3;
		}
	}
	
	public static int isValidCountry(String ctry) {
		ctry = ctry.replaceAll(" ", "");
		if(ctry == null || ctry.equals("")) {
			System.err.println("±¹°¡ ÀÌ¸§À» ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			return 4;
		}
		
		if(Pattern.matches("^[a-zA-Z°¡-ÆR]*$", ctry))
			return 0;
		
		if(ctry.matches("^[0-9]*$")) {
			System.err.println("³ª¶ó ÀÌ¸§¿¡´Â ¼ýÀÚ°¡ Æ÷ÇÔµÉ ¼ö ¾ø½À´Ï´Ù.");
			return 4;
		}
		else {
			System.err.println("³ª¶ó ÀÌ¸§¿¡´Â Æ¯¼ö¹®ÀÚ¸¦ Æ÷ÇÔÇÒ ¼ö ¾ø½À´Ï´Ù.");
			return 4;
		}
	}
	
	public static int isValidUselang(String useLang) {
		useLang = useLang.replaceAll(" ", "");
		if(useLang == null || useLang.equals("")) {
			System.err.println("ÇØ´ç ±¹°¡ »ç¿ë¾ð¾îÀ» ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			return 5;
		}
		
		if(Pattern.matches("^[a-zA-Z°¡-ÆR]*$", useLang))
			return 0;
		
		if(useLang.matches("^[0-9]*$")) {
			System.err.println("ÇØ´ç ±¹°¡ »ç¿ë¾ð¾î¿¡´Â ¼ýÀÚ°¡ Æ÷ÇÔµÉ ¼ö ¾ø½À´Ï´Ù.");
			return 5;
		}
		else {
			System.err.println("ÇØ´ç ±¹°¡ »ç¿ë¾ð¾î¿¡´Â Æ¯¼ö¹®ÀÚ¸¦ Æ÷ÇÔÇÒ ¼ö ¾ø½À´Ï´Ù.");
			return 5;
		}
	}
	
	public static int isLowestGradeValidCheck(String lowestGrade) {
		double grade;
		lowestGrade = lowestGrade.replaceAll(" ", "");
		try {
			grade = Double.parseDouble(lowestGrade);
			if(grade < 0 || grade > 4.5) { 
				System.err.println("ÇÐÁ¡ ¹üÀ§ : 0.0 ~ 4.5");
				return 6;
			}
			
		} catch (NumberFormatException e) {
			return 6;
		} catch( NullPointerException e) {
			System.err.println("ÀÔ·Â Çü½ÄÀÌ ´Ù¸¨´Ï´Ù.(Ex. 3.3)");
			return 6;
		}
		return 0;
	}
	
	public static int isSubmitDueValidCheck(String submitdue) {
		Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		String pattern = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
		int submitdueYear, submitdueMonth, submitdueDay;
		int curMonth, curDay;
		curMonth = cal.get(Calendar.MONTH) + 1;
		curDay = cal.get(Calendar.DATE);
		
		if(Pattern.matches(pattern, submitdue)) {
			try {
					String[] str = submitdue.split("-");
					submitdueYear = Integer.parseInt(str[0]);
					submitdueDay = Integer.parseInt(str[2]);
					if(submitdueYear == curYear || submitdueYear == curYear+1) {
						submitdueMonth = Integer.parseInt(str[1]);
						submitdueDay = Integer.parseInt(str[2]);
						
						if(submitdueYear == curYear) {
							if(isValidMonth(submitdueMonth, curMonth) == false) {
								System.out.println("month error");
								return 2;
							}
							
							if(isValidDay(submitdueMonth, submitdueDay, curDay) == false) {
								System.out.println("day error");
								return 2;
							}
						}
						else{ 
							if(isValidMonth(submitdueMonth) == false){
								System.out.println("month error");
								return 2;
							}
							if(isValidDay(submitdueMonth, submitdueDay) == false) {
								System.out.println("day error");
								return 2;
							}
						}
					}
					else {
						System.err.println("Á¦Ãâ ±âÇÑÀÇ ³âµµ´Â ¿ÃÇØ ¶Ç´Â ³»³âÀ¸·Î ÇØÁÖ½Ê½Ã¿À.");
						return 2;	
					}
				
			} catch(NumberFormatException e) {
				System.out.println("³â, ¿ù, ÀÏÀº ¼ýÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				return 2;
			}
		}
		else {
			System.err.println("yyyy-mm-dd Çü½ÄÀ¸·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			return 2;
		}
		return 0;
	}
	
	public static boolean isValidMonth(int month, int curMonth) {
		if(month < curMonth)
			return false;
		if(month < 1 || month > 12)
			return false;
		return true;
	}
	public static boolean isValidMonth(int month) {
		if(month < 1 || month > 10)
			return false;
		return true;
	}
	
	public static boolean isValidDay(int month, int day, int curDay) {
		if(day <= curDay) {
			System.err.println("Á¦Ãâ ±âÇÑÀº ±ÝÀÏÀÌ°Å³ª ÀÌÀüÀÏ ¼ö ¾ø½À´Ï´Ù.");
			return false;	
		}
		return CheckMonth(month, day);
	}
	
	public static boolean isValidDay(int month, int day) {
		return CheckMonth(month, day);
	}
	
	public static boolean CheckMonth(int month, int day) {
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
	

	public void SetStaff(Staff staff) {
		// TODO Auto-generated method stub
		this._staff = staff;
	}
	
	public String GetName() {
		return _name;
	}

	public final Staff get_staff() {
		return _staff;
	}

	public final String get_name() {
		return _name;
	}

	public final PROGRAMSTATE get_state() {
		return _state;
	}

	public final String get_submitdue() {
		return _submitdue;
	}

	public final String get_university() {
		return _university;
	}

	public final String get_country() {
		return _country;
	}

	public final float get_lowestGrade() {
		return _lowestGrade;
	}

	public final String get_useLang() {
		return _useLang;
	}

	public final String get_datetime() {
		return _datetime;
	}

	public final List<Application> get_aList() {
		return _aList;
	}
	
	/*public static void main(String args[]) {
		int ret = isSubmitDueValidCheck("2018-12-29");
		if(ret == 0)
			System.out.println("good");
		else
			System.out.println("bad");
	}*/	
}
