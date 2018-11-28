package OCSF.common;

import com.google.gson.*;
public class JsonWrapper {
	public enum SEND_TYPE{
		SIGNIN,
		SIGNUPSTUDENT,
		SIGNUPSTAFF,
		MAKERECRUITMENT,
		MAKEAPPLICATION,
	}
	
	public SEND_TYPE type;
	public String json;
	
	public String ToJson(SEND_TYPE type, Object obj) {
		Gson gson = new Gson();
		
		this.type = type;
		this.json = gson.toJson(obj);
		
		return gson.toJson(this);
	}
}
