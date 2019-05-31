package Server;


import java.sql.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Authentication.Users;
import GameResources.Question;
/**
 * Created by pavlo on 20/02/2018.
 */

public class Database {

    private static Database _instance;
    private Statement stmnt;
    private Connection con;
   
    public Database()
    {
        try {
            System.out.println("Hello");
            Class.forName("com.mysql.jdbc.Driver");
           
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dissystem?user=admin&password=admin");

            System.out.println("Connection Successful");


//            ResultSet rs = stmt.executeQuery("select * from emp");
//            while (rs.next())
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {

        e.printStackTrace();
        }
    }
    
    public Users getUser(String name)
    {
    	try {
    		Users user = new Users("","");
    	String statement = "SELECT * from users where username ='"+name+"'";
    	
    	 stmnt = con.createStatement();
    	
    	ResultSet set = stmnt.executeQuery(statement);
    	
    	while(set.next())
    	{
    		
    	
    		user = new Users(set.getString("username"),set.getNString("password"),set.getInt("ID"),set.getInt("score"));
    	}
    	
    	return user;
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
    }
    
    public ArrayList<Users> GetUsers()
    {
    	ArrayList<Users> users = new ArrayList<Users>();
    	try {
    		String statement = "SELECT*FROM users;";
			stmnt = con.createStatement();
			
			ResultSet set = stmnt.executeQuery(statement);
			
			while(set.next())
			{	int id = set.getInt("ID");
			
				String username = set.getNString("username");
				String password = set.getNString("password");
				
				users.add(new Users(username,password,id,set.getInt("score")));
				
			}
			
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    }

    public void RegisterUser(String username, String password) throws SQLException // TODO handle exception
    {
       
       
        String SQLInsertQuery = "INSERT INTO users (username,password,score) values(?,?,?)";

       PreparedStatement stm = con.prepareStatement(SQLInsertQuery);
       
       stm.setString(1, username);
       stm.setString(2, password);
       stm.setInt(3, 0);
       
       stm.executeUpdate();
    }

   public ArrayList<Question> getQuestionDB()
   {
	   try {
	   ArrayList<Question> temp = new ArrayList<Question>();
	   
	   String SQLQuery = "SELECT * FROM questions;";

	   Statement stm = con.createStatement();
	   
	   
	   
	   ResultSet rs =stm.executeQuery(SQLQuery);
	   while(rs.next())
	   {
		   temp.add(new Question(rs.getInt("questionid"),rs.getNString("question"),rs.getNString("correctans"),rs.getNString("wrongans1"),rs.getNString("wrongans2"),rs.getNString("wrongans3"),rs.getNString("wrongsans4"),rs.getInt("BasePoints")));
	   }
	   
	   return temp;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return null;
   }

    
    public static  Database getSeassion()
    {
        if(_instance == null)
        {                
            _instance = new Database();
            return _instance;
        }
        else
        {
            return _instance;
        }

    }

    private boolean isConnectionClosed()
    {
        try {
            if (con.isClosed()) {
                return true;

            } else {
                return false;
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        	return false;
    }

}
