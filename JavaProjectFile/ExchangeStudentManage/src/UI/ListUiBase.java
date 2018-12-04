package UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**/
public abstract class ListUiBase extends UiBase{
	
	private Scanner scanner = new Scanner(System.in);
	
	public ListUiBase(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	protected List<UiBase> _uiLists = new ArrayList<UiBase>();
	
	@Override
	protected void OnStart() {
		
		int menu;
		
		do {
		PrintMenus();
		
		menu = GetMenu();
		GoToMenu(menu);
		
		}while(menu != 0 /*menu's exit type is 0*/);
		
	}
	
	
	protected int GetMenu() {
		int ret = 0;
		
		do {
			
			ret = scanner.nextInt();
			
		}while(!IsValidMenu(ret));
		
		return ret;
	}//exit 포함.

	protected abstract void PrintMenus();
	
	protected boolean IsValidMenu(int menuNum) {
		boolean ret = menuNum >= 0 && menuNum <= _uiLists.size();
		
		if(!ret)
			System.out.println("0번부터" + String.valueOf(_uiLists.size()) +"번 까지 입력해 주세요");
		
		return ret;
	}
	
	protected void GoToMenu(int menu) {
		
		if(menu != 0) {
			_uiLists.get(menu - 1).UiStart();
		}
		
	}//0일때는 아무일도 하지 않는다.

}
