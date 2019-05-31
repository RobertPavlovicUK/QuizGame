package GameResources;

import Authentication.Users;

public class SeassionScore {
	
	public Users username;

	public double points;
	public int questionNumber;
	public Boolean ready;
	public SeassionScore() {
		
	}
	public SeassionScore(Users username, double points, int questionNumber) {
	
		this.username = username;
		this.points = points;
		this.questionNumber = questionNumber;
	}
	
	public Boolean getReady() {
		return ready;
	}
	public void setReady(Boolean ready) {
		this.ready = ready;
	}
	
	
	public void addScore()
	{
		points = points+10;
	}
	public Users getUsername() {
		return username;
	}
	
	public double getPoints() {
		return points;
	}
	
	public int getQuestionNumber() {
		return questionNumber;
	}
	
	@Override
	public String toString()
	{
		return username+" "+questionNumber+" - "+points;
	}
	@Override
	public boolean equals(Object e)
	{
		
		SeassionScore obj = (SeassionScore) e;
		if(this.getUsername().equals(obj.getUsername()) && this.getPoints() == obj.getPoints() && this.getQuestionNumber() == obj.getQuestionNumber() )
				{
					return true;
				}
		else
		return false;
		
		}
	
	
	

}
