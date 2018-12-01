package UI;

import java.io.IOException;
import java.util.InputMismatchException;
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
			
			try {
				
				if(!SignUpToServer())
					System.out.println("�̹� �����ϴ� ID�Դϴ�.");
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
				System.out.println("��й�ȣ�� 3�ڸ� �̻��̾�� �մϴ�.");
		}
	}
	
	private boolean isValidPw(String pw) {
		boolean ret = true;
		
		if(pw.length() < 3)
			ret = false;
		
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
					System.out.println("�˸��� ���̰� �ƴմϴ�.");
			}catch(InputMismatchException e) {
				System.out.println("��ȿ���� ���� ���Դϴ�.");
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
				System.out.println("��Ȯ�� �Է��� �ƴմϴ�. (Y/N)�� �Է��ϼ���.");
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
						System.out.println("��ſ���");
					}
			}
			
			if(serverText == "true")
				isSucceed = true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSucceed;
	}
	protected abstract void SignUpSucceed();
	
	protected abstract String SignUpJsonInfo();

}
