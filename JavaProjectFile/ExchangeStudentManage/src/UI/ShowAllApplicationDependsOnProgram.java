package UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import DataManage.JsonFormat.ApplicationInfo;
import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.ProgramInfo;
import DataManage.JsonFormat.ProgramListInfo;
import DataManage.UiManage.ObjectCarrier;
import Member.Staff;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import OCSF.client.ChatClient;
import Posts.APPLICATIONSTATE;
import Posts.Application;
import Posts.PROGRAMSTATE;
import Posts.Program;

public class ShowAllApplicationDependsOnProgram extends ListUiBase{

	public ShowAllApplicationDependsOnProgram(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}

	private String _serverText;
	private int timeCount;
	private Staff staff;
	private int programIndex;
	private Program pro;
	private List<Integer> indexMap = new ArrayList<Integer>();
	
	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		try {
			staff = (Staff) ObjectCarrier.GetData("Staff");
			programIndex = (int) ObjectCarrier.GetData("ProgramIndex");
			pro = staff.GetProgramList().get(programIndex);
			
			ChatClient.GetInstance().sendToServer(JsonWrapper.ToJson(SEND_TYPE.REQUESTAPPLICATION, pro.GetProgramInfo()));
			
			while((_serverText = ChatClient.GetInstance().GetstringFromServer()) == null) {
				timeCount++;
				
				Thread.sleep(1);
					
				if(timeCount > 100000)
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
	protected void OnFinished() {
		
		while((_serverText = ChatClient.GetInstance().GetstringFromServer()) == null) {
			timeCount++;
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			if(timeCount > 100000)
				try {
					throw new Exception();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		for(Program p : staff.GetProgramList()) {
			if(p.get_name().equals(pro.get_name()))
				p.SetState(pro.get_state());
		}
		ObjectCarrier.SaveData("Staff", staff);
		
	}
	
	@Override
	protected void PrintMenus() {
		// TODO Auto-generated method stub
		
		_uiLists.clear();
		indexMap.clear();
		List<Application> aList = pro.get_aList();
		int cnt = 1;
		
		if(pro.get_state() == PROGRAMSTATE.DOCUEMNTVIEW || pro.get_state() == PROGRAMSTATE.INTERVIEWREVIEW) {
			for(int i = 0 ; i < aList.size() ; i++) {
				Application a = aList.get(i);
				
				if(a.get_state() != APPLICATIONSTATE.UNPASS && a.get_state() != APPLICATIONSTATE.EVALUATED) {
					System.out.println(String.valueOf(cnt++) + ". " + a.get_student().GetId());
					indexMap.add(i);
					_uiLists.add((new ShowApplicationSetScoreUi("응시원서 보기")));
				}
			}
			
			if(HasAllApplicationChanged(cnt)) {
				System.out.println("이대로 저장하시겠습니까 ?(Y : 0, N : any)");
			}
		}
		else {
			System.out.println("점수입력이 종료된 프로그램 입니다.");
		}
		
		
		
	}
	private boolean HasAllApplicationChanged(int cnt) {
		return cnt == 1;
	}
	
	private void SendToServer() {
		
		try {
			pro.NextState();
			ChatClient.GetInstance().sendToServer(JsonWrapper.ToJson(SEND_TYPE.EDITAPPLICATIONSCORE, pro.GetProgramInfo()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected int GetMenu() {
		
		if(_uiLists.size() != 0)
			return super.GetMenu();
		else 
			return scanner.nextInt();
		
	}
	
	@Override
	protected void GoToMenu(int menu) {
		
		if(_uiLists.size() > 0) {
			if(menu != 0) {
				ObjectCarrier.SaveData("Program", pro);
				ObjectCarrier.SaveData("index", indexMap.get(menu-1));
			}
			
			super.GoToMenu(menu);
			
			if(menu != 0)
				pro = (Program)ObjectCarrier.GetData("Program");
		}
		
		else if(menu == 0)
			SendToServer();
	}
}