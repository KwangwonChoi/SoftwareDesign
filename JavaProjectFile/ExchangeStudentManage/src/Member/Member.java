package Member;

import DataManage.JsonFormat.MemberInfo;

public abstract class Member {
	
	//singletone Constructor
	protected Member() {}
	
	protected Member(String id, String pw, String name, String number, int age) {
		this._id = id;
		this._pw = pw;
		this._number = number;
		this._age = age;
	}
	
	protected Member(MemberInfo m) {
		this._id = m.id;
		this._pw = m.pw;
		this._number = m.number;
		this._age = m.age;
	}
	
	protected String _id;
	protected String _pw;
	protected String _name;
	protected String _number;
	protected int _age;
	
	
}
