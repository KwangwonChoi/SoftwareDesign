package UI;

import DataManage.JsonFormat.ApplicationInfo;
import DataManage.UiManage.ObjectCarrier;
import Posts.Application;
import Posts.Program;

public class ShowApplicationSetScoreUi extends UiBase{

	public ShowApplicationSetScoreUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	Program program;
	Application application;
	@Override
	protected void OnAwake() {
		
		int index = (int) ObjectCarrier.GetData("index");
		program = ((Program) ObjectCarrier.GetData("Program"));
		
		application = program.get_aList().get(index);
	}
	
	@Override
	protected void OnStart() {
		
		PrintUiName();
		
		System.out.println("Program Name : " + application.get_programName());
		
		System.out.println("Current State : " + application.get_state());
		
		System.out.println("Study Plan : " + application.get_studyPlan());
				
		System.out.println("Student Grade : " + application.get_student().GetGrade());
		
		System.out.println("Language Grade : " + application.get_langGrade());
		
		System.out.println("Score : ");
		
		application.AddScore(GetScore());
	}
	
	private float GetScore() {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		float score;
		while(true) {
			
			try {
				score = scanner.nextFloat();
				break;
			}
			catch(Exception e) {
				System.out.println("잘못된 형식입니다.");
			}
		}
		
		return score;
	}
	
	@Override
	protected void OnFinished() {
		ObjectCarrier.SaveData("Program", program);
	}
}
