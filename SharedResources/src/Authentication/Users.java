package Authentication;

import javax.xml.bind.annotation.XmlRootElement;


public class Users {

public String username;
public String password;
public int ID;
public int score;
public Users()
{}
public Users(String username, String password)
{

this.username = username;
this.password = password;
}
public Users(String username, String password, int ID,int score)
	{
	this.ID=ID;
	this.username = username;
	this.password = password;
	this.score =score;
	}




public String getUsername()
{
	return username;
}
public String getPassword()
{
	return password;
}
public int getScore()
{
	return score;
}
public int getId()
{
	return ID;
}

@Override
public boolean equals(Object e)
{
	Users obj = (Users)e;
	if(this.username.equals(obj.getUsername()))
			{
				return true;
			}
	else
	return false;
	
	}
@Override
public String toString( )
{
	return this.username;
}
}