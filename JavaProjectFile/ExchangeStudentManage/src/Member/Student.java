package Member;

import java.util.*;

import DataManage.JsonFormat.ApplicationInfo;
import DataManage.JsonFormat.MemberInfo;
import DataManage.JsonFormat.StudentInfo;
import Posts.APPLICATIONSTATE;
import Posts.Application;
import Posts.Program;

public class Student extends Member {

	private int _year;
	private  String _major;
	private  float _grade;

	private List<Application> aList;
	
	public Student(String id, String pw, String name, String number, int age, int year, String major, float grade) {
		super(id, pw, name, number, age);
		
		this._year = year;
		this._major = major;
		this._grade = grade;
		
		aList = new ArrayList<Application>();
	}
	
	public Student(StudentInfo std) {
		super((MemberInfo)std);
		
		this._year = std.year;
		this._major = std.major;
		this._grade = std.grade;
		
		aList = new ArrayList<Application>();
		for(ApplicationInfo a : std.aList) {
			aList.add(Application.GetApplicationFromApplicationInfo(a));
		}
	}
	
	public Application MakeApplication(Program pro, APPLICATIONSTATE state, float score, Object studyPlan, float langGrade) {
		Application a = new Application(pro, state,  score, studyPlan, langGrade);
		a.SetStudent(this);
		aList.add(a);
		
		return a;
	}
	
	public void MakeApplication(Application a) {
		a.SetStudent(this);
		aList.add(a);
	}
	
	public String GetId() {
		return _id;
	}

}