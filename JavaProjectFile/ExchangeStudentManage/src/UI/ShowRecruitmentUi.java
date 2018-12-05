package UI;

import DataManage.UiManage.ObjectCarrier;
import Member.Staff;
import Posts.Program;

public class ShowRecruitmentUi extends UiBase {

	public ShowRecruitmentUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	private Staff staff;
	private int programIndex;
	private Program program;
	
	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		staff = (Staff)ObjectCarrier.GetData("Staff");
		programIndex = (int)ObjectCarrier.GetData("ProgramIndex");
		program = staff.GetProgramList().get(programIndex);
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
		ObjectCarrier.SaveData("Staff", staff);
		(new ShowAllApplicationDependsOnProgram("응시원서 확인")).UiStart();
	}

}
