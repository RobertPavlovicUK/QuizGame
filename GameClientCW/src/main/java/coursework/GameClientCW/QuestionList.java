package coursework.GameClientCW;

import java.util.ArrayList;

import GameResources.Question;

public class QuestionList {

	public ArrayList<Question> questions;
	private int currentQuestion=0;
	public Question currentQuestionQ;
	public QuestionList(ArrayList<Question> list)
	
	{
		this.questions = list;
		
	}
	
	public Question getQuestion()
	{
		for(Question q : questions)
		{
			System.out.println(q.getCans());
		}
		System.out.println("FROM TEST"+ questions.size());
			Question temp = questions.get(currentQuestion);
			currentQuestionQ = temp;
			System.out.println(temp.getCans());
			currentQuestion++;
				return  temp;
		
	}
	
}
