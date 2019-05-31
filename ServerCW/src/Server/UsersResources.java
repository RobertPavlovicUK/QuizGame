package Server;

import java.sql.SQLException;
import java.util.ArrayList;

import Authentication.Users;

public class UsersResources {

	private static UsersResources _instance;
	private ArrayList<Users> listOfUsers = new ArrayList<Users>();
	private ArrayList<Users> onlineUsers = new ArrayList<Users>();
	
	
	public static UsersResources getInstance()
	{
		if( _instance != null)
		{
			return _instance;
		}
		
		return _instance = new UsersResources();
	}
	
	public ArrayList<Users> getListOfUsers()
	{
		return Database.getSeassion().GetUsers();
	}
	
	public Users getUser(String name)
	{
		return Database.getSeassion().getUser(name);
	}
	
	public void CreateUser(String username, String password)
	{
		try {
			Database.getSeassion().RegisterUser(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void AddOnlinePlayer(Users y)
	{
		onlineUsers.add(y);
	}
	
	public ArrayList<Users> getUsersOnline()
	{
		return onlineUsers;
	}

	public void RemovePlayer(Users u) {
		
		onlineUsers.remove(u);
		
		
	}
	
}
