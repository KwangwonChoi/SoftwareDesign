package UI;

import java.io.File;
import java.io.IOException;
import java.util.List;

import DataManage.JsonFormat.*;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import DataManage.UiManage.ObjectCarrier;
import Member.Staff;
import OCSF.client.ChatClient;
import Posts.APPLICATIONSTATE;
import Posts.PROGRAMSTATE;
import Posts.Program;


public class MakeRecruitmentUi extends MakeUiBase{
	
	private Staff staff;
	private String programName;
	private String submitdue;
	private String university;
	private String country;
	private float lowestGrade;
	private String useLang;
	private int finalPassNum;

	public MakeRecruitmentUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void OnAwake() {
		super.OnAwake();
		staff = (Staff) ObjectCarrier.GetData("Staff");
	}
	
	@Override
	protected void OnStart() {
		
		PrintNameRequire();
		GetName();
		
		PrintSubmitdueRequire();
		GetSubmitDue();
		
		PrintUniversityRequire();
		GetUniversity();
		
		PrintCountryRequire();
		GetCountry();
		
		PrintLowestGradeRequire();
		GetLowestGrade();
		
		PrintUseLangRequire();
		GetUseLang();
		
		PrintFinalPassNumRequire();
		GetFinalPassNum();
	}
	
	@Override
	protected void OnFinished() {
		super.OnFinished();
		ObjectCarrier.SaveData("Staff", staff);
	}

	@Override
	protected void SendPostsToServer() {
		// TODO Auto-generated method stub
		
		staff.MakeProgram(programName, PROGRAMSTATE.RECRUIT, submitdue, university, country, lowestGrade, useLang,
				finalPassNum);
		StaffInfo sInfo = staff.GetStaffInfo();
		
		String json = JsonWrapper.ToJson(SEND_TYPE.MAKERECRUITMENT, sInfo);
		
		try {
		
			ChatClient.GetInstance().sendToServer(json);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void PrintNameRequire() {
		System.out.println("Program Name : ");
	}
	private void GetName() {
		programName = _scanner.next();
	}
	private void PrintSubmitdueRequire() {
		System.out.println("Submit Due : ");
	}
	private void GetSubmitDue() {
		submitdue = _scanner.next();
	}
	private void PrintUniversityRequire() {
		System.out.println("University : ");
	}
	private void GetUniversity() {
		university = _scanner.next();
	}
	private void PrintCountryRequire() {
		System.out.println("Country : ");
	}
	private void GetCountry() {
		country = _scanner.next();
	}
	private void PrintLowestGradeRequire() {
		System.out.println("LowestGrade : ");
	}
	private void GetLowestGrade() {
		lowestGrade = _scanner.nextFloat();
	}
	private void PrintUseLangRequire() {
		System.out.println("UseLang : ");
	} 
	private void GetUseLang() {
		useLang = _scanner.next();
	}
	private void PrintFinalPassNumRequire() {
		System.out.print("Final Pass Num : ");
	} 
	private void GetFinalPassNum() {
		finalPassNum = _scanner.nextInt();
	}
	

}
