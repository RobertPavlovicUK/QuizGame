package Server;

import java.util.ArrayList;

import GameResources.GameLobby;

public class LobbyScoreResources {
	



		private static LobbyScoreResources _instance;

		
		
		
		
		
		public static LobbyScoreResources getInstance()
		{
			if(_instance != null)
			{
				return _instance;
			}
			return _instance = new LobbyScoreResources();
					
			
		}

}
