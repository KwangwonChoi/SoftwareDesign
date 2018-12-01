package DataManage;

import MemberInfoManage.*;

public class StaffInfo extends MemberInfo{
	public String department;
	
	public StaffInfo(String id, String pw, String number, int age, String department) {
		super(id, pw, number, age);

		this.department = department;
	}
}
