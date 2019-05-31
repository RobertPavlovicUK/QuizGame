package Server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import GameResources.Messages;

@Path("Lobby")
public class MessagingService {
	@POST
	@Path("/{lobbyname}/sendMessage")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response SendMessage(@PathParam("lobbyname") String lobbyname, Messages s) {
		
		
			System.out.println("GSADWADs");
         LobbyResources.getInstance().findLobby(lobbyname).AddMessage(s); 
            
                return Response.ok().build();

	}
	
	@GET
	@Path("/{lobbyname}/ReceiveMessages")
	@Produces(MediaType.APPLICATION_JSON)
	   public void getlobbiesScore(@Suspended final AsyncResponse asyncResponse,@PathParam("lobbyname") String lobbyname) {

		
 	   asyncResponse.resume( LobbyResources.getInstance().findLobby(lobbyname).getMessages() ); 
    
     
			
        
            
              

	}
}
