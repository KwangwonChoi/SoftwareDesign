package DataManage;

import java.util.ArrayList;
import java.util.List;

import MemberInfoManage.*;

public class MemberList {
	public List<StaffInfo> staffs;
	public List<StudentInfo> students;
	
	public MemberList() {
		staffs = new ArrayList<StaffInfo>();
		students = new ArrayList<StudentInfo>();
	}
}
