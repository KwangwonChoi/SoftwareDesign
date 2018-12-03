package DataManage.JsonFormat;

import java.util.*;

public class StudentInfo extends MemberInfo{
	
	public int year;
	public String major;
	public float grade;
	
	public List<ApplicationInfo> aList;

	public StudentInfo(String id, String pw, String number, int age, int year, String major, float grade) {
		super(id,pw,number,age);
		
		this.year = year;
		this.major = major;
		this.grade = grade;
	}
}
