package Server;

import javax.ws.rs.GET;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import Authentication.Users;

import javax.ws.rs.*;

@Path("")
public class AsyncClass {
	
	   
	@GET
    public void asyncRestMethod(@Suspended final AsyncResponse asyncResponse) {

		
            	   asyncResponse.resume(new Users("sa","sad")); 
               
                
                

	}
	


}
