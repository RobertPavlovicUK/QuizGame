package Server;
import java.util.ArrayList;

import GameResources.Question;
public class QuestionsResources {
	private static QuestionsResources _instance;
	private ArrayList<Question> questions;
	
	
	private void getQuestions()
	{
		questions=Database.getSeassion().getQuestionDB();
		
	}
	
	public static QuestionsResources getInstance()
	{
		
		if(_instance != null)
		{
			_instance.getQuestions();
			return _instance;
		}
		
	 _instance = new QuestionsResources();
		_instance.getQuestions();
		return _instance;
	}
	
	public ArrayList<Question> getRandomQuestions(int max)
	{
		ArrayList<Question> temp = (ArrayList<Question>) questions.clone();
		ArrayList<Question> randomQuestions = new ArrayList<>();
		for(int i = 0; i<max; i++)
		{
			int rnum = (int) (Math.random()*(temp.size()-0)+0);
			Question random = temp.get(rnum);
			
			temp.remove(rnum);
			randomQuestions.add(random);
			
		}
		return randomQuestions;
		
	}

}
