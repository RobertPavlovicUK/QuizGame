package Server;

import java.util.ArrayList;

import GameResources.GameLobby;

public class LobbyResources {

	private static LobbyResources _instance;

	ArrayList<GameLobby> allLobby = new ArrayList<>();
	
	
	
	public static LobbyResources getInstance()
	{
		if(_instance != null)
		{
		
			return _instance;
		}
	
		return _instance = new LobbyResources();
		
	}
	
	public void AddLobby(GameLobby l)
	{
		allLobby.add(l);
	}
	
	public void RemoveLobby(GameLobby l)
	{
		allLobby.remove(l);
	}
	
	public ArrayList<GameLobby> getAllLobby()
	{
		return allLobby;
	}
	
	public GameLobby findLobby(String lobbyName)
	{
	
		
		for(GameLobby l : allLobby)
		{
			
			if(l.getLobbyName().equals(lobbyName))
			{
				return l;
			}
		}
		
		return new GameLobby();
		
	}
	
	
	
	
	
}
