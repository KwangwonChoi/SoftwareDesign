package Posts;

import java.io.File;
import java.util.List;

import DataManage.JsonFormat.ApplicationInfo;
import Member.Student;

public class Application {
	 
	private Program _program;
	private String _programName;
	private Student _student;
	private String _studentId;
	private APPLICATIONSTATE _state;
	private float _score;
	private Object _studyPlan;
	private float _langGrade;
	
	public Application(Student std, Program program, APPLICATIONSTATE state, float score, Object studyPlan, float langGrade) {
		this._student = std;
		this._studentId = std.GetId();
		this._program = program;
		this._programName = program.get_name();
		this._state = state;
		this._score = score;
		this._studyPlan = studyPlan;
		this._langGrade = langGrade;
	}
	
	public Application(ApplicationInfo a) {
		
		this._studentId = a.studentId;
		this._programName = a.ProgramName;
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
		aInfo.ProgramName = _programName;
		aInfo.studentId = _studentId;
		aInfo.state = _state;
		aInfo.score = _score;
		aInfo.studyPlan = _studyPlan;
		aInfo.langGrade = _langGrade;
		return aInfo;
	}
	
	public void SetStudent(Student s) {
		this._student = s;
	}

	public final Program get_program() {
		return _program;
	}

	public final Student get_student() {
		return _student;
	}

	public final APPLICATIONSTATE get_state() {
		return _state;
	}

	public final float get_score() {
		return _score;
	}

	public final Object get_studyPlan() {
		return _studyPlan;
	}

	public final float get_langGrade() {
		return _langGrade;
	}
	
	public final String get_programName() {
		return _programName;
	}

}
