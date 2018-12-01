package bin;

public abstract class Member {
	
	public String id;
	public String pw;
	public String number;
	public int age;
	
	public Member(String id, String pw, String number, int age) {
		this.id = id;
		this.pw = pw;
		this.number = number;
		this.age = age;
	}
}
