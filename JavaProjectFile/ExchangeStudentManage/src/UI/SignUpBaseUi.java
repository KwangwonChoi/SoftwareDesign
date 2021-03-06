package UI;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.Gson;

import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.StaffInfo;
import DataManage.JsonFormat.StudentInfo;
import DataManage.UiManage.ObjectCarrier;
import Member.Staff;
import Member.Student;
import OCSF.client.*;

public abstract class SignUpBaseUi extends UiBase{

	protected Scanner scanner = new Scanner(System.in);
	protected String _id;
	protected String _pw;
	protected String _name;
	protected String _number;
	protected int _age;
	protected boolean _wantSignUp; 
	protected UiBase nextUi;
	
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
		PrintUiName();
		// TODO Auto-generated method stub
		GetInfoProcess();
	}
	
	@Override
	protected void OnFinished() {
		
		if(_wantSignUp) {
					
			try {
				
				if(!SignUpToServer())
					System.out.println("이미 존재하는 ID입니다.");
				else
					SignUpSucceed();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
	}
	
	protected void GetInfoProcess() {

		GetId();
		
		GetPw();
		
		GetName();
		
		GetAge();
	}
	
	private void PrintIdRequire() {
		System.out.print("ID : ");
	}
	
	private void GetId() {
		while (true) {
			PrintIdRequire();
			_id = scanner.next();

			if (isValidId(_id))
				break;
			else
				System.out.println("");
		}
	}
	
	private boolean isValidId(String id) {
		boolean ret = true;
		
		return ret;
	}
	
	private void PrintPwRequire() {
		System.out.print("Pw : ");
	}
	
	private void GetPw() {
		while(true) {
			PrintPwRequire();
			_pw = scanner.next();

			if (isValidPw(_pw))
				break;
			else
				System.out.println("비밀번호는 3자리 이상 20자리 이하이어야 합니다.");
		}
	}
	
	private boolean isValidPw(String pw) {
		boolean ret = true;
		
		if(pw.length() < 3 || pw.length() > 20)
			ret = false;
		
		return ret;
	}
	
	private void PrintNameRequire() {
		System.out.print("Name : ");
	}
	
	private void GetName() {
		while(true) {
			PrintNameRequire();
			_name = scanner.next();
			
			if (isValidName(_name))
				break;
			else
				System.out.println("");
		}
	}
	
	private boolean isValidName(String name) {
		boolean ret = true;
		
		return ret;
	}
	
	private void PrintAgeRequire() {
		System.out.print("Age : ");
	}
	
	private void GetAge() {
		while(true) {
			try {
				PrintAgeRequire();
				_age = scanner.nextInt();

				if (isValidAge(_age))
					break;
				else
					System.out.println("알맞은 나이가 아닙니다.");
			}catch(InputMismatchException e) {
				System.out.println("유효하지 않은 값입니다.");
			}
		}
	}
	
	private boolean isValidAge(Integer age) {
		boolean ret = true;
		
		if(age < 15 || age > 200)
			ret = false;
		return ret;
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

	protected boolean SignUpToServer() throws InterruptedException {
		boolean isSucceed = false;
		String serverText = null;
		int timeCount = 0;
		
		try {
			
			ChatClient.GetInstance().sendToServer(SignUpJsonInfo());
			
			while((serverText = ChatClient.GetInstance().GetstringFromServer()) == null) {
				timeCount++;
				Thread.sleep(1);

				if(timeCount > 1000)
					try {
						throw new Exception();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("통신에러");
						return false;
					}
			}
			
			Gson gson = new Gson();
			JsonWrapper jsonwrapper = JsonWrapper.FromJson(serverText);
			
			switch(jsonwrapper.type) {
			case SIGNUPSTUDENT : 
				isSucceed = true;
				nextUi = (new StudentMainMenuUi("Student Main Menu"));
				ObjectCarrier.SaveData("Student", new Student(gson.fromJson(jsonwrapper.json, StudentInfo.class)));
				break;
			case SIGNUPSTAFF :
				isSucceed = true;
				nextUi = (new StaffMainMenuUi("Staff Main Menu"));
				ObjectCarrier.SaveData("Staff", new Staff(gson.fromJson(jsonwrapper.json, StaffInfo.class)));
				break;
			default :
				System.out.println("통신 에러!");
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSucceed;
	}
	
	protected void SignUpSucceed() {
		nextUi.UiStart();
	}
	
	protected abstract String SignUpJsonInfo();

}
