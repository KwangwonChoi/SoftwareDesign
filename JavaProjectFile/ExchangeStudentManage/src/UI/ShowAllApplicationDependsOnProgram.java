package UI;

import java.io.IOException;

import com.google.gson.Gson;

import DataManage.JsonFormat.ApplicationInfo;
import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.ProgramInfo;
import DataManage.JsonFormat.ProgramListInfo;
import DataManage.UiManage.ObjectCarrier;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import OCSF.client.ChatClient;
import Posts.Application;
import Posts.Program;

public class ShowAllApplicationDependsOnProgram extends ListUiBase{

	public ShowAllApplicationDependsOnProgram(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}

	private String _serverText;
	private int timeCount;
	private Program pro;
	
	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		try {
			pro = (Program) ObjectCarrier.GetData("Program");
			
			ChatClient.GetInstance().sendToServer(JsonWrapper.ToJson(SEND_TYPE.REQUESTAPPLICATION, pro.GetProgramInfo()));
			
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
			return;
		}
		
		Gson gson = new Gson();
		
		pro = Program.GetProgramFromProgramInfo(gson.fromJson(JsonWrapper.FromJson(_serverText).json, ProgramInfo.class));
	}
	
	@Override
	protected void PrintMenus() {
		// TODO Auto-generated method stub
		
		for(int i = 0 ; i < pro.get_aList().size() ; i++) {
			Application a = pro.get_aList().get(i);
			System.out.println(String.valueOf(i+1) + ". " + a.get_student().GetId());
			
			_uiLists.add((new ShowApplicationSetScoreUi("응시원서 보기")));
		}
	}
	
	private void SendToServer() {
		
		try {
			
			ChatClient.GetInstance().sendToServer(JsonWrapper.ToJson(SEND_TYPE.EDITAPPLICATIONSCORE, pro.GetProgramInfo()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void GoToMenu(int menu) {
		
		if(menu != 0) {
			ObjectCarrier.SaveData("Program", pro);
			ObjectCarrier.SaveData("index", menu-1);
		}
		
		super.GoToMenu(menu);
		
		pro = (Program)ObjectCarrier.GetData("Program");
	}
}