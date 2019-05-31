package Server;


import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.GET;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import Authentication.*;



@Path("/Authentication")
public class HelloWorld {
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Users> helloword(String hello)
	{
		//return Response.status(200).entity( "{'friends': ['Michael', 'Tom', 'Daniel', 'John', 'Nick']}").build();
		
		return UsersResources.getInstance().getListOfUsers();
	}
	
	@POST
	@Path("/GetUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Users GetUser(Users user)
	{
		
		return UsersResources.getInstance().getUser(user.getUsername());
	}
	
	@POST
	@Path("/CreateUser")
	@Consumes(MediaType.APPLICATION_JSON)
	
	public Response CreateUser(Users user)
	{
		
		UsersResources.getInstance().CreateUser(user.getUsername(), user.getPassword());
		
		return Response.ok().build();
	}

	@GET
	@Path("/OnlineUsers")
	@Produces(MediaType.APPLICATION_JSON)
    public void getOnlineUsersAsync(@Suspended final AsyncResponse asyncResponse) {

	
            	   asyncResponse.resume(UsersResources.getInstance().getUsersOnline()); 
               
                
                

	}
	@POST
	@Path("/Connect")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response getOnlineUsersAsync(Users u) {

	
		UsersResources.getInstance().AddOnlinePlayer(u);
		return Response.ok().build();
               
                
                

	}
	@POST
	@Path("/Disconnect")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response RemovePlayers(Users u) {

	
		UsersResources.getInstance().RemovePlayer(u);
		return Response.ok().build();
               
                
                

	}
	
}

