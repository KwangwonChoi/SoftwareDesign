package UI;

import java.util.InputMismatchException;

import com.google.gson.Gson;

import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.StudentInfo;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;

public class SignUpStudentUi extends SignUpBaseUi{

	private int _year;
	private String _major;
	private float _grade;
	
	public SignUpStudentUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void GetInfoProcess() {
		super.GetInfoProcess();

		GetNumber();
		
		GetYear();
		
		GetMajor();
		
		GetGrade();

		PrintSignUpDecisionRequire();
		GetSignUpDecision();
	}
	
	private void PrintNumberRequire() {
		System.out.print("Student Number : ");
	}
	
	private void GetNumber() {
		while(true) {
			PrintNumberRequire();
			_number = scanner.next();
			
			if(isValidNumber(_number))
				break;
			else
				System.out.println("학번이 아닙니다.");
		}
	}
	
	private boolean isValidNumber(String number) {
		boolean ret = true;
		
		if(number.length() != 10)
			ret = false;
		
		return ret;
	}
	
	private void PrintYearRequire() {
		System.out.print("Student Year : ");
	}
	
	private void GetYear() {
		while(true) {
			PrintYearRequire();
			try {
				_year = scanner.nextInt();
			
				if(isValidYear(_year) == 0)
					break;
				
				else if(isValidYear(_year) == 1)
					System.out.println("2학기 이상 등록한 학생이어야 합니다.");
				
				else
					System.out.println("올바른 학년이 아닙니다.");
				
			}catch(InputMismatchException e){
				System.out.println("유효하지 않은 값입니다.");
			}
		}
	}
	
	private int isValidYear(Integer year) {
		int ret = 0;
		
		if(year < 2)
			ret = 1;
		else if(year > 4)
			ret = 2;
		
		return ret;
	}
	
	private void PrintMajorRequire() {
		System.out.print("Major : ");
	}
	private void GetMajor() {
		PrintMajorRequire();
		
		_major = scanner.next();
	}
	
	private void PrintGradeRequire() {
		System.out.print("Grade : ");
	}
	
	private void GetGrade() {
		while(true) {
			PrintGradeRequire();
			
			try {
				_grade = scanner.nextFloat();

				if (isValidGrade(_grade))
					break;
				else
					System.out.println("학점이 올바르지 않습니다.");
			
			}catch(InputMismatchException e) {
				System.out.println("유효하지 않은 값입니다.");
			}
		}
	}

	private boolean isValidGrade(Float grade) {
		boolean ret = true;
		
		if(grade > 4.5)
			ret = false;
		
		return ret;
	}
	
	@Override 
	protected void PrintSignUpDecisionRequire() {
		super.PrintSignUpDecisionRequire();
		
		System.out.println("Year : " + _year);
		System.out.println("Major : " + _major);
		System.out.println("Grade : " + _grade);
	}

	@Override
	protected String SignUpJsonInfo() {
		JsonWrapper json = new JsonWrapper();
		StudentInfo student = new StudentInfo(_id,_pw,_name,_number,_age, _year, _major,_grade);
		
		return json.ToJson(SEND_TYPE.SIGNUPSTUDENT, student);
	}


}
