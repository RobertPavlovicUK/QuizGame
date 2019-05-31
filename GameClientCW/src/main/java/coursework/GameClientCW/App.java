package coursework.GameClientCW;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Future;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import Authentication.Users;
import Database.DatabaseRes;
import GameResources.GameLobby;
import GameResources.Question;
import javax.swing.Timer; 
/**
 * Hello world!
 *
 */
public class App 
{
	static String lobby= null;
	static Authentication auth;
    public static void main( String[] args )
    {
    	  EventQueue.invokeLater(new Runnable() {
    		   public void run() {
    		    try {
    		     LogInPage window = new LogInPage();
    		     System.out.println("sadw");
    		     	
    		     
    		    
    		    } catch (Exception e) {
    		     e.printStackTrace();
    		    }
    		   }
    		  });
    		
    	

    }
    
    
    public static void t()
    {
    	System.out.println("Hsda");

    }
    

	private static void joinLobby() {

    	Scanner sk = new Scanner(System.in);
    	int choise;
    	while(true)
    	{
    		switch(choise = sk.nextInt())
    		{
    		case 1 : createLobby();
    		break;
    		case 2 : joinLobby();
    		}
    		
    	}
		
	}



	public static void createLobby()
	{
try {
    		
    		WebResource wb = RESTConnection.getConnection().setResource("Lobby/CreateLobby").getWebResource();
    		GameLobby g = new GameLobby();
    				g.setLobbyName("test3");
    				ArrayList<Users> s = new ArrayList<Users>();
    				s.add(new Users("s","s"));
    				lobby =g.getLobbyName();
    			
    			g.createUser(s);
    		ClientResponse response = wb.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,g);
    		addAdmin(g.getLobbyName(),"admin");
    			
    		System.out.println("createdLobby");
    		t();
    	}
    		catch(Exception e) {
    		e.printStackTrace();
    		}
    
		
	}
	public static void addAdmin(String lobbyname,String name)
	{
try {
    		
    		WebResource wb = RESTConnection.getConnection().setResource("Lobby/"+lobbyname+"/AddPlayer").getWebResource();
    		
    		ClientResponse response = wb.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,new Users(name,"Test"));
    		
    		
    		System.out.println("createdLobby");
    	}
    		catch(Exception e) {
    		e.printStackTrace();
    		}
    
		
	}

    }

