package UI;

import java.util.*;
import java.util.Scanner;

public abstract class MenuUiBase extends ListUiBase {

	public MenuUiBase(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void PrintMenus() {
		
		if(_uiLists.size() != 0) {
			for(int i = 0; i < _uiLists.size(); i++) {
				System.out.println(String.valueOf(i+1) + ". " + _uiLists.get(i)._uiName);
			}
			
			System.out.println("Enter your menu. if you want to exit, enter Num 0.");
		}
	}
	
}
