package UI;

import DataManage.UiManage.ObjectCarrier;
import Posts.Program;

public class ShowRecruitmentUi extends UiBase {

	public ShowRecruitmentUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	private Program program;
	
	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		program = (Program)ObjectCarrier.GetData("Program");
	}

	@Override
	protected void OnStart() {
		// TODO Auto-generated method stub
		System.out.println("Program Name : " + program.get_name());
		
		System.out.println("Write Date : " + program.get_datetime());
		
		System.out.println("Submit Due : " + program.get_submitdue());
		
		System.out.println("University : " + program.get_university());
		
		System.out.println("Country : " + program.get_country());
		
		System.out.println("Lowest Grade : " + program.get_lowestGrade());
		
		System.out.println("Use Language : " + program.get_useLang());
		
	}
	
	@Override
	protected void OnFinished() {
		ObjectCarrier.SaveData("Program", program);
		(new ShowAllApplicationDependsOnProgram("응시원서 확인")).UiStart();
	}

}
