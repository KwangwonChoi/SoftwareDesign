package UI;

import DataManage.UiManage.ObjectCarrier;
import Member.Staff;
import Posts.Program;

public class ShowRecruitmentListUi extends ListUiBase{

	public ShowRecruitmentListUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	private Staff staff;

	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		staff = (Staff) ObjectCarrier.GetData("Staff");
	}
	
	@Override
	protected void PrintMenus() {
		// TODO Auto-generated method stub
		
		for(int i = 0 ; i < staff.GetProgramList().size() ; i++) {
			System.out.println(String.valueOf(i+1) + ". " + staff.GetProgramList().get(i).GetName());
			Program p = staff.GetProgramList().get(i);
			
			_uiLists.add((new ShowRecruitmentUi(p.GetName())));
		}
	}
	
	protected void OnFinished() {
		ObjectCarrier.SaveData("Staff", staff);
	}
	
	@Override
	protected void GoToMenu(int menu) {
		
		if(menu != 0) {
			ObjectCarrier.SaveData("Staff", staff);
			ObjectCarrier.SaveData("ProgramIndex", menu-1);
		}
		
		super.GoToMenu(menu);
	}

}
