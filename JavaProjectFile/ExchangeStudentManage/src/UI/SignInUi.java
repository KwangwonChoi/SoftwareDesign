package UI;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;

import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.LoginInfo;
import DataManage.JsonFormat.StaffInfo;
import DataManage.JsonFormat.StudentInfo;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import DataManage.UiManage.ObjectCarrier;
import Member.Staff;
import Member.Student;
import OCSF.client.*;

public class SignInUi extends UiBase{

	Scanner scanner = new Scanner(System.in); 
	private String _id;
	private String _pw;
	private String _serverText = null;
	private UiBase nextUi = null;
	
	public SignInUi(String uiName) {
		super(uiName);
	}
	
	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void OnStart() {
		
		boolean retry = false;
		
		do {
			
			PrintIdRequire();
			GetId();
			PrintPwRequire();
			GetPw();
			
			try {
				
				if(!ConnectToServer())
					retry = IsSignUpRetry();
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(retry);
	}
	
	@Override
	protected void OnFinished() {
		
		if(nextUi != null) {
		
			nextUi.UiStart();
			
		}
	}
	
	private void PrintIdRequire() {
		System.out.print("ID : ");
	}
	
	private void GetId() {
		_id = scanner.next();
	}
	
	private void PrintPwRequire() {
		System.out.print("PW : ");
	}
	
	private void GetPw() {
		_pw = scanner.next();
	}
	
	private boolean ConnectToServer() throws InterruptedException {
		boolean ret = false;
		
		JsonWrapper json = new JsonWrapper();
		String loginInfo = json.ToJson(SEND_TYPE.SIGNIN, new LoginInfo(_id,_pw));

		
		try {
			
			ChatClient.GetInstance().sendToServer(loginInfo);
			
			int timeCount = 0;
			
			while((_serverText = ChatClient.GetInstance().GetstringFromServer()) == null) {
				timeCount++;
				Thread.sleep(1);

				if(timeCount > 1000)
					throw new Exception();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("통신에러!");
		}
		
		Gson gson = new Gson();
		JsonWrapper jsonwrapper = JsonWrapper.FromJson(_serverText);
		
		switch(jsonwrapper.type) {
		case SIGNINSTUDENT : 
			ret = true;
			nextUi = (new StudentMainMenuUi("Student Main Menu"));
			ObjectCarrier.SaveData("Student", new Student(gson.fromJson(jsonwrapper.json, StudentInfo.class)));
			break;
		case SIGNINSTAFF :
			ret = true;
			nextUi = (new StaffMainMenuUi("Staff Main Menu"));
			ObjectCarrier.SaveData("Staff", new Staff(gson.fromJson(jsonwrapper.json, StaffInfo.class)));
			break;
		default :
			System.out.println("ID 또는 PW가 틀립니다.");
			break;
		}
						
		return ret;
	}
	
	private boolean IsSignUpRetry() {
		System.out.println("Login Failed! will you retry ? (Y/N)");
		
		String det = scanner.next();
		
		if(det.toLowerCase().equals("y"))
			return true;
		else
			return false;
	}

	
}
