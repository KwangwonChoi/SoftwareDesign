package DataManage.JsonFormat;

import java.util.*;

public class StudentInfo extends MemberInfo{
	
	public int year;
	public String major;
	public float grade;
	
	public List<ApplicationInfo> aList;

	public StudentInfo() {
		
	}
	
	public StudentInfo(String id, String pw, String name, String number, int age, int year, String major, float grade) {
		super(id,pw, name, number,age);
		
		this.year = year;
		this.major = major;
		this.grade = grade;
		
		aList = new ArrayList<ApplicationInfo>();
	}
	
	
}
