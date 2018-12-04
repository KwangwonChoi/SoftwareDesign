package OCSF;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;

import DataManage.*;
import DataManage.FileManage.FileManager;
import DataManage.JsonFormat.ApplicationInfo;
import DataManage.JsonFormat.ApplicationListInfo;
import DataManage.JsonFormat.JsonWrapper;
import DataManage.JsonFormat.JsonWrapper.SEND_TYPE;
import DataManage.JsonFormat.LoginInfo;
import DataManage.JsonFormat.MemberList;
import DataManage.JsonFormat.ProgramInfo;
import DataManage.JsonFormat.ProgramListInfo;
import DataManage.JsonFormat.StaffInfo;
import DataManage.JsonFormat.StudentInfo;
import OCSF.server.*;
import OCSF.server.AbstractServer;
import OCSF.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
    System.out.println("Message received: " + msg + " from " + client);
    
    Gson gson = new Gson();
    
    JsonWrapper receivedData = JsonWrapper.FromJson(msg.toString());
    
    String toClientString = null;
    
    switch(receivedData.type) {
    case SIGNIN:
    	toClientString = SignIn(gson.fromJson(receivedData.json, LoginInfo.class));
    	break;
    case SIGNUPSTAFF:
    	toClientString = SignUp(gson.fromJson(receivedData.json, StaffInfo.class));
    	break;
    case SIGNUPSTUDENT:
    	toClientString = SignUp(gson.fromJson(receivedData.json, StudentInfo.class));
    	break;
    case MAKERECRUITMENT:
    	toClientString = MakeRecruitment(gson.fromJson(receivedData.json, StaffInfo.class));
    	break;
    case MAKEAPPLICATION:
    	toClientString = MakeApplication(gson.fromJson(receivedData.json, StudentInfo.class));
    	break;
    case REQUESTRECRUITMENT:
    	toClientString = RequestRecruitment();
    	break;
    case REQUESTAPPLICATION:
    	//해당 프로그램에 연결 된 모든 applicationList를 찾아준다.
    	toClientString = RequestApplication(gson.fromJson(receivedData.json, ProgramInfo.class));
    	break;
    }
    
    try {
		client.sendToClient(toClientString);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  private String RequestRecruitment() {
	  FileManager fmgr = new FileManager();
	  MemberList member = fmgr.GetMemberListFromFile();
	  ProgramListInfo pListInfo = new ProgramListInfo();
	  
	  for(StaffInfo s : member.staffs) {
		  for(ProgramInfo p : s.pList) {
			  pListInfo.p.add(p);
		  }
	  }
	  
	  return JsonWrapper.ToJson(SEND_TYPE.REQUESTRECRUITMENT, pListInfo);
  }
  
  private String RequestApplication(ProgramInfo pro) {
	  FileManager fmgr = new FileManager();
	  MemberList member = fmgr.GetMemberListFromFile();
	  
	  ApplicationListInfo aListInfo = new ApplicationListInfo();
	  
	  for(StudentInfo s: member.students) {
		  for(ApplicationInfo a : s.aList) {
			  if(a.ProgramName.equals(pro.name)) {
				  aListInfo.a.add(a);
			  }
			  
			  break;
		  }
	  }
	  
	  return JsonWrapper.ToJson(SEND_TYPE.REQUESTAPPLICATION, aListInfo);
  }
  
  private String SignIn(LoginInfo login){
	  FileManager fmgr = new FileManager();
	  MemberList member = fmgr.GetMemberListFromFile();
	  for(StaffInfo s : member.staffs) {
		  if(s.id.equals(login.id)) {
			  if(s.pw.equals(login.pw))
				  return JsonWrapper.ToJson(SEND_TYPE.SIGNINSTAFF, s);
		  }			  
	  }

	  for(StudentInfo s : member.students ) {
		  if(s.id.equals(login.id)) {
			  if(s.pw.equals(login.pw))
				  return JsonWrapper.ToJson(SEND_TYPE.SIGNINSTUDENT, s);
		  }
	  }
	  
	return JsonWrapper.ToJson(SEND_TYPE.SIGNIN, "Error");
  }
  
  private String SignUp(StaffInfo stff) {
	  FileManager fmgr = new FileManager();
	  MemberList member = fmgr.GetMemberListFromFile();

	  for(StaffInfo s : member.staffs) {
		  if(s.id.equals(stff.id))
			  return JsonWrapper.ToJson(SEND_TYPE.ERROR, "Error");
	  }

	  for(StudentInfo s : member.students ) {
		  if(s.id.equals(stff.id))
			  return JsonWrapper.ToJson(SEND_TYPE.ERROR, "Error");
	  }
	  
	  member.staffs.add(stff);
	  fmgr.SaveMemberListToFile(member);
	  return JsonWrapper.ToJson(SEND_TYPE.SIGNUPSTAFF, stff);
  }
  
  private String SignUp(StudentInfo stdnt) {
	  FileManager fmgr = new FileManager();
	  MemberList member = fmgr.GetMemberListFromFile();

	  for(StaffInfo s : member.staffs ) {
		  if(s.id.equals(stdnt.id))
			  return JsonWrapper.ToJson(SEND_TYPE.ERROR, "Error");
	  }
	  
	  for(StudentInfo s : member.students ) {
		  if(s.id.equals(stdnt.id))
			  return JsonWrapper.ToJson(SEND_TYPE.ERROR, "Error");
	  }

	  
	  member.students.add(stdnt);
	  fmgr.SaveMemberListToFile(member);
	  return JsonWrapper.ToJson(SEND_TYPE.SIGNUPSTUDENT, stdnt);
  }
    
  private String MakeRecruitment(StaffInfo staff) {
	  FileManager fmgr = new FileManager();
	  MemberList member = fmgr.GetMemberListFromFile();
	  
	  for(StaffInfo s: member.staffs) {
		  if(s.id.equals(staff.id)) {
			  s.pList = staff.pList;
			  break;
		  }
	  }
	  
	  fmgr.SaveMemberListToFile(member);
	  return JsonWrapper.ToJson(SEND_TYPE.MAKERECRUITMENT, staff);
  }
  
  private String MakeApplication(StudentInfo stdnt) {
	  FileManager fmgr = new FileManager();
	  MemberList member = fmgr.GetMemberListFromFile();
	  
	  for(StudentInfo s : member.students) {
		  if(s.id.equals(stdnt.id)) {
			  s.aList = stdnt.aList;
			  break;
		  }
	  }
	  
	  fmgr.SaveMemberListToFile(member);
	  return JsonWrapper.ToJson(SEND_TYPE.MAKEAPPLICATION, stdnt);
  }
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port);
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
//End of EchoServer class
