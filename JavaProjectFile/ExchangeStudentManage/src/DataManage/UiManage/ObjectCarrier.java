package DataManage.UiManage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectCarrier {

	private static Map<String, Object> _objlists = new HashMap<String, Object>();
	
	public static void SaveData(String key, Object obj) {
		_objlists.put(key, obj);
	}
	
	public static Object GetData(String key) {
		Object ret = _objlists.get(key);
		
		_objlists.remove(key);
		
		return ret;
	}
	
}
