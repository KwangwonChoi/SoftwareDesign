package Posts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import DataManage.JsonFormat.ApplicationInfo;
import DataManage.JsonFormat.ProgramInfo;
import Member.Staff;

public class Program {
	private Staff _staff;
	private String _staffId;
	private String _name;					// valid check error number : 1
	private PROGRAMSTATE _state;
	private String _submitdue;  			// valid check error number : 2
	private String _university;				// valid check error number : 3 
	private String _country;				// valid check error number : 4 
	private String _useLang;				// valid check error number : 5	
	private float _lowestGrade;				// valid check error number : 6 
	private String _datetime;										
	private SimpleDateFormat _transFormat;
	private int _finalPassNum; 				// valid check error number : 7
	private List<Application> _aList;
	

	public Program(String name, PROGRAMSTATE state, String submitdue, String university, String country, float lowestGrade, String useLang, int finalPassNum) {
		this._name = name;
		this._state = state;
		this._submitdue = submitdue;
		this._university = university;
		this._country = country;
		this._lowestGrade = lowestGrade;
		this._useLang = useLang;
		this._transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this._datetime = _transFormat.format(new Date());
		this._finalPassNum = finalPassNum;
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
		this._finalPassNum = pInfo.finalPassNum;
		
		_aList = new ArrayList<Application>();
		for(ApplicationInfo a : pInfo.aList) {
			_aList.add(new Application(a));
		}
	}
	
	public ProgramInfo GetProgramInfo() {
		ProgramInfo pInfo = new ProgramInfo();
		pInfo.staffId = _staffId;
		pInfo.name = this._name;
		pInfo.country = this._country;
		pInfo.datetime = this._datetime;
		pInfo.lowestGrade = this._lowestGrade;
		pInfo.state = this._state;
		pInfo.university = this._university;
		pInfo.submitdue = this._submitdue;
		pInfo.useLang = this._useLang;
		pInfo.finalPassNum = this._finalPassNum;
		
		pInfo.aList = new ArrayList<ApplicationInfo>();
		for(Application a : _aList) {
			pInfo.aList.add(a.GetApplicationInfo());
		}
	
		
		return pInfo;
	}	
	
	private void ApplicationListSort() {
		
		Collections.sort(_aList, new Comparator<Application>() {
			public int compare(Application app1, Application app2) {
				return (app1.get_score() > app2.get_score()) ? -1 : (app1.get_score() > app2.get_score()) ? 1 : 0; 
			}
		});
	}
	
	private void FinishDocumentScoreFillout() {
		ApplicationListSort();
		int aliveNum = _finalPassNum * 2;
		
		for(int i = 0 ; i < aliveNum && i < _aList.size() ; i++) {
			_aList.get(i).SetState(APPLICATIONSTATE.UNDERREVIEW);
		}
		
		for(int i = aliveNum; i < _aList.size() ; i++) {
			_aList.get(i).SetState(APPLICATIONSTATE.UNPASS);
		}
	}
	
	private void FinishInterviewScoreFillout() {
		ApplicationListSort();
		int aliveNum = _finalPassNum;
		
		for(int i = 0 ; i < aliveNum && i < _aList.size() ; i++) {
			_aList.get(i).SetState(APPLICATIONSTATE.PASS);
		}
		
		for(int i = aliveNum; i < _aList.size() ; i++) {
			_aList.get(i).SetState(APPLICATIONSTATE.UNPASS);
		}
		
	}
	
	public static Program GetProgramFromProgramInfo(ProgramInfo pInfo) {
		
		return new Program(pInfo); 
		
	}
	
	public static Program GetProgramFromProgramInfo(ProgramInfo pInfo, Staff staff) {
		
		Program p = new Program(pInfo.name, pInfo.state, pInfo.submitdue, pInfo.university, pInfo.country, pInfo.lowestGrade, pInfo.useLang, pInfo.finalPassNum);
		p.SetStaff(staff);
		return p;
	} 
	
