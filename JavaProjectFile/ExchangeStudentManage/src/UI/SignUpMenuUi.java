package UI;

public class SignUpMenuUi extends MenuUiBase{

	public SignUpMenuUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		_uiLists.add(new SignUpStudentUi("Student SignUp"));
		_uiLists.add(new SignUpStaffUi("Staff SignUp"));
	}

}
