package UI;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import MemberInfoManage.*;
import OCSF.client.*;

public class SignInUi extends UiBase{

	Scanner scanner = new Scanner(System.in); 
	private String _id;
	private String _pw;
	
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
				
				else
					(new MainMenuUi("MainMenuUi")).UiStart();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(retry);
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
		
		Gson gson = new Gson();
		String loginInfo = gson.toJson(new LoginInfo(_id,_pw));
		String serverText = null;
		
		try {
			
			ClientData.GetIntance().GetClient().sendToServer(loginInfo);
			
			int timeCount = 0;
			while((serverText = ClientData.GetIntance().GetClient().GetstringFromServer()) == null) {
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
		
		if(!serverText.equals("no member"))
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
