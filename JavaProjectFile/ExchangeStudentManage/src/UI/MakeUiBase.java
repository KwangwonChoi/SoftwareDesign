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
		System.out.print("제목을 입력하세요 (8자 이상, 16자 미만) : ");
	}                 
	protected String GetSubjectName() {
		return _scanner.next();
	}
	//default : 제목은 8자 이상, 32자 미만
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
			System.out.println("제목은 8자 이상, 16자 미만입니다.");
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