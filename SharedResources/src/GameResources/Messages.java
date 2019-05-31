package GameResources;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Messages {
	
	public String username;
	public String message;
	
	public Messages()
	{}
	public Messages(String username,String message)
	{
		this.username = username;
		this.message=message;
		Date d = new Date();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(d);
		this.message = "["+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+"] "+username+": "+message;
	}
	
	public String getMessage()
	{
		return message;
		
	}
	@Override
	public String toString( )
	{
		return message;
	}

}
