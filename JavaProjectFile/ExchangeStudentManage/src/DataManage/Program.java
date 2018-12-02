package DataManage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
	private String name;
	private String state;
	private String submitdue;
	private String university;
	private String country;
	private double lowestGrade;
	private String useLang;
	private String datetime;
	private Recruitment recru;
	SimpleDateFormat transFormat;
	
	public Program(String name, String state, String submitdue, String university, String country, double lowestGrade, String useLang) {
		this.name = name;
		this.state = state;
		this.submitdue = submitdue;
		this.university = university;
		this.country = country;
		this.lowestGrade = lowestGrade;
		this.useLang = useLang;
		this.transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.datetime = transFormat.format(new Date());
	}
	
	public Program(ProgramInfo pInfo) {
		this.name = pInfo.name;
		this.state = pInfo.state;
		this.submitdue = pInfo.submitdue;
		this.university = pInfo.university;
		this.country = pInfo.country;
		this.lowestGrade = pInfo.lowestGrade;
		this.useLang = pInfo.useLang;
		this.datetime = pInfo.datetime;
		this.recru = pInfo.recru;
	}
	
	public ProgramInfo GetProgramInfo() {
		ProgramInfo pInfo = new ProgramInfo();
		pInfo.name = this.name;
		pInfo.country = this.country;
		pInfo.datetime = this.datetime;
		pInfo.lowestGrade = this.lowestGrade;
		pInfo.state = this.state;
		pInfo.university = this.university;
		pInfo.submitdue = this.submitdue;
		pInfo.useLang = this.useLang;
		pInfo.recru = this.recru;
		return pInfo;
	}
	
	public boolean isValid(double grade) {
		if(this.lowestGrade <= grade)
			return true;
		return false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSubmitdue() {
		return submitdue;
	}

	public void setSubmitdue(String submitdue) {
		this.submitdue = submitdue;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getLowestGrade() {
		return lowestGrade;
	}

	public void setLowestGrade(double lowestGrade) {
		this.lowestGrade = lowestGrade;
	}

	public String getUseLang() {
		return useLang;
	}

	public void setUseLang(String useLang) {
		this.useLang = useLang;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public Recruitment getRecru() {
		return recru;
	}

	public void setRecru(Recruitment recru) {
		this.recru = recru;
	}
}
