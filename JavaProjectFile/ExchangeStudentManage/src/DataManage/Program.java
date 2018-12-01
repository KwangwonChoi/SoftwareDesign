package DataManage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
	private String _name;
	private String _state;
	private String _submitdue;
	private String _university;
	private String _country;
	private double _lowestGrade;
	private String _useLang;
	private String _datetime;
	private Recruitment _recru;
	private SimpleDateFormat _transFormat;
	
	public Program(String name, String state, String submitdue, String university, String country, double lowestGrade, String useLang) {
		this._name = name;
		this._state = state;
		this._submitdue = submitdue;
		this._university = university;
		this._country = country;
		this._lowestGrade = lowestGrade;
		this._useLang = useLang;
		this._transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this._datetime = _transFormat.format(new Date());
	}
	
	public Program(ProgramInfo pInfo) {
		this._name = pInfo.name;
		this._state = pInfo.state;
		this._submitdue = pInfo.submitdue;
		this._university = pInfo.university;
		this._country = pInfo.country;
		this._lowestGrade = pInfo.lowestGrade;
		this._useLang = pInfo.useLang;
		this._datetime = pInfo.datetime;
	}
	
	public ProgramInfo GetProgramInfo() {
		ProgramInfo pInfo = new ProgramInfo();
		pInfo.name = this._name;
		pInfo.country = this._country;
		pInfo.datetime = this._datetime;
		pInfo.lowestGrade = this._lowestGrade;
		pInfo.state = this._state;
		pInfo.university = this._university;
		pInfo.submitdue = this._submitdue;
		pInfo.useLang = this._useLang;
		return pInfo;
	}
	
	public boolean isValid(double grade) {
		if(this._lowestGrade <= grade)
			return true;
		return false;
	}

	public void setRecru(Recruitment recru) {
		// TODO Auto-generated method stub
		this._recru = recru; 
	}
	
	
}
