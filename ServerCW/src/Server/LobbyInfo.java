package Server;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Authentication.Users;
import GameResources.Messages;
import GameResources.SeassionScore;
import Server.LobbyResources;
@Path("/Lobby")
public class LobbyInfo {

	
	@GET
	@Path("/{lobbyname}/getPlayersScores")
	@Produces(MediaType.APPLICATION_JSON)
    public void getlobbiesScore(@Suspended final AsyncResponse asyncResponse,@PathParam("lobbyname") String lobbyname) {

		
            	   asyncResponse.resume(LobbyResources.getInstance().findLobby(lobbyname).playersScores); 
               
                
                

	}
	
	@POST
	@Path("/{lobbyname}/getlobbyupdate")
	@Produces(MediaType.APPLICATION_JSON)
    public Response UpdateScore(@PathParam("lobbyname") String lobbyname, SeassionScore s) {
		
			
          SeassionScore ss =  LobbyResources.getInstance().findLobby(lobbyname).findSeassionScore(s.getUsername(),s) ;
            
                return Response.ok().build();

	}

	

}