	public static int isValidName(String name) {
		name = name.replaceAll(" ", "");
		if(name == null || name.equals("")) {
			System.err.println("이름을 입력해 주세요.");
			return 1;
		}
		
		if(Pattern.matches("^[a-zA-Z가-힣]*$", name))
			return 0;
		
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
					if(submitdueYear == curYear || submitdueYear == curYear+1) {
						submitdueMonth = Integer.parseInt(str[1]);
						if(isValidMonth(submitdueMonth, curMonth) == false){
							System.out.println("month error");
							return 2;
						}
						submitdueDay = Integer.parseInt(str[2]);
						if(isValidDay(submitdueMonth, submitdueDay, curDay) == false) {
							System.out.println("day error");
							return 2;
						}
					}
					else {
						System.err.println("제출 기한의 년도는 올해 또는 내년으로 해주십시오.");
						return 2;	
					}
				
			} catch(NumberFormatException e) {
				System.out.println("년, 월, 일은 숫자를 입력해주세요.");
				return 2;
			}
		}
		else {
			System.err.println("yyyy-mm-dd 형식으로 입력해주세요.");
			return 2;
		}
		return 0;
	}

	public static int isValidUniversity(String uni) {
		uni = uni.replaceAll(" ", "");
		if(uni == null || uni.equals("")) {
			System.err.println("대학교 이름을 입력해 주세요.");
			return 3;
		}
		
		if(Pattern.matches("^[a-zA-Z가-힣]*$", uni))
			return 0;
		
		if(uni.matches("^[0-9]*$")) {
			System.err.println("대학교 이름에는 숫자가 포함될 수 없습니다.");
			return 3;
		}
		else {
			System.err.println("대학교 이름에는 특수문자를 포함할 수 없습니다.");
			return 3;
		}
	}
	
	public static int isValidCountry(String ctry) {
		ctry = ctry.replaceAll(" ", "");
		if(ctry == null || ctry.equals("")) {
			System.err.println("국가 이름을 입력해 주세요.");
			return 4;
		}
		
		if(Pattern.matches("^[a-zA-Z가-힣]*$", ctry))
			return 0;
		
		if(ctry.matches("^[0-9]*$")) {
			System.err.println("나라 이름에는 숫자가 포함될 수 없습니다.");
			return 4;
		}
		else {
			System.err.println("나라 이름에는 특수문자를 포함할 수 없습니다.");
			return 4;
		}
	}
	
	public static int isValidUselang(String useLang) {
		useLang = useLang.replaceAll(" ", "");
		if(useLang == null || useLang.equals("")) {
			System.err.println("해당 국가 사용언어을 입력해 주세요.");
			return 5;
		}
		
		if(Pattern.matches("^[a-zA-Z가-힣]*$", useLang))
			return 0;
		
		if(useLang.matches("^[0-9]*$")) {
			System.err.println("해당 국가 사용언어에는 숫자가 포함될 수 없습니다.");
			return 5;
		}
		else {
			System.err.println("해당 국가 사용언어에는 특수문자를 포함할 수 없습니다.");
			return 5;
		}
	}
	
	public static int isLowestGradeValidCheck(float lowestGrade) {
		
		try {
			if(lowestGrade < 0 || lowestGrade > 4.5) { 
				System.err.println("학점 범위 : 0.0 ~ 4.5");
				return 6;
			}
			
		} catch (NumberFormatException e) {
			return 6;
		} catch( NullPointerException e) {
			
			return 6;
		}
		return 0;
	}
	
	public static int isValidFinalPassNum(int num) {
		
		return 0;
	}
	
	public static boolean isValidMonth(int month, int curMonth) {
		if(month < 1 || month > 12)
			return false;
		return true;
	}
	
	public static boolean isValidDay(int month, int day, int curDay) {
		if(day <= curDay) {
			System.err.println("제출 기한은 금일이거나 이전일 수 없습니다.");
			return false;	
		}
		
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
		this._staffId = staff.GetStaffInfo().id;
	}
	
	public void SetState(PROGRAMSTATE state) {
		_state = state;
	}
	
	public void NextState() {
		switch(_state) {
		case RECRUIT:
			_state = PROGRAMSTATE.DOCUEMNTVIEW; 
			break;
		case DOCUEMNTVIEW:
			_state = PROGRAMSTATE.INTERVIEWREVIEW;
			FinishDocumentScoreFillout();
			break;
		case INTERVIEWREVIEW:
			_state = PROGRAMSTATE.RESULTANNOUNCE;
			FinishInterviewScoreFillout();
			break;
		case RESULTANNOUNCE:
			_state = PROGRAMSTATE.PROGRESS;
			break;
		case PROGRESS:
			_state = PROGRAMSTATE.END;
			break;
		case END:
			break;
		}
		
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
	
	
}
