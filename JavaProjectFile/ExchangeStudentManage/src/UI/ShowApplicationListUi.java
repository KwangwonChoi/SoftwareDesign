package UI;

import DataManage.UiManage.ObjectCarrier;
import Member.Student;
import Posts.Application;
import Posts.Program;

public class ShowApplicationListUi extends ListUiBase{

	public ShowApplicationListUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	private Student stdnt;

	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		stdnt = (Student) ObjectCarrier.GetData("Student");
	}
	
	@Override
	protected void PrintMenus() {
		// TODO Auto-generated method stub

		for(int i = 0 ; i < stdnt.GetaList().size() ; i++) {
			Application a = stdnt.GetaList().get(i);
			System.out.println(String.valueOf(i+1) + ". " + a);
			
			ObjectCarrier.SaveData("Application", a);
			_uiLists.add((new ShowApplicationUi(a.get_programName())));
		}
	}



}
