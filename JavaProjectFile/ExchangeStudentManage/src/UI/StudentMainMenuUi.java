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
		_uiLists.add(new MakeApplicationUI("���ÿ��� ����"));
		_uiLists.add(new ShowApplicationListUi("���ÿ��� �����ֱ�"));
	}

}
