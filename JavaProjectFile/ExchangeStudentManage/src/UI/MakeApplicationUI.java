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
		System.out.println("수학 계획서를 작성하시오 (30자 내외) : ");
	}
	
	private void GetStudyPlan() {
		studyPlan = _scanner.next();
	}
	
	private void PrintLangGradeRequire() {
		System.out.println("영어 성적을 입력하시오 (ex. TOEIC,900 or TOEFL,110. TOEIC,TOEFL만 인정.)");
	}
	
	private void GetLangGrade() {
		
		do {
			String tmp = _scanner.next();
			String[] str = tmp.split(",");
			float score;
			
			if(str.length != 2)
			{
				System.out.println("올바른 값을 입력하세요 (TOEIC,700  or TOEFL,70)");
				break;
			}
			
			try {
				
				score = Integer.parseInt(str[1]);
				
			}catch(NumberFormatException e) {
				System.out.println("올바른 값을 입력하세요 (TOEIC,700  or TOEFL,70)");
				break;
			}
			
			switch(str[0]) {
			case "TOEIC":
				
				if(score > 990 || score < 0) {
					System.out.println("토익 점수의 범위는 0 ~ 990점 입니다.");
					break;
				}
				
				langGrade = (score / 990) * 100;
				return;
				
			case "TOEFL":
				if(score > 120 || score < 0) {
					System.out.println("토플 점수의 범위는 0 ~ 990점 입니다.");
					break;
				}
				
				langGrade = (score / 120) * 100;
			
			default : System.out.println("올바르지 않은 값입니다.");
			}
			
		}while(true);
		
	}
	
}
