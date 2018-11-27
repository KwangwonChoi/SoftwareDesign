package MemberInfoManage;

public class StudentInfo extends MemberInfo{
	
	public String major;
	public float grade;

	public StudentInfo(String id, String pw, String number, int age, String major, float grade) {
		super(id,pw,number,age);
		
		this.major = major;
		this.grade = grade;
	}
}
