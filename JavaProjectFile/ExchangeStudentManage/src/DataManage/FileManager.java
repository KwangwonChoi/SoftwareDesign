package DataManage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import MemberInfoManage.*;

public class FileManager {

	enum FILE_PATH{
		MEMBER_LIST("../Data/Member.json"),
		APPLICATION_LIST("../Data/Applications.json"),
		RECRUITMENT_LIST("../Data/Recruitment.json");
		
		private final String path;
		
		FILE_PATH(String path){
			this.path = path;
		}
		
		public String getPath() {
			return path;
		}
	}
	
	public MemberList GetMemberListFromFile() {
		File file = new File(FILE_PATH.MEMBER_LIST.path);
		
		Gson gson = new Gson();
		
		String str = "";
		
		try {
			
			str = GetDataFromFile(file);
			
		} catch (FileNotFoundException e) {
		
			return new MemberList();
		}
		
		return gson.fromJson(str, MemberList.class);
	}
	
	private String GetDataFromFile(File file) throws FileNotFoundException {
		String str = "";
		
		BufferedReader br = new BufferedReader(new FileReader(file));

		//파일을 한 문장씩 읽어온다.
		try {
			String tmp;

			do {
			
				tmp = br.readLine();
				
				if(tmp != null)
					str += tmp;
			
			}while(tmp != null);
			
			br.close();
							
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		return str;
	}
	
	public void SaveMemberListToFile(MemberList list) {
		File file = new File(FILE_PATH.MEMBER_LIST.path);
		Gson gson = new Gson();

		String json = gson.toJson(list);
		
		SaveDataToFile(file,json);
	}
	
	private void SaveDataToFile(File file, String str) {
		
		try {
			PrintWriter pwriter = new PrintWriter(new FileWriter(file));
			
			pwriter.write(str);
			
			pwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
