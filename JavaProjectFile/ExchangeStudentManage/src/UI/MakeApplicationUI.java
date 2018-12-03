package UI;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import DataManage.UiManage.ObjectCarrier;
import Member.Student;
import OCSF.client.ChatClient;
import Posts.APPLICATIONSTATE;
import Posts.Application;
import Posts.Program;

public class MakeApplicationUI extends MakeUiBase {

	private Student std;
	private Program pro;
	private String studyPlan;
	private float langGrade;
	public MakeApplicationUI(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void OnAwake() {
		super.OnAwake();
		std = (Student) ObjectCarrier.GetData("Student");
		pro = (Program) ObjectCarrier.GetData("Program");
	}
	
	@Override
	protected void OnStart() {
		// TODO Auto-generated method stub
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
		String json = JsonWrapper.ToJson(SEND_TYPE.MAKEAPPLICATION, std.MakeApplication(pro, APPLICATIONSTATE.SUBMIT, 0.0f, studyPlan, langGrade));
		
		try {
		
			ChatClient.GetInstance().sendToServer(json);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void PrintStudyplanRequire() {
		System.out.println("���� ��ȹ���� �ۼ��Ͻÿ� (30�� ����) : ");
	}
	
	private void GetStudyPlan() {
		studyPlan = _scanner.next();
	}
	
	private void PrintLangGradeRequire() {
		System.out.println("���� ������ �Է��Ͻÿ� (ex. TOEIC,900 or TOEFL,110. TOEIC,TOEFL�� ����.)");
	}
	
	private void GetLangGrade() {
		
		do {
			String tmp = _scanner.next();
			String[] str = tmp.split(",");
			float score;
			
			if(str.length != 2)
			{
				System.out.println("�ùٸ� ���� �Է��ϼ��� (TOEIC,700  or TOEFL,70)");
				break;
			}
			
			try {
				
				score = Integer.parseInt(str[1]);
				
			}catch(NumberFormatException e) {
				System.out.println("�ùٸ� ���� �Է��ϼ��� (TOEIC,700  or TOEFL,70)");
				break;
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
			
			default : System.out.println("�ùٸ��� ���� ���Դϴ�.");
			}
			
		}while(true);
		
	}
	
}
