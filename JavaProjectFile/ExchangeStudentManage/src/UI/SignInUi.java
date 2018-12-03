package UI;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;

import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.LoginInfo;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import OCSF.client.*;

public class SignInUi extends UiBase{

	Scanner scanner = new Scanner(System.in); 
	private String _id;
	private String _pw;
	private String _serverText = null;
	
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

		
		
			(new StudentMainMenuUi("MainMenuUi")).UiStart();
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
		
		if(!_serverText.equals("false"))
			ret = true;
		
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
