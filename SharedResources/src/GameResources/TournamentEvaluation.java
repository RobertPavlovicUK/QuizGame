package GameResources;

import Authentication.Users;

public class TournamentEvaluation {
	
	Question q;
	int time;
	Boolean ans;
	Users u;
	int Qnum;
	public TournamentEvaluation()
	{
		
	}
	public TournamentEvaluation(Question q, int time, Boolean ans, Users u, int qnum) {
		super();
		this.q = q;
		this.time = time;
		this.ans = ans;
		this.u = u;
		Qnum = qnum;
	}
	public Question getQ() {
		return q;
	}
	public void setQ(Question q) {
		this.q = q;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Boolean getAns() {
		return ans;
	}
	public void setAns(Boolean ans) {
		this.ans = ans;
	}
	public Users getU() {
		return u;
	}
	public void setU(Users u) {
		this.u = u;
	}
	public int getQnum() {
		return Qnum;
	}
	public void setQnum(int qnum) {
		Qnum = qnum;
	}
	
	
}
