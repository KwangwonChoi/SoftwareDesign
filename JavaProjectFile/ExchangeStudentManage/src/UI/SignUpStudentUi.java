package UI;

import com.google.gson.Gson;
import MemberInfoManage.StudentInfo;
import OCSF.common.JsonWrapper;
import OCSF.common.JsonWrapper.SEND_TYPE;

public class SignUpStudentUi extends SignUpBaseUi{

	private String _major;
	private float _grade;
	
	public SignUpStudentUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void GetInfoProcess() {
		super.GetInfoProcess();

		PrintMajorRequire();
		GetMajor();
		PrintGradeRequire();
		GetGrade();

		PrintSignUpDecisionRequire();
		GetSignUpDecision();
	}
	private void PrintMajorRequire() {
		System.out.print("Major : ");
	}
	private void GetMajor() {
		_major = scanner.next();
	}
	private void PrintGradeRequire() {
		System.out.print("Grade : ");
	}
	private void GetGrade() {
		_grade = scanner.nextFloat();
	}

	@Override 
	protected void PrintSignUpDecisionRequire() {
		super.PrintSignUpDecisionRequire();
		
		System.out.println("Major : " + _major);
		System.out.println("Grade : " + _grade);
		
	}

	@Override
	protected String SignUpJsonInfo() {
		JsonWrapper json = new JsonWrapper();
		StudentInfo student = new StudentInfo(_id,_pw,_number,_age,_major,_grade);
		
		return json.ToJson(SEND_TYPE.SIGNUPSTUDENT, student);
	}

	@Override
	protected void SignUpSucceed() {
		// TODO Auto-generated method stub
		(new StudentMainMenuUi("Student Main Menu")).UiStart();
	}

}
