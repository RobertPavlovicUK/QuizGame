package GameResources;

import javax.xml.bind.annotation.XmlRootElement;


public class Question {

	String question;
	String cans;
	String wans1;
	String wans2;
	String wans3;
	String wans4;
	int basepoint;

	public Question()
	{}
	public Question(int id)
	{
		this.questionID = id;
	}
	public Question(int questionID,String question,String cans,String wans1, String wans2,String wans3,String wans4, int basepoint)
	{
		this.questionID = questionID;
		this.question = question;
		this.cans=cans;
		this.wans1=wans1;
		this.wans2=wans2;
		this.wans3=wans3;
		this.wans4=wans4;
	
		
	}
	
	int questionID;
	public int getQuestionID() {
		return questionID;
	}

	

	public String getQuestion() {
		return question;
	}



	public String getCans() {
		return cans;
	}

	public String getWans1() {
		return wans1;
	}

	

	public String getWans2() {
		return wans2;
	}

	
	public String getWans3() {
		return wans3;
	}


	public String getWans4() {
		return wans4;
	}

	
	public int getBasepoint() {
		return basepoint;
	}
	
	

	

	

	
	
}
