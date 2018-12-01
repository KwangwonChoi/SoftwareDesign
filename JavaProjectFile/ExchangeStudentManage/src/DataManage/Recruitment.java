package DataManage;

import java.io.File;
import java.util.List;

import Posts.Contents;
import Posts.PostsBase;

public class Recruitment extends PostsBase{
	Program pro;
	public Recruitment(String str, Contents c, List<File> f, Program pro) {
		super(str, c, f);
		// TODO Auto-generated constructor stub
		this.pro = pro;
	}
}
