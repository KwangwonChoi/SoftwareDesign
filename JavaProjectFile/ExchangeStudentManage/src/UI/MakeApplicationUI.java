package UI;

import java.io.IOException;
import java.util.Scanner;

import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import DataManage.JsonFormat.StudentInfo;
import DataManage.UiManage.ObjectCarrier;
import Member.Student;
import OCSF.client.ChatClient;
import Posts.APPLICATIONSTATE;
import Posts.Application;
import Posts.Program;

public class MakeApplicationUI extends MakeUiBase {

	private Student std;
	private Program pro;
	private String appProgName;
	private String studyPlan;
	private float langGrade;
	private Scanner scan;
	
	public MakeApplicationUI(String uiName) {
		super(uiName);
		scan = new Scanner(System.in);
		// TODO Auto-generated constructor stub
	}
	
	public MakeApplicationUI SetProgram(Program pro) {
		this.pro = pro;
		return this;
	}
	
	@Override
	protected void OnAwake() {
		super.OnAwake();
		std = (Student) ObjectCarrier.GetData("Student");
		//pro = (Program) ObjectCarrier.GetData("Program");
	}
	
	@Override
	protected void OnStart() {
		int ret = 0;
		do {
			PrintProgramName();
			GetProgramName();
			ret = Application.isValidName(this.appProgName);
		}while(ret != 0);
		
		PrintStudyplanRequire();
		GetStudyPlan();

		PrintLangGradeRequire();
		GetLangGrade();
	}

	
	@Override
	protected void OnFinished() {
		super.OnFinished();
		ObjectCarrier.SaveData("Student", std);
	}
	
	@Override
	protected void SendPostsToServer() {
		// TODO Auto-generated method stub
		
		std.MakeApplication(pro, APPLICATIONSTATE.SUBMIT, 0.0f, studyPlan, langGrade);
		StudentInfo sInfo = std.GetStudentInfo();
		
		String json = JsonWrapper.ToJson(SEND_TYPE.MAKEAPPLICATION, sInfo);
		
		try {
		
			ChatClient.GetInstance().sendToServer(json);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void PrintProgramName() {
		System.out.println("�����ϴ� ���α׷� �̸��� �Է��ϼ���.");
	}

	private void PrintStudyplanRequire() {
		System.out.println("���� ��ȹ���� �ۼ��Ͻÿ� (30�� ����) : ");
	}
	
	private void GetStudyPlan() {
		studyPlan = scan.nextLine();
	}
	
	private void PrintLangGradeRequire() {
		System.out.println("���� ������ �Է��Ͻÿ� (ex. TOEIC,900 or TOEFL,110. TOEIC,TOEFL�� ����.)");
	}
	
	private void GetProgramName() {
		appProgName = scan.nextLine();
	}
	
	private void PrintInfo() {
		System.out.println(this.appProgName + " " + this.studyPlan + " " + this.langGrade);
	}
	
	private void GetLangGrade() {
		
		do {

			String tmp = _scanner.next();

			String[] str = tmp.split(",");
			float score = 0;
			
			if(str.length != 2)
			{
				System.out.println("�ùٸ� ���� �Է��ϼ��� (TOEIC,700  or TOEFL,70)");
				continue;
			}
			
			try {
				
				score = Integer.parseInt(str[1]);
				
			}catch(NumberFormatException e) {
				System.out.println("�ùٸ� ���� �Է��ϼ��� (TOEIC,700  or TOEFL,70)");
				continue;
			}
			
			switch(str[0]) {
			case "TOEIC":
				
				if(score > 990 || score < 0) {
					System.out.println("���� ������ ������ 0 ~ 990�� �Դϴ�.");
					break;
				}
				
				langGrade = (score / 990) * 100;
				return;
				
			case "TOEFL":
				if(score > 120 || score < 0) {
					System.out.println("���� ������ ������ 0 ~ 990�� �Դϴ�.");
					break;
				}
				
				langGrade = (score / 120) * 100;
				return;
			
			default : System.out.println("�ùٸ��� ���� ���Դϴ�.");
			}
			
		}while(true);
		
	}
	
	public static void main(String[] args) {
		MakeApplicationUI test = new MakeApplicationUI("test");
		test.OnStart();
		test.PrintInfo();
	}
	
}
