package UI;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import Posts.*;


public abstract class MakeUiBase extends UiBase {
	
	protected Scanner _scanner;
	protected String _name;
	protected Contents _contents;
	protected List<File> _files;
	
	
	public MakeUiBase(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void OnStart() {
		_scanner = new Scanner(System.in);
		
		PrintSubjectRequire();
		while(PrintSubjectValiation(CheckValidSubjectName(_name = GetSubjectName())));
	
		PrintContentsRequire();
		while(PrintContentsValidation(CheckValidContents(_contents = GetContents())));
		
		PrintFileRequire();
		while(PrintFileValidation(CheckValidFiles(_files = GetFiles())));
		
	}
	
	protected void PrintSubjectRequire() {
		System.out.print("������ �Է��ϼ��� (8�� �̻�, 16�� �̸�) : ");
	}                 
	protected String GetSubjectName() {
		return _scanner.next();
	}
	//default : ������ 8�� �̻�, 32�� �̸�
	protected int CheckValidSubjectName(String str) {
		int ret = 0;
		
		if(str.length() > 8 && str.length() < 16)
			ret = 1;
		
		return ret;
	}
	protected boolean PrintSubjectValiation(int invalidType) {
		boolean ret = true;
		
		switch(invalidType) {
		case 1:
			System.out.println("������ 8�� �̻�, 16�� �̸��Դϴ�.");
			ret = false;
			break;
		}
		
		return ret;
	}
	
	
	protected abstract void PrintContentsRequire();
	protected abstract Contents GetContents();
	protected abstract int CheckValidContents(Contents c);
	protected abstract boolean PrintContentsValidation(int invalidType);

	protected abstract void PrintFileRequire();
	protected abstract List<File> GetFiles();
	protected abstract int CheckValidFiles(List<File> f);
	protected abstract boolean PrintFileValidation(int invalidType);
}