package DataManage.JsonFormat;

import com.google.gson.*;
public class JsonWrapper {
	public enum SEND_TYPE{
		SIGNIN,
		SIGNINSTUDENT,
		SIGNINSTAFF,
		SIGNUPSTUDENT,
		SIGNUPSTAFF,
		MAKERECRUITMENT,
		MAKEAPPLICATION,
		REQUESTRECRUITMENT,
		REQUESTAPPLICATION
	}
	
	public SEND_TYPE type;
	public String json;
	
	public static String ToJson(SEND_TYPE type, Object obj) {
		JsonWrapper json = new JsonWrapper();
		Gson gson = new Gson();
		
		json.type = type;
		json.json = gson.toJson(obj);
		
		return gson.toJson(json);
	}
	
	public static JsonWrapper FromJson(String json) {
		Gson gson = new Gson();
		
		return gson.fromJson(json, JsonWrapper.class);
	}

}
