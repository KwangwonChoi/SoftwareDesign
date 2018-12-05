package Posts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DataManage.JsonFormat.ApplicationInfo;
import DataManage.JsonFormat.ProgramInfo;
import Member.Staff;

public class Program {
	private Staff _staff;
	private String _staffId;
	private String _name;
	private PROGRAMSTATE _state;
	private String _submitdue;
	private String _university;
	private String _country;
	private float _lowestGrade;
	private String _useLang;
	private String _datetime;
	private SimpleDateFormat _transFormat;
	private int _finalPassNum; 
	private List<Application> _aList;
	
	public Program(String name, PROGRAMSTATE state, String submitdue, String university, String country, float lowestGrade, String useLang, int finalPassNum) {
		this._name = name;
		this._state = state;
		this._submitdue = submitdue;
		this._university = university;
		this._country = country;
		this._lowestGrade = lowestGrade;
		this._useLang = useLang;
		this._transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this._datetime = _transFormat.format(new Date());
		this._finalPassNum = finalPassNum;
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
		this._finalPassNum = pInfo.finalPassNum;
		
		_aList = new ArrayList<Application>();
		for(ApplicationInfo a : pInfo.aList) {
			_aList.add(new Application(a));
		}
	}
	
	public ProgramInfo GetProgramInfo() {
		ProgramInfo pInfo = new ProgramInfo();
		pInfo.staffId = _staffId;
		pInfo.name = this._name;
		pInfo.country = this._country;
		pInfo.datetime = this._datetime;
		pInfo.lowestGrade = this._lowestGrade;
		pInfo.state = this._state;
		pInfo.university = this._university;
		pInfo.submitdue = this._submitdue;
		pInfo.useLang = this._useLang;
		pInfo.finalPassNum = this._finalPassNum;
		return pInfo;
	}
	
	public static Program GetProgramFromProgramInfo(ProgramInfo pInfo) {
		
		return new Program(pInfo); 
		
	}
	
	public static Program GetProgramFromProgramInfo(ProgramInfo pInfo, Staff staff) {
		
		Program p = new Program(pInfo.name, pInfo.state, pInfo.submitdue, pInfo.university, pInfo.country, pInfo.lowestGrade, pInfo.useLang, pInfo.finalPassNum);
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
		this._staffId = staff.GetStaffInfo().id;
	}
	
	public String GetName() {
		return _name;
	}

	public final Staff get_staff() {
		return _staff;
	}

	public final String get_name() {
		return _name;
	}

	public final PROGRAMSTATE get_state() {
		return _state;
	}

	public final String get_submitdue() {
		return _submitdue;
	}

	public final String get_university() {
		return _university;
	}

	public final String get_country() {
		return _country;
	}

	public final float get_lowestGrade() {
		return _lowestGrade;
	}

	public final String get_useLang() {
		return _useLang;
	}

	public final String get_datetime() {
		return _datetime;
	}

	public final List<Application> get_aList() {
		return _aList;
	}
	
	
	
}
