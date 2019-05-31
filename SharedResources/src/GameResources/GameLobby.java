package GameResources;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Authentication.Users;

public class GameLobby {
	
	String lobbyName;
	public Users admin;

	public Users getAdmin() {
		return admin;
	}
	
	public ArrayList<Users> players = new ArrayList<Users>();
	
	public ArrayList<Question> lobbyQuestions;
	public ArrayList<SeassionScore> playersScores = new ArrayList<SeassionScore>();
	public ArrayList<Messages> messages = new ArrayList<Messages>();
	public ArrayList<TournamentEvaluation> evaluation = new ArrayList<TournamentEvaluation>();
	public Boolean Ready= false;
	public int maxPlayers;
	public int maxQuestions;
	public int m;
	
	

	public GameLobby()
	{
		
	}
	public GameLobby(Users admin, String lobbyName,int maxQuestions, int maxPlayers,int m)
	
	{
		this.maxPlayers= maxPlayers;
		this.maxQuestions = maxQuestions;
		this.admin = admin;
		this.lobbyName = lobbyName;
		
	this.m = m;
	}
	public ArrayList<SeassionScore> getPlayersScores() {
		return playersScores;
	}
	public void setPlayersScores(ArrayList<SeassionScore> playersScores) {
		this.playersScores = playersScores;
	}
	 
	
	public void setAdmin(Users e)
	{
		admin = e ;
	}
	
	public void setLobbyName(String l)
	{
		lobbyName = l;
		
	}
	
	public void createQuestions(ArrayList<Question> q)
	{
		lobbyQuestions = q;
	}
	
	public String getLobbyName()
	{
			return lobbyName;
	}
	
	public ArrayList<Question> getQuestions()
	{	
		
		return lobbyQuestions;
	}
	public ArrayList<Users> getPlayers()
	{	
		
		return players;
	}
	
	public Boolean AddPlayer(Users e)
	{
		if(players.size()<= maxPlayers-1)
		{
			players.add(e);
			playersScores.add(new SeassionScore(e,0,0));
			System.out.println("ADDED"+players.size());
			return true;
		}
		else 
			{
			System.out.println("NOT ADDED"+players.size());
			return false;
			}
	}
	public void createUser(ArrayList<Users> q)
	{
		players = q;
	}
	

	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	
	@Override
	public boolean equals(Object e)
	{
		
		GameLobby obj = (GameLobby) e;
		if(this.lobbyName.equals(obj.getLobbyName()) )
				{
					return true;
				}
		else
		return false;
		
		}
	@Override
	public String toString()
	{	String type = "";
		if(this.m == 2)
		{
			type ="'FRIENDLY MODE'";
		}
		if(this.m == 1)
		{
			type ="'PRACTICE MODE'";
		}
		if(this.m == 3)
		{
			type ="'TOURNAMENT MODE'";
		}
		return this.getLobbyName()+"														"+"                                                              "+type+"                                                                      "+this.getPlayers().size()+"/"+this.maxPlayers;
	}
	
	public SeassionScore findSeassionScore(Users u, SeassionScore ss)
	{
		for(SeassionScore s : playersScores)
		{
			if(s.getUsername().getUsername().equals(u.getUsername()))
			{
				
				playersScores.add(playersScores.indexOf(s), ss);
				playersScores.remove(playersScores.indexOf(s));
				
				return s;
			}
		}
		return null;
	}
	public SeassionScore findSeassionScore1(Users u, SeassionScore ss)
	{
		for(SeassionScore s : playersScores)
		{
			if(s.getUsername().getUsername().equals(u.getUsername()))
			{
				double currentPoints = s.getPoints()+ss.getPoints();
				
				playersScores.add(playersScores.indexOf(s), new SeassionScore(u,currentPoints,ss.getQuestionNumber()));
				playersScores.remove(playersScores.indexOf(s));
				
				return s;
			}
		}
		return null;
	}
	
	public void AddMessage(Messages m)
	{
		messages.add(m);
	}
	
	public ArrayList<Messages> getMessages()
	{
		return messages;
		
	}
	
	public TournamentResult getResult()
	{
		TournamentResult result =null;;
		int shortest=0;
		Users winner= null;
		System.out.println(evaluation.size()+"  sadaw "+players.size());
		if(evaluation.size() == players.size())
		{
					
		for(TournamentEvaluation e : evaluation)
		{
			System.out.println("evaluating");
			if(e.getAns())
			{
				
				if(e.getTime()> shortest)
				{
					winner = e.getU();
					shortest =e.getTime();
					
				}
			}
			
		}
		evaluation.clear();
		return  result = new TournamentResult(winner,shortest);
		}
		
		return null;
		
	}
	
	
		
	
	
}
