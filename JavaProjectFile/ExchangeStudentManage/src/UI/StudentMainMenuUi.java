package UI;

import java.io.File;
import java.util.List;


public class StudentMainMenuUi extends MenuUiBase{

	public StudentMainMenuUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		_uiLists.add(new MakeApplicationUI("응시원서 접수"));
		_uiLists.add(new ShowApplicationListUi("응시원서 보여주기"));
	}

}
