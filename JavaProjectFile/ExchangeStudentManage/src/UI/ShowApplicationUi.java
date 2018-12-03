package UI;

import DataManage.UiManage.ObjectCarrier;
import Posts.Application;
import Posts.Program;

public class ShowApplicationUi extends UiBase{

	public ShowApplicationUi(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	private Application application;
	
	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		application = (Application) ObjectCarrier.GetData("Application");
	}

	@Override
	protected void OnStart() {
		// TODO Auto-generated method stub
		
	}

}
