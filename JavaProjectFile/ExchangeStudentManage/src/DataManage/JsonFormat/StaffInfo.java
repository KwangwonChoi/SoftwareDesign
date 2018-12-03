package DataManage.JsonFormat;

import java.util.*;

public class StaffInfo extends MemberInfo{
	public String department;
	public List<ProgramInfo> pList;
	
	public StaffInfo(String id, String pw, String number, int age, String department) {
		super(id, pw, number, age);

		this.department = department;
		pList = new ArrayList<ProgramInfo>();
	}
}
