package UI;

import com.google.gson.Gson;
import MemberInfoManage.StaffInfo;
import OCSF.common.*;
import OCSF.common.JsonWrapper.SEND_TYPE;

public class SignUpStaffUi extends SignUpBaseUi{

	private String _department;

	public SignUpStaffUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void GetInfoProcess() {
		super.GetInfoProcess();

		PrintDepartmentRequire();
		GetDepartment();
		
		PrintSignUpDecisionRequire();
		GetSignUpDecision();
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
