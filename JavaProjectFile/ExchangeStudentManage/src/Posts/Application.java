package Posts;

import java.io.File;
import java.util.List;

import DataManage.JsonFormat.ApplicationInfo;
import Member.Student;

public class Application {
	 
	private Program _program;
	private Student _student;
	private APPLICATIONSTATE _state;
	private float _score;
	private Object _studyPlan;
	private float _langGrade;
	
	public Application(Program program, APPLICATIONSTATE state, float score, Object studyPlan, float langGrade) {
		this._program = program;
		this._state = state;
		this._score = score;
		this._studyPlan = studyPlan;
		this._langGrade = langGrade;
	}
	
	public Application(ApplicationInfo a) {
		
		this._state = a.state;
		this._score = a.score;
		this._studyPlan = a.studyPlan;
		this._langGrade = a.langGrade;		
	}
	
	public static Application GetApplicationFromApplicationInfo(ApplicationInfo a) {
	
		Application app = new Application(a);
					
		return app;
	}
	
	public static Application GetApplicationFromApplicationInfo(ApplicationInfo a, Student stdnt) {
		
		Application app = Application.GetApplicationFromApplicationInfo(a);
		app.SetStudent(stdnt);
				
		return app;
	}
	
	public ApplicationInfo GetApplicationInfo() {
		ApplicationInfo aInfo = new ApplicationInfo();
		aInfo.studentId = _student.GetId();
		aInfo.state = _state;
		aInfo.score = _score;
		aInfo.studyPlan = _studyPlan;
		aInfo.langGrade = _langGrade;
		return aInfo;
	}
	
	public void SetStudent(Student s) {
		this._student = s;
	}
	

}
