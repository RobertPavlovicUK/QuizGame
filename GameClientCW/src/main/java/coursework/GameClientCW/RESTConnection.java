package coursework.GameClientCW;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import Authentication.CWResources;

public class RESTConnection {
	private  static RESTConnection _instance;
	private String baseURL;
	private Client client;
	private String IPAddress = CWResources.IpAddress;
	WebResource web;
	public RESTConnection()
	{
		client = Client.create();
		
	}
	
	public RESTConnection(String resource,String IPAddress)
	{
		
		
		
	}
	
	public static RESTConnection getConnection()
	{
		
		if(_instance != null)
		{
			return _instance;
		}
		return _instance = new RESTConnection();
	}
	
	public RESTConnection setResource(String resource)
	{
		System.out.println("http://"+IPAddress+":8080/ServerCW/"+resource);
		web = client.resource("http://"+IPAddress+":8080/ServerCW/"+resource);
		return _instance;
	}
	
	public WebResource getWebResource()
	{
		return web;
	}
	
	
}
