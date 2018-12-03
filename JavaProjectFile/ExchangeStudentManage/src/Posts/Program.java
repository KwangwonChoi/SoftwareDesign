<<<<<<< HEAD:JavaProjectFile/ExchangeStudentManage/src/Posts/Program.java
package Posts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DataManage.JsonFormat.ProgramInfo;
import Member.Staff;

public class Program {
	private Staff _staff;
	private String _name;
	private PROGRAMSTATE _state;
	private String _submitdue;
	private String _university;
	private String _country;
	private float _lowestGrade;
	private String _useLang;
	private String _datetime;
	private SimpleDateFormat _transFormat;
	private List<Application> _aList;
	
	public Program(String name, PROGRAMSTATE state, String submitdue, String university, String country, float lowestGrade, String useLang) {
		this._name = name;
		this._state = state;
		this._submitdue = submitdue;
		this._university = university;
		this._country = country;
		this._lowestGrade = lowestGrade;
		this._useLang = useLang;
		this._transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this._datetime = _transFormat.format(new Date());
		this._aList = new ArrayList<Application>(); 
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
		pInfo.staffId = _staff.GetId();
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
	
	public static Program GetProgramFromProgramInfo(ProgramInfo pInfo) {
		
		return new Program(pInfo.name, pInfo.state, pInfo.submitdue, pInfo.university, pInfo.country, pInfo.lowestGrade, pInfo.useLang); 
	}
	
	public static Program GetProgramFromProgramInfo(ProgramInfo pInfo, Staff staff) {
		
		Program p = new Program(pInfo.name, pInfo.state, pInfo.submitdue, pInfo.university, pInfo.country, pInfo.lowestGrade, pInfo.useLang);
		p.SetStaff(staff);
		return p;
	} 
	
	public boolean isValidGrade(double grade) {
		if(this._lowestGrade <= grade)
			return true;
		return false;
	}

	public void SetStaff(Staff staff) {
		// TODO Auto-generated method stub
		this._staff = staff;
	}
	
}
