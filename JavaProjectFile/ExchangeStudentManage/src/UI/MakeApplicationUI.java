package UI;

import java.io.File;
import java.util.List;

import Posts.Contents;

public class MakeApplicationUI extends MakeUiBase {

	public MakeApplicationUI(String uiName) {
		super(uiName);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void PrintContentsRequire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Contents GetContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int CheckValidContents(Contents c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean PrintContentsValidation(int invalidType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void PrintFileRequire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<File> GetFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int CheckValidFiles(List<File> f) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean PrintFileValidation(int invalidType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void SendPostsToServer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void OnAwake() {
		// TODO Auto-generated method stub
		
	}

}
