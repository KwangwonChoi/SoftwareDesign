package UI;

import java.util.*;

public class SignMenuUi extends MenuUiBase{

	public SignMenuUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnAwake() {
		//initilize ui
		
		_uiLists.add(new SignInUi("SignIn"));
		_uiLists.add(new SignUpMenuUi("SignUp"));
	}
}
