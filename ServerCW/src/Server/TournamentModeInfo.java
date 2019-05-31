package Server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import GameResources.Messages;
import GameResources.SeassionScore;
import GameResources.TournamentEvaluation;
import GameResources.TournamentResult;
@Path("Lobby")
public class TournamentModeInfo {
	
	
	@POST
	@Path("/{lobbyname}/{username}/submitAns")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response Submit(@PathParam("lobbyname") String lobbyname, @PathParam("username") String username, TournamentEvaluation e) {
		
			System.out.println("Evaluation");
         LobbyResources.getInstance().findLobby(lobbyname).evaluation.add(e);
        TournamentResult res= LobbyResources.getInstance().findLobby(lobbyname).getResult();
      
         if(res != null && res.user != null)
         {
        	
        	 Messages s = new Messages("Server: ",res.user.username+"has won the round with time "+res.timer);
        	 
        	 LobbyResources.getInstance().findLobby(lobbyname).findSeassionScore1(res.user,new SeassionScore(res.user,10,e.getQnum()));
        	 LobbyResources.getInstance().findLobby(lobbyname).AddMessage(s); 
        	 
         }
            
                return Response.ok().build();

	}
	

}
