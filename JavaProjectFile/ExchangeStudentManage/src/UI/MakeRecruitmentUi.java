package UI;

import java.io.File;
import java.io.IOException;
import java.util.List;

import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import DataManage.UiManage.ObjectCarrier;
import Member.Staff;
import OCSF.client.ChatClient;
import Posts.APPLICATIONSTATE;
import Posts.PROGRAMSTATE;


public class MakeRecruitmentUi extends MakeUiBase{
	
	private Staff staff;
	private String programName;
	private String submitdue;
	private String university;
	private String country;
	private float lowestGrade;
	private String useLang;

	public MakeRecruitmentUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void OnAwake() {
		super.OnAwake();
		staff = (Staff) ObjectCarrier.GetData("Staff");
	}
	
	@Override
	protected void OnStart() {
		
	}
	
	@Override
	protected void OnFinished() {
		super.OnFinished();
		ObjectCarrier.SaveData("Staff", staff);
	}

	@Override
	protected void SendPostsToServer() {
		// TODO Auto-generated method stub
		String json = JsonWrapper.ToJson(SEND_TYPE.MAKERECRUITMENT, staff.MakeProgram(programName, PROGRAMSTATE.RECRUIT, submitdue, university, country, lowestGrade, useLang));
		
		try {
		
			ChatClient.GetInstance().sendToServer(json);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
