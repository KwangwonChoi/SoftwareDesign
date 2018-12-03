// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package OCSF.client;

import OCSF.client.*;
import OCSF.common.*;
import java.io.*;

import com.google.gson.*;

import DataManage.JsonFormat.LoginInfo;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //SINGLE TONE *****************************************************
	
	protected static ChatClient _instance;
	
	public static ChatClient GetInstance() {
				
		return _instance;	
	}
	
	public static void SetInstance(String host, int port, ChatIF clientUI) {
		try {
			
			_instance = new ChatClient(host,port,clientUI);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  String stringFromServer;

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  protected ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
    stringFromServer = msg.toString();
  }

  
  public String GetstringFromServer() {
	  String ret = stringFromServer;
	  
	  stringFromServer = null;
	  
	  return ret;
  }
  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
    try
    {
    	String[] str = message.split(" ");
    	LoginInfo info = new LoginInfo(str[0],str[1]);
    	Gson gson = new Gson();
    	String msg = gson.toJson(info);
      sendToServer(msg);
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
