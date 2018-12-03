package UI;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import Posts.*;


public abstract class MakeUiBase extends UiBase {
	
	protected Scanner _scanner;
		
	public MakeUiBase(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void OnAwake() {
		_scanner = new Scanner(System.in);
	}
		
	@Override
	protected void OnFinished() {
		SendPostsToServer();
	}
	
	protected abstract void SendPostsToServer();
}