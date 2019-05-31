package coursework.GameClientCW;



import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.MediaType;
import Authentication.CWResources;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import Authentication.Users;
import Database.DatabaseRes;
import GameResources.GameLobby;
import GameResources.Messages;
import GameResources.Question;
import GameResources.SeassionScore;
import GameResources.TournamentEvaluation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class GameInterface extends JFrame implements MouseListener {

 private GameLobby lobby;
 Boolean started = false;
 JLabel ansLab1;
 private long WaitTime = 50000;
 private int int_mil;
 private int int_sec;
 private JPanel contentPane;
 JLabel firstLabel;
 JLabel secondLabel;
 JLabel thirdLabel;
 JLabel fourthLabel;
 JLabel fifthlabel;
 JLabel sixthLabel;
 private JTextField textField;
 Stopwatch t;
 DefaultListModel<SeassionScore> playerList;
 DefaultListModel<Messages> messages;
 QuestionList Qlist;
 Timer updateTimer = new Timer(); 
 Boolean shouldcancel = false;
	Boolean bol =false;
 private int questNum,points;
 long gameTimer = 50000;
 long startTime;
 ArrayList<Users> p = new ArrayList<Users>(); 
 Date da = new Date();
 Calendar s = GregorianCalendar.getInstance();

 

 
 private ArrayList<Question> questions;
 JLabel questionLab; 
	JLabel pointsLab;
	JLabel timerLab ;
	protected boolean shouldStart= false;
	private boolean ShouldStop;
	private boolean selected;
	private boolean notSend;
public GameInterface(GameLobby lobby)
{
	this.lobby = lobby;
	init();
	

}

private void init()
{
	this.addWindowListener(new WindowListener()
			{

				public void windowActivated(WindowEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void windowClosed(WindowEvent arg0) {
					MenuWindow.mainWindow.setVisible(true);
					
				}

				public void windowClosing(WindowEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void windowDeactivated(WindowEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void windowDeiconified(WindowEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void windowIconified(WindowEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void windowOpened(WindowEvent arg0) {
					// TODO Auto-generated method stub
					
				}
		
			});
	playerList = new DefaultListModel<SeassionScore>() ;
	messages = new DefaultListModel<Messages>() ;
	questNum = 1;
	points = 0;
	
	Updater t = new Updater(1500, new OnTickListener()
			{

				public void onUpdate() {
				
					ReceiveMessages();
					ReceiverPlayersUpdates();
					
					
				}
		
			}
			);
	Updater tt = new Updater(1, new OnTickListener()
	{

		public void onUpdate() {
			
			TimerUpdates();
			
			
		}

	}
	);
	t.start();
	tt.start();
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setBounds(100, 100, 982, 614);
	contentPane = new JPanel();
	contentPane.setBackground(Color.GRAY);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	 timerLab = new JLabel("0");
	timerLab.setFont(new Font("Tahoma", Font.PLAIN, 53));
	timerLab.setBounds(375, 18, 74, 70);
	contentPane.add(timerLab);
	
	JPanel panel = new JPanel();
	panel.setBackground(Color.DARK_GRAY);
	panel.setBounds(12, 101, 761, 283);
	contentPane.add(panel);
	panel.setLayout(null);
	
	 firstLabel = new JLabel("New label");
	 firstLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
	 firstLabel.setForeground(Color.WHITE);
	 firstLabel.setBounds(30, 20, 737, 24);
	panel.add(firstLabel);
	
	 secondLabel = new JLabel("New label");
	secondLabel.setForeground(Color.WHITE);
	secondLabel.setBounds(30, 60, 737, 24);
	panel.add(secondLabel);
	
	thirdLabel = new JLabel("New label");
	thirdLabel.setForeground(Color.WHITE);
	thirdLabel.setBounds(30, 100, 737, 24);
	panel.add(thirdLabel);
	
	 fourthLabel = new JLabel("New label");
	 fourthLabel.setForeground(Color.WHITE);
	 fourthLabel.setBounds(30, 140, 737, 24);
	panel.add(fourthLabel);
	
	 fifthlabel = new JLabel("New label");
	 fifthlabel.setForeground(Color.WHITE);
	fifthlabel.setBounds(30, 180, 737, 24);
	panel.add(fifthlabel);
	
	sixthLabel = new JLabel("New label");
	sixthLabel.setForeground(Color.WHITE);
	sixthLabel.setBounds(30, 220, 737, 24);
	panel.add(sixthLabel);
	
	JList list = new JList(messages);
	JScrollPane listScroller1 = new JScrollPane(list);
	listScroller1.setBounds(12, 410, 761, 117);
	contentPane.add(listScroller1);
	
	questionLab = new JLabel("/"+lobby.maxQuestions);
	questionLab.setFont(new Font("Tahoma", Font.PLAIN, 16));
	questionLab.setBounds(91, 44, 56, 16);
	contentPane.add(questionLab);
	
	JList list_1 = new JList(playerList);
	 JScrollPane listScroller = new JScrollPane(list_1);
	 listScroller.setBounds(788, 101, 164, 430);
	contentPane.add(listScroller);
	
	 pointsLab = new JLabel("Points: ");
	pointsLab.setFont(new Font("Tahoma", Font.PLAIN, 16));
	pointsLab.setBounds(589, 45, 90, 16);
	contentPane.add(pointsLab);
	
	JLabel label_6 = new JLabel("Lobby Scores:");
	label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
	label_6.setBounds(837, 45, 120, 16);
	contentPane.add(label_6);
	
	textField = new JTextField();
	textField.setBounds(12, 532, 619, 22);
	contentPane.add(textField);
	textField.setColumns(10);
	
	final JButton btnSend = new JButton("Send");
	btnSend.setBounds(656, 531, 97, 25);
	btnSend.addActionListener(new ActionListener()
			{

				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == btnSend)
					{
						if(!textField.getText().equals(""))
						{
						SendMessage();
						textField.setText("");
						}
					}
					
				}
		
			});
	contentPane.add(btnSend)	;
	 firstLabel.setVisible(false);;
	   secondLabel.setVisible(false);
	   thirdLabel.setVisible(false);
	   fourthLabel.setVisible(false);
	   fifthlabel.setVisible(false);
	   sixthLabel.setVisible(false);
	   
	   
	   secondLabel.addMouseListener(this);
	   thirdLabel.addMouseListener(this);
	   fourthLabel.addMouseListener(this);
	   fifthlabel.addMouseListener(this);
	   sixthLabel.addMouseListener(this);

		Qlist = new QuestionList(lobby.getQuestions());
		if(lobby.getM() == 1 || lobby.getM() == 2)
		{
			StartGame();
		}
		if(lobby.getM() == 3)
		{
			StartTimer();
		}

}

protected void TimerUpdates() {
	 Client client = ClientBuilder.newBuilder().build();

	 	
	 
	 
		
		
		
Future<ArrayList<SeassionScore>> target = client.target("http://"+CWResources.IpAddress+":8080/ServerCW/Lobby/"+lobby.getLobbyName()+"/getPlayersScores").request(MediaType.APPLICATION_JSON).async().get(new InvocationCallback<ArrayList<SeassionScore>>()
		
		{

	
				
				public void completed(ArrayList<SeassionScore> arg0) {
				
					for(SeassionScore s : arg0)
					{
							

						
						if(!p.contains(s.getUsername()))
						{	gameTimer = 50000;
							p.add(s.getUsername());
						}
						
					
				
						
						
					}
					
					
					
					
				}

				public void failed(Throwable arg0) {
					// TODO Auto-generated method stub
					
				}
	
		})
			;

	
	
}

private void StartTimer() {
	 ShouldStop = false;
	 final Timer timer = new Timer(); 
	 startTime = System.currentTimeMillis();
	 timer.scheduleAtFixedRate(new TimerTask() {
	        @Override
	        public void run() {
	        	
	        
	        		gameTimer = gameTimer-1;
	        		timerLab.setText(gameTimer/1000+"");
	        		startTime= System.currentTimeMillis();
	        	
	        	if(gameTimer<=0)
	        	{
	        		
	        		StartGame();
	        		timer.cancel();
	        		return;
	        	}
	        	
	        	
	        }
	        }, 0, 1);
	 
	

	
}



protected void SendMessage() {
	WebResource wb = RESTConnection.getConnection().setResource("Lobby/"+lobby.getLobbyName()+"/sendMessage").getWebResource();
	
	ClientResponse response = wb.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,new Messages(Authentication.loggedIn.getUsername(),textField.getText()));

	
}

protected void ReceiveMessages() {
	Client client = ClientBuilder.newBuilder().build();

 	
	 
	 
		
	
	messages.removeAllElements();;
 	Future<ArrayList<Messages>> target = client.target("http://"+CWResources.IpAddress+":8080/ServerCW/Lobby/"+lobby.getLobbyName()+"/ReceiveMessages").request(MediaType.APPLICATION_JSON).async().get(new InvocationCallback<ArrayList<Messages>>()
 			
 			{

					public void completed(ArrayList<Messages> arg0) {

						for(Messages s : arg0)
						{
						if(!messages.contains(s))
						{		
							messages.addElement(s);
						}
						}
						
						
						
						
					}

					public void failed(Throwable arg0) {
						// TODO Auto-generated method stub
						
					}
 		
 			})
 				;
 	
	
}

private void StartGame() {
	setVisibleLab(true);
	setQuestions(Qlist.getQuestion());
	
	
	
}

public String getNextQuestion(String clicked)
{
	return clicked;
	}

 protected void ReceiverPlayersUpdates() {
	 Client client = ClientBuilder.newBuilder().build();

	 	
	 
	 
	 		 
	
	 			playerList.removeAllElements();;
	 	Future<ArrayList<SeassionScore>> target = client.target("http://"+CWResources.IpAddress+":8080/ServerCW/Lobby/"+lobby.getLobbyName()+"/getPlayersScores").request(MediaType.APPLICATION_JSON).async().get(new InvocationCallback<ArrayList<SeassionScore>>()
	 			
	 			{

	 		
					

						public void completed(ArrayList<SeassionScore> arg0) {
							System.out.println("Getting called");
						
							for(SeassionScore s : arg0)
							{
							if(!playerList.contains(s))
							{		
								
								playerList.addElement(s);
								
								
								
							}
							
							}
							
							
							
							
						}

						public void failed(Throwable arg0) {
							// TODO Auto-generated method stub
							
						}
	 		
	 			})
	 				;
	 	
	 		
	 		
	
	
}

 
 public void setQuestions(Question question)
 {
	 if(lobby.getM() == 1 || lobby.getM() == 2)
	 {
		 FriendlyMode(question);
	 }
	 if(lobby.getM() == 3)
	 {
		 TournamentGame(question);
	 }
	 
	
 }
 private void FriendlyMode(Question question)
 {
	
	 questNum++;
	 firstLabel.setForeground(Color.WHITE);
	   secondLabel.setForeground(Color.WHITE);
	   thirdLabel.setForeground(Color.WHITE);
	   fourthLabel.setForeground(Color.WHITE);
	   fifthlabel.setForeground(Color.WHITE);
	   sixthLabel.setForeground(Color.WHITE);
	if(shouldcancel) {
	 updateTimer.cancel();
	 updateTimer = new Timer();
	}else shouldcancel = false;
	
	
	 ArrayList<JLabel> list = new ArrayList<JLabel>();
	 ArrayList<String> qList = new ArrayList<String>();
	 qList.add(question.getCans());
	 qList.add(question.getWans1());
	 qList.add(question.getWans2());
	 qList.add(question.getWans3());
	 qList.add(question.getWans4());
		list.add(secondLabel);
		list.add(thirdLabel);
		list.add(fourthLabel);
		list.add(fifthlabel);
		list.add(sixthLabel);
		firstLabel.setText(question.getQuestion());

	 ArrayList<JLabel> temp = (ArrayList<JLabel>) list.clone();
	 for(JLabel l : list)
	 {

			int rnum = (int) (Math.random()*(temp.size()-0)+0);
			
			temp.get(rnum).setText("-"+qList.get(0));
			
			temp.remove(rnum);
			qList.remove(0);
			
	 }
	
		/*
		secondLabel.setText("-"+question.getCans());
		thirdLabel.setText("-"+question.getWans1());
		fourthLabel.setText("-"+question.getWans2());
		fifthlabel.setText("-"+question.getWans3());
		sixthLabel.setText("-"+question.getWans4());*/
 }
 
 private void TournamentGame(Question question){
	 notSend= false;
	 final Timer timer = new Timer(); 
	 questNum++;
	 firstLabel.setForeground(Color.WHITE);
	   secondLabel.setForeground(Color.WHITE);
	   thirdLabel.setForeground(Color.WHITE);
	   fourthLabel.setForeground(Color.WHITE);
	   fifthlabel.setForeground(Color.WHITE);
	   sixthLabel.setForeground(Color.WHITE);
	if(shouldcancel) {
	 updateTimer.cancel();
	 updateTimer = new Timer();
	}else shouldcancel = false;
	
	
	 ArrayList<JLabel> list = new ArrayList<JLabel>();
	 ArrayList<String> qList = new ArrayList<String>();
	 qList.add(question.getCans());
	 qList.add(question.getWans1());
	 qList.add(question.getWans2());
	 qList.add(question.getWans3());
	 qList.add(question.getWans4());
		list.add(secondLabel);
		list.add(thirdLabel);
		list.add(fourthLabel);
		list.add(fifthlabel);
		list.add(sixthLabel);
		firstLabel.setText(question.getQuestion());

	 ArrayList<JLabel> temp = (ArrayList<JLabel>) list.clone();
	 for(JLabel l : list)
	 {

			int rnum = (int) (Math.random()*(temp.size()-0)+0);
			
			temp.get(rnum).setText("-"+qList.get(0));
			
			temp.remove(rnum);
			qList.remove(0);
			
	 }
	
	 
	 startTime = System.currentTimeMillis();
	 gameTimer= 10000;
	 timer.scheduleAtFixedRate(new TimerTask() {
	      

			@Override
	        public void run() {
	        	
	        
	        		gameTimer = gameTimer-1;
	        		timerLab.setText(gameTimer/1000+"");
	        		startTime= System.currentTimeMillis();
	        	
	        	if(gameTimer<=0 )
	        	{
	        		selected = false;
	        		if(!notSend)
	        		{
	        			System.out.println("Helloooasd");
	        			SendAnswearTournament(new TournamentEvaluation (Qlist.currentQuestionQ, (int)gameTimer, false,Authentication.loggedIn,  questNum));
	        			
	        		}
	        		
	        		
	        		updateTimer.scheduleAtFixedRate(new TimerTask() {
	        	        @Override
	        	        public void run() {
	        	        	shouldcancel = true;
	        	        	questionLab.setText(questNum+"/"+lobby.maxQuestions);
	        	        	pointsLab.setText("Points: "+ points);
	        	        	setQuestions(Qlist.getQuestion());
	        	        	
	        	        }
	        	        }, 1500, 10000);
	     
	        		timer.cancel();
	        	}
	        	
	        	
	        }
	        }, 0, 1);
		/*
		secondLabel.setText("-"+question.getCans());
		thirdLabel.setText("-"+question.getWans1());
		fourthLabel.setText("-"+question.getWans2());
		fifthlabel.setText("-"+question.getWans3());
		sixthLabel.setText("-"+question.getWans4());*/
	 
 }


public void mouseClicked(MouseEvent e) {
	{
		if(lobby.m ==1 || lobby.m ==2)
		{
		Boolean ans;
		JLabel t = (JLabel)e.getSource();
	
		if(t.getText().equals("-"+Qlist.currentQuestionQ.getCans()))
		{
		ans=true;	
			t.setForeground(Color.GREEN);
			points= points+10;
		}else
		{
			ans=false;
			t.setForeground(Color.RED);
		}
		
		SendAnswear(ans,questNum,points);
		
		updateTimer.scheduleAtFixedRate(new TimerTask() {
	        @Override
	        public void run() {
	        	questionLab.setText(questNum+"/"+lobby.maxQuestions);
	        	pointsLab.setText("Points: "+ points);
	        	shouldcancel = true;
	        	setQuestions(Qlist.getQuestion());
	        	
	        }
	        }, 1500, 10000);
		
		}
		 
		if(lobby.m ==3)
		{
			if(!selected)
			{
				selected = true;
		Boolean ans;
		JLabel t = (JLabel)e.getSource();
	
		if(t.getText().equals("-"+Qlist.currentQuestionQ.getCans()))
		{
		ans=true;	
			t.setForeground(Color.GREEN);
			points= points+10;
		}else
		{
			ans=false;
			t.setForeground(Color.RED);
		}
		notSend = true;
		SendAnswearTournament(new TournamentEvaluation (Qlist.currentQuestionQ, (int)gameTimer, ans,Authentication.loggedIn,  questNum));
		
		System.out.println("Send for evaluation");
		
		}
		}
	
		
		
	}
	
}

private void SendAnswear(Boolean ans, int question2, int points2) {
WebResource wb = RESTConnection.getConnection().setResource("Lobby/"+lobby.getLobbyName()+"/getlobbyupdate").getWebResource();
	
	ClientResponse response = wb.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,new SeassionScore(Authentication.loggedIn,points,questNum));
	
	
	
}
private void SendAnswearTournament(TournamentEvaluation t) {
	
	WebResource wb = RESTConnection.getConnection().setResource("Lobby/"+lobby.getLobbyName()+"/"+t.getU().getUsername()+"/submitAns").getWebResource();
	
	ClientResponse response = wb.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,t);
	
}

public void setVisibleLab(Boolean b)
{
	   firstLabel.setVisible(b);;
	   secondLabel.setVisible(b);
	   thirdLabel.setVisible(b);
	   fourthLabel.setVisible(b);
	   fifthlabel.setVisible(b);
	   sixthLabel.setVisible(b);
	}


public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
}