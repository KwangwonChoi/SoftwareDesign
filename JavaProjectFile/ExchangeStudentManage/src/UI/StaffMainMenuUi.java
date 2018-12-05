package UI;

public class StaffMainMenuUi extends MenuUiBase{

	public StaffMainMenuUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		_uiLists.add(new MakeRecruitmentUi("프로그램 만들기"));
		_uiLists.add(new ShowRecruitmentListUi("프로그램 리스트"));
	}

}
