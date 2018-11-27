package UI;

import java.util.*;
import java.util.Scanner;

public abstract class MenuUiBase extends UiBase {

	private Scanner scanner = new Scanner(System.in);
	
	public MenuUiBase(String uiName) {
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
	
	protected void PrintMenus() {
		
		if(_uiLists.size() != 0) {
			for(int i = 0; i < _uiLists.size(); i++) {
				System.out.println(String.valueOf(i+1) + ". " + _uiLists.get(i)._uiName);
			}
			
			System.out.println("Enter your menu. if you want to exit, enter Num 0.");
		}
	}
	
	protected int GetMenu() {
		int ret = 0;
		
		do {
			
			ret = scanner.nextInt();
			
		}while(!isValidMenu(ret));
		
		return ret;
	}//exit ����.
	
	private boolean isValidMenu(int menuNum) {
		boolean ret = menuNum >= 0 && menuNum <= _uiLists.size();
		
		if(!ret)
			System.out.println("�޴��� 0������" + String.valueOf(_uiLists.size()) +"�� ���� �Է��� �ּ���");
		
		return ret;
	}
	
	protected void GoToMenu(int menu) {
		
		if(menu != 0) {
			_uiLists.get(menu - 1).UiStart();
		}
		
	}//0�϶��� �ƹ��ϵ� ���� �ʴ´�.
	
}
