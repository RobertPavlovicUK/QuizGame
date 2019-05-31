package GameResources;

import Authentication.Users;

public class TournamentResult {
	
	public Users user;
	public int timer;
	
	public TournamentResult()
	{
		
	}
	public TournamentResult (Users winner, int timer)
	{
		this.user = winner;
		this.timer =timer;
	}

}
