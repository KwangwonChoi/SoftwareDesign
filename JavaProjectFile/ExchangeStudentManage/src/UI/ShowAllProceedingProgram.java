package UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import DataManage.UiManage.ObjectCarrier;
import Member.Student;
import DataManage.JsonFormat.ProgramInfo;
import DataManage.JsonFormat.ProgramListInfo;
import OCSF.client.ChatClient;
import Posts.Application;
import Posts.Program;

public class ShowAllProceedingProgram extends ListUiBase{

	public ShowAllProceedingProgram(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}

	private String _serverText = null;
	private long timeCount = 0;
	private ProgramListInfo pListInfo;
	private Student std;
	private List<Integer> indexMap = new ArrayList<Integer>();
	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		try {
			
			ChatClient.GetInstance().sendToServer(JsonWrapper.ToJson(SEND_TYPE.REQUESTRECRUITMENT, null));
			
			while((_serverText = ChatClient.GetInstance().GetstringFromServer()) == null) {
				timeCount++;
				
				Thread.sleep(1);
					
				if(timeCount > 1000)
					throw new Exception();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("통신시간 만료");
		}
		
		Gson gson = new Gson();
		std = (Student) ObjectCarrier.GetData("Student");
		pListInfo = gson.fromJson(JsonWrapper.FromJson(_serverText).json, ProgramListInfo.class);
	}
	
	@Override
	protected void PrintMenus() {
		// TODO Auto-generated method stub
		int cnt = 1;
		_uiLists.clear();
		indexMap.clear();
		for(int i = 0 ; i < pListInfo.p.size() ; i++) {
			boolean hasSubmit = false;
			ProgramInfo pInfo = pListInfo.p.get(i);
			
			for(Application a : std.GetaList()) {
				if(a.get_programName().equals(pInfo.name))
					hasSubmit = true;
			}
			
			if(!hasSubmit) {
				System.out.println(String.valueOf(cnt++) + ". " + pInfo.name);
				indexMap.add(i);
				_uiLists.add((new MakeApplicationUI("응시원서 접수")));
			}
		}
		System.out.println("Enter your menu. if you want to exit, enter Num 0.");
	}
	
	@Override
	protected void GoToMenu(int menu) {
		if(menu != 0) {
			ObjectCarrier.SaveData("Program", Program.GetProgramFromProgramInfo(pListInfo.p.get(indexMap.get(menu-1))));
			ObjectCarrier.SaveData("Student", std);
		}
		super.GoToMenu(menu);
		
		std = (Student)ObjectCarrier.GetData("Student");
	}
	
	@Override
	protected void OnFinished() {
		ObjectCarrier.SaveData("Student",std);
	}

	

}
