# 1. Naming
## 1.1 Code 상에서의 Naming 규칙
#### - Class 및 Method 는 Pascal-Case를 사용한다. (ex. PascalCase)
#### - attribute는 Camel-Case를 사용한다. (ex.camelCase)
#### - 특별한 경우를 제외하곤 bool을 형의 Method 및 attribute의 어두를 is 나 has로 사용한다.
#### - private 및 protected 의 어두에는 _(underbar)가 들어가야 한다.
#### - import는 Dictionary 순으로 정렬한다.
#
# 2. UI
## 2.1. UiBase
### 2.1.1. UiBase는 모든 Ui가 상속 받아야하는 Class이다.
#### UiBase를 보면 모든 Ui의 LifeCycle이 OnAwake() -> OnStart() -> OnFinished() 임을 알 수 있다.
#### OnAwake에서는 데이터를 초기화 하고, OnStart에서는 작업을 수행하며, OnFinished에서는 데이터를 정리한다.
##
## 2.2. ListBaseUi
### 2.2.1 List를 가지는 Ui의 base가 되는 Ui이다.
#### PrintMenus를 Override하여 Customizing 할 수 있다.
##### MenuUiBase 참고.
```java

public abstract class MenuUiBase extends ListUiBase {

	public MenuUiBase(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void PrintMenus() {
		
		if(_uiLists.size() != 0) {
			for(int i = 0; i < _uiLists.size(); i++) {
				System.out.println(String.valueOf(i+1) + ". " + _uiLists.get(i)._uiName);
			}
			
			System.out.println("Enter your menu. if you want to exit, enter Num 0.");
		}
	}
	
}
```
##
## 2.3. MakeUiBase
### 2.3.1 User로 부터 내용을 입력 받아서 서버에 Posts를 송신하는 Ui의 Base가 된다.
#### MakeApplicationUi, MakeRecruitmentUi의 Base.

#
# 3. Communiation
## 3.1 Server-Client
### 3.1.1 Server-Client의 Communiation양식은 기본적으로 Json을 사용한다.
### 3.1.2 Client에서 받은 정보를 Server에서 재가공 하기 위해 JsonWrapper class의 type을 이용하여 Client object의 정보를 읽어낸다.
#### EchoServer의 handleMessageFromClient에서 확인할 수 있다.
```java
public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
    System.out.println("Message received: " + msg + " from " + client);
    
    Gson gson = new Gson();
    
    JsonWrapper receivedData = JsonWrapper.FromJson(msg.toString());
    
    String toClientString = null;
    
    switch(receivedData.type) {
    case SIGNIN:
    	toClientString = SignIn(gson.fromJson(receivedData.json, LoginInfo.class));
    	break;
    case SIGNUPSTAFF:
    	toClientString = SignUp(gson.fromJson(receivedData.json, StaffInfo.class));
    	break;
    case SIGNUPSTUDENT:
    	toClientString = SignUp(gson.fromJson(receivedData.json, StudentInfo.class));
    	break;
    case MAKERECRUITMENT:
    	toClientString = MakeRecruitment(gson.fromJson(receivedData.json, StaffInfo.class));
    	break;
    case MAKEAPPLICATION:
    	toClientString = MakeApplication(gson.fromJson(receivedData.json, StudentInfo.class));
    	break;
    case REQUESTRECRUITMENT:
    	toClientString = RequestRecruitment();
    	break;
    case REQUESTAPPLICATION:
    	//해당 프로그램에 연결 된 모든 applicationList를 찾아준다.
    	toClientString = RequestApplication(gson.fromJson(receivedData.json, ProgramInfo.class));
    	break;
    case EDITAPPLICATIONSCORE:
    	toClientString = EditApplicationScore(gson.fromJson(receivedData.json, ProgramInfo.class));
    }
    
    try {
		client.sendToClient(toClientString);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
```
##
## 3.2 Ui-Server
### 3.2.1 Ui에서 Server에 정보를 보내기위해 OCSF의 ChatClient를 이용해야한다. 이 ChatClient는 OCSF.client의 ClientData 안에 구현되어있다.
#### client의 instantiate는 ClientConsole에서 이루어진다.
#### 예제는 SignUpBaseUi의 SignUpToServer 참고.
```java

protected boolean SignUpToServer() {
		boolean isSucceed = false;
		
		try {
			
			ClientData.GetIntance().GetClient().sendToServer(SignUpJsonInfo());
			
			if(ClientData.GetIntance().GetClient().GetstringFromServer() == "true")
				isSucceed = true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSucceed;
	}
```
##
## 3.3 Server-File
### 3.3.1 Server에서 File에 저장하는 모든 내용은 Json으로 저장된다.
### 3.3.2 File에 접근하기위해서는 FileManager만 이용해야 하며, 모든 파일의 경로는 ../Data/* 이어야 한다.
```java

enum FILE_PATH{
		MEMBER_LIST("../Data/Member.json"),
		APPLICATION_LIST("../Data/Applications.json"),
		RECRUITMENT_LIST("../Data/Recruitment.json");
		
		private final String path;
		
		FILE_PATH(String path){
			this.path = path;
		}
		
		public String getPath() {
			return path;
		}
	}
```

##
## 3.4 UI-UI
### 3.4.1 Ui에 필요한 정보는 ObjectCarrier를 이용하여 이동한다.
```java
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
```

#
# 4. Framework
## 4.1 OCSF
### 4.1.1 본 프로그램은 OCSF를 이용하여 구현되었다.

#
# 5. External Libraries
## 5.1 Gson
### 5.1.1 본 프로그램의 Json형식은 Gson을 이용하여 구현하였다.
## 5.2 Log4j
