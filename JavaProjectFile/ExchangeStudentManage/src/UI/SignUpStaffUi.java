package UI;

import com.google.gson.Gson;

import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.StaffInfo;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import OCSF.common.*;

public class SignUpStaffUi extends SignUpBaseUi{

	private String _department;

	public SignUpStaffUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void GetInfoProcess() {
		super.GetInfoProcess();

		GetNumber();
		
		PrintDepartmentRequire();
		GetDepartment();
		
		PrintSignUpDecisionRequire();
		GetSignUpDecision();
	}
	
	private void PrintNumberRequire() {
		System.out.print("Staff Number : ");
	}
	
	private void GetNumber() {
		while(true) {
			PrintNumberRequire();
			
			_number = scanner.next();
			
			if(isValidNumber(_number))
				break;
			else
				System.out.println("직원번호는 두자리 이상이어야합니다.");
		}
	}
	
	private boolean isValidNumber(String number) {
		boolean ret = true;
		
		if(number.length() < 2)
			ret = false;
		
		return ret;
	}
	
	private void PrintDepartmentRequire() {
		System.out.print("Department : ");
	}
	private void GetDepartment() {
		_department = scanner.next();
	}
	
	@Override 
	protected void PrintSignUpDecisionRequire() {
		super.PrintSignUpDecisionRequire();
		
		System.out.println("Department : " + _department);
	}

	@Override
	protected String SignUpJsonInfo() {
		
		StaffInfo staff = new StaffInfo(_id,_pw,_number,_age,_department);
		JsonWrapper json = new JsonWrapper();
		
		return json.ToJson(SEND_TYPE.SIGNUPSTAFF, staff);
	}

	@Override
	protected void SignUpSucceed() {
		(new StaffMainMenuUi("Staff Main Menu")).UiStart();
	}

}
