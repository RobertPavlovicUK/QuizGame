package Server;

import java.util.ArrayList;

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

import Authentication.Users;
import GameResources.GameLobby;

import GameResources.Question;
@Path("/Lobby")
public class Lobby {
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<GameLobby> GetAllLobby()
	{
			
		return LobbyResources.getInstance().getAllLobby();
	}
	
	
	
	
		@POST
		@Path("CreateLobby")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response GetAllLobby(GameLobby lob)
		{
		
			
			
			
			lob.createQuestions(QuestionsResources.getInstance().getRandomQuestions(lob.maxQuestions));
			LobbyResources.getInstance().AddLobby(lob);
			lob.AddPlayer(lob.getAdmin());
			
			return Response.ok().build();
		}
		

		@GET
		@Path("{lobbyname}/getLobby")
		
		@Produces(MediaType.APPLICATION_JSON)
	    public GameLobby asyncRestMethod(@PathParam("lobbyname") String lobbyname) {

					
	            	   
						
						return LobbyResources.getInstance().findLobby(lobbyname);
	               
	                
	                

		}
		
		

		@POST
		@Path("{lobbyname}/AddPlayer")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response AddPlayer(@PathParam("lobbyname") String lobbyname, Users user)
		{
			
			System.out.println("asdawdasdawda"+lobbyname);
		Boolean added =	LobbyResources.getInstance().findLobby(lobbyname).AddPlayer(user);
			if(added)
			{
				System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqNOT 404");
				return Response.ok().build();
				}
			
			else {
				System.out.println("404");
				return Response.status(404).build();
				
			}
			
		}
		
		@GET
		@Path("{lobbyname}/getQuestions")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<Question> GetAllLobyQuestions(@PathParam("lobbyname") String lobbyname)
		{
			
			return LobbyResources.getInstance().findLobby(lobbyname).getQuestions();
		}
		
	
	
		@GET
		@Path("{lobbyname}/getPlayers")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
	    public void asyncRestMethod(@Suspended final AsyncResponse asyncResponse,@PathParam("lobbyname") String lobbyname) {

					
	            	   asyncResponse.resume(LobbyResources.getInstance().findLobby(lobbyname).getPlayers()); 
	               
	                
	                

		}
		
		@GET
		@Path("/GetAll")
		@Produces(MediaType.APPLICATION_JSON)
	    public void GetAllLobbies(@Suspended final AsyncResponse asyncResponse) {

							
	            	   asyncResponse.resume(LobbyResources.getInstance().getAllLobby()); 
	               
	                
	                

		}
		
		
	
	
}

