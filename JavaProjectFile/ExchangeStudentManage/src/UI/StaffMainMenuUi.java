package UI;

public class StaffMainMenuUi extends MenuUiBase{

	public StaffMainMenuUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		_uiLists.add(new MakeRecruitmentUi("���α׷� �����"));
	}

}
