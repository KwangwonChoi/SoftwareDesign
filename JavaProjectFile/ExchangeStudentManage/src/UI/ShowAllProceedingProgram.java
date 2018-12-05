package UI;

import java.io.IOException;

import com.google.gson.Gson;

import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import DataManage.UiManage.ObjectCarrier;
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
		
		pListInfo = gson.fromJson(JsonWrapper.FromJson(_serverText).json, ProgramListInfo.class);
	}
	
	@Override
	protected void PrintMenus() {
		// TODO Auto-generated method stub

		for(int i = 0 ; i < pListInfo.p.size() ; i++) {
			ProgramInfo pInfo = pListInfo.p.get(i);
			System.out.println(String.valueOf(i+1) + ". " + pInfo.name);
		
			_uiLists.add((new MakeApplicationUI("응시원서 접수")));
		}
		System.out.println("Enter your menu. if you want to exit, enter Num 0.");
	}
	
	@Override
	protected void GoToMenu(int menu) {
		if(menu != 0)
			ObjectCarrier.SaveData("Program", Program.GetProgramFromProgramInfo(pListInfo.p.get(menu-1)));
		
		super.GoToMenu(menu);
	}

	

}
