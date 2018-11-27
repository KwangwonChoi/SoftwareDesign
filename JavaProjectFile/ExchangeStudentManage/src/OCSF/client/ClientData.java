package OCSF.client;

import bin.*;

public class ClientData {

	//enumeration
	public enum MEMBER_TYPE{
		STUDENT,
		STAFF
	}
	
	//Singleton
	private ClientData() {}
	
	private static ClientData _instance;
	
	public static ClientData GetIntance() {
		
		if(_instance != null)
			return _instance;
		
		return _instance = new ClientData();
	}
	
	//Attributes
	private ChatClient client;
	private Student student;
	private Staff staff;
	private MEMBER_TYPE memberType;
	
	public ChatClient GetClient() {
		return client;
	}
	
	public void SetClient(ChatClient c) {
		client = c;
	}
		
	public Student GetStudent() {
		return student;
	}
	
	public Staff GetStaff() {
		return staff;
	}
}
