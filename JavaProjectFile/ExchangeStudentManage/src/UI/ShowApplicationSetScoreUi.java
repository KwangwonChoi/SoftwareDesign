package UI;

import DataManage.JsonFormat.ApplicationInfo;
import DataManage.JsonFormat.ApplicationListInfo;
import DataManage.UiManage.ObjectCarrier;
import Posts.Application;

public class ShowApplicationSetScoreUi extends UiBase{

	public ShowApplicationSetScoreUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	ApplicationInfo app;
	
	@Override
	protected void OnAwake() {
		
		int index = (int) ObjectCarrier.GetData("index");
		app = ((ApplicationListInfo) ObjectCarrier.GetData("ApplicationList")).a.get(index);
		
	}
	
	@Override
	protected void OnStart() {
		
		System.out.println("Program Name : " + app.ProgramName);
		
		System.out.println("Current State : " + app.state);
		
		System.out.println("Study Plan : " + app.studyPlan);
				
		System.out.println("Student Grade : " + app.grade);
		
		System.out.println("Language Grade : " + app.langGrade);
		
		System.out.println("Score : ");
		app.score += GetScore();
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
	
}
