package Posts;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import DataManage.JsonFormat.ApplicationInfo;
import Member.Student;

public class Application {
	 
	private Program _program;

	private String _programName;
	private Student _student;
	private String _studentId;
	private APPLICATIONSTATE _state;
	private float _score;					// valid check error number : 2
	private Object _studyPlan;
	private float _langGrade;				// valid check error number : 3
	
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
		
		if(a.student != null)
			this._student = new Student(a.student);
		
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
	
	public void SetState(APPLICATIONSTATE state) {
		_state = state;
	}

	public void AddScore(float score) {
		_score += score;
		_state = APPLICATIONSTATE.EVALUATED;
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
	
	public static int isValidScore(String score) {
		float g;
		score = score.replaceAll(" ", "");
		try {
			g = Float.parseFloat(score);
		}catch(NumberFormatException e) {
			System.err.println("¼ýÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			return 2;
		}catch(NullPointerException e) {
			System.err.println("Á¡¼ö¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			return 2;
		}
		return 0;
	}
	
	public static int isValidLangGrade(String langGrade) {
		float g;
		langGrade = langGrade.replaceAll(" ", "");
		try {
			g = Float.parseFloat(langGrade);
		}catch(NumberFormatException e) {
			System.err.println("¼ýÀÚ¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			return 3;
			
		}catch(NullPointerException e) {
			System.err.println("¾îÇÐ ¼ºÀûÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
			return 3;
		}
		return 0;

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
