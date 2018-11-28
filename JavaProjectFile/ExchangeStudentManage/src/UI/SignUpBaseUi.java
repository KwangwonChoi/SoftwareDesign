package UI;

import java.io.IOException;
import java.util.Scanner;
import OCSF.client.*;

public abstract class SignUpBaseUi extends UiBase{

	protected Scanner scanner = new Scanner(System.in);
	protected String _id;
	protected String _pw;
	protected String _number;
	protected int _age;
	protected boolean _wantSignUp; 
	
	public SignUpBaseUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void OnStart() {
		// TODO Auto-generated method stub
		GetInfoProcess();
		
		if(_wantSignUp) {
			if(!SignUpToServer())
				System.out.println("이미 존재하는 ID입니다.");
		}
	}
	
	protected void GetInfoProcess() {

		PrintIdRequire();
		GetId();
		
		PrintPwRequire();
		GetPw();
		
		PrintNumberRequire();
		GetNumber();
		
		PrintAgeRequire();
		GetAge();
	}
	
	private void PrintIdRequire() {
		System.out.print("ID : ");
	}
	
	private void GetId() {
		_id = scanner.next();
	}
	private void PrintPwRequire() {
		System.out.print("Pw : ");
	}
	
	private void GetPw() {
		_pw = scanner.next();
	}
	private void PrintNumberRequire() {
		System.out.print("Student/Staff Number : ");
	}
	
	private void GetNumber() {
		_number = scanner.next();
	}
	private void PrintAgeRequire() {
		System.out.print("Age : ");
	}
	
	private void GetAge() {
		_age = scanner.nextInt();
	}
	
	protected void PrintSignUpDecisionRequire() {
		System.out.println("You really want to make account like this ? (Y/N)");
		
		System.out.println("ID : " + _id);
		System.out.println("Pw : " + _pw);
		System.out.println("Number : " + _number);
		System.out.println("Age : " + _age);
	}
	
	protected void GetSignUpDecision() {
		
		do{
		String decision = scanner.next();
			
			if(decision.toLowerCase().equals("y")) {
				_wantSignUp = true;
				break;
				}
			else if(decision.toLowerCase().equals("n")) {
				_wantSignUp = false;
				break;
			}
			else
				System.out.println("정확한 입력이 아닙니다. (Y/N)을 입력하세요.");
		}while(true);
	}

	protected boolean SignUpToServer() {
		boolean isSucceed = false;
		
		try {
			
			ClientData.GetIntance().GetClient().sendToServer(SignUpJsonInfo());
			
			if(ClientData.GetIntance().GetClient().GetstringFromServer() == "true")
				isSucceed = true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSucceed;
	}
	
	protected abstract String SignUpJsonInfo();

}
