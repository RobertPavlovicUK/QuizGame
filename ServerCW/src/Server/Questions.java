package Server;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Authentication.Users;
import GameResources.Question;


@Path("/Questions")
public class Questions {
	
	@GET
	@Path("/GetQuestions")
	
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Question> GetUser()
	{
		
		return QuestionsResources.getInstance().getRandomQuestions(14);
	}

}
