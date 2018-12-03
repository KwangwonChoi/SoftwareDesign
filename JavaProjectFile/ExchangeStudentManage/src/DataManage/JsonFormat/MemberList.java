package DataManage.JsonFormat;

import java.util.ArrayList;
import Member.*;
import java.util.List;

public class MemberList {
	public List<StaffInfo> staffs;
	public List<StudentInfo> students;
	
	public MemberList() {
		staffs = new ArrayList<StaffInfo>();
		students = new ArrayList<StudentInfo>();
	}
}
