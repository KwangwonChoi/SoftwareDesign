package UI;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
		_scanner = new Scanner(System.in);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void OnAwake() {
		super.OnAwake();
		staff = (Staff) ObjectCarrier.GetData("Staff");
	}
	
	@Override
	protected void OnStart() {
	String temp = null;
	int ret = 0;
	
		PrintUiName();
	
		do {
		PrintNameRequire();
		GetName();
		ret = Program.isValidName(this.programName);
		}while(ret != 0);
		
		do {
			PrintSubmitdueRequire();
			GetSubmitDue();
			ret = Program.isSubmitDueValidCheck(this.submitdue);
		}while(ret != 0);
		
		do {
			PrintUniversityRequire();
			GetUniversity();
			ret = Program.isValidUniversity(this.university);
		}while(ret != 0);
		
		do {
			PrintCountryRequire();
			GetCountry();
			ret = Program.isValidCountry(this.country);
		}while(ret != 0);
		
		do {
			PrintLowestGradeRequire();
			GetLowestGrade();
			ret = Program.isLowestGradeValidCheck(lowestGrade);
		}while(ret != 0);
		
		do {
			PrintUseLangRequire();
			GetUseLang();
			ret = Program.isValidUselang(this.useLang);
		}while(ret != 0);

		do {
			PrintFinalPassNumRequire();
			GetFinalPassNum();
			ret = Program.isValidFinalPassNum(this.finalPassNum);
		}while(ret != 0);

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
	//
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
		boolean re = true;
		while(re) {
			try {
				lowestGrade = _scanner.nextFloat();
				re = false;
			}catch(Exception e) {
				
			}
		}
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
		boolean re = true;
		while(re) {
			try {
				finalPassNum = _scanner.nextInt();
				re = false;
			}catch(Exception e) {
				
			}
		}
	}

}
