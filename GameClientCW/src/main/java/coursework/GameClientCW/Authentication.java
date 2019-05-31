package coursework.GameClientCW;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import Authentication.Users;
import Database.DatabaseRes;

public class Authentication  {
	String username;
	String password;
	public static Users loggedIn;

	public Authentication(String username, String password) {
				
		this.username = username;
		this.password= password;

	}
	
	public void LogIn(OnLoginListener i)
	{
		
		WebResource wb = RESTConnection.getConnection().setResource("Authentication/GetUser").getWebResource();
		
		ClientResponse response = wb.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,new Users(username,password));
		if (response.getStatus() != 200) {
			i.onFail(DatabaseRes.DATABASE_CONNECTION_ERROR);
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}

		
		Users result = response.getEntity(Users.class);
		System.out.println("asd"+ result.getUsername());
		if(result.getUsername().equals(""))
		{
			i.onFail(DatabaseRes.DATABASE_ACCOUNT_NOT_FOUND);
			return;
		}
		System.out.println(result.getPassword() +" "+ password);
		if(!password.equals(result.getPassword()))
		{
			
			i.onFail(DatabaseRes.DATABASE_ACCOUNT_PASSOWRD_INCORECT);
		}
		if(password.equals(result.getPassword()))
		{
			if(username.equals(result.getUsername()))
			{
				loggedIn = result;
				i.onSuccess(DatabaseRes.DATABASE_ACCOUNT_LOGIN_SUCCESSFUL);
			}
		}

		
	}
	
	
	public void Register(OnLoginListener i)
	{
		WebResource wb = RESTConnection.getConnection().setResource("Authentication/GetUser").getWebResource();
		
		ClientResponse response = wb.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,new Users(username,password));
		if (response.getStatus() != 200) {
			
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
		
		Users result = response.getEntity(Users.class);
		if(!result.getUsername().equals(""))
		{
			i.onFail(DatabaseRes.DATABASE_ACCOUNT_ALREADY_EXISTS);
		return;
		}
		if(result.getUsername().equals(""))
		{
				System.out.println("From if "+result.getUsername()+ " "+ username);
			WebResource web = RESTConnection.getConnection().setResource("Authentication/CreateUser").getWebResource();
			System.out.println(username +" sdaawdas "+password);
			Users u = new Users(username,password);
			System.out.println(u.getUsername() +" sdas "+u.getPassword());
			ClientResponse responsew = web.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,u);
			System.out.println(username +" sdaawdas "+password);
			if (response.getStatus() != 200) {
				
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
			i.onSuccess(DatabaseRes.DATABASE_ACCOUNT_REGISTERED);
			
		}
		
	}
	
	public void joinOnline()
	{
WebResource wb = RESTConnection.getConnection().setResource("Authentication/Connect").getWebResource();
		
		ClientResponse response = wb.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,new Users(username,password));
	}
	
	


	
	
	
	public static void logOut()
	{
WebResource wb = RESTConnection.getConnection().setResource("Authentication/Disconnect").getWebResource();
		
		ClientResponse response = wb.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,loggedIn);
		System.out.println("Hello");
	}
	

}
