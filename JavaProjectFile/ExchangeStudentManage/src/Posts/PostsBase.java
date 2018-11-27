package Posts;

import java.io.*;
import java.util.List;

public abstract class PostsBase {

	public String _subjectName;
	public Contents _contents;
	public List<File> _files;
	
	public PostsBase(String str, Contents c, List<File> f) {
		_subjectName = str;
		_contents = c;
		_files = f;
	}
}
