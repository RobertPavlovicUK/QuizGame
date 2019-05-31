package coursework.GameClientCW;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Future;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import Authentication.CWResources;
import Authentication.Users;
import GameResources.GameLobby;
import GameResources.GameMode;
import GameResources.Question;

import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;


public class MenuWindow extends JFrame implements ActionListener {
ArrayList<Users> oldLobby= new ArrayList<Users>();;
private HashMap<GameLobby,String> lobbiesmap = new HashMap<GameLobby,String>();
private GameLobby createLobby;
private JButton Button;
DefaultListModel<GameLobby> lobby;
DefaultListModel<Users> onlinePlayers;
Updater u;
ArrayList<Users>online = new ArrayList<Users>();
JTextField LobbyName;
JTextField MaxPlayers;
JTextField QuestionNum;
JComboBox<String> comboBox;
JButton btnNewButton ;
JButton praciteButton;
public String lobbyName;
public GameLobby selectedLobby;
JButton btnNewButton_1;
public static JFrame  mainWindow;
JFrame settings ;

 public MenuWindow() {
  initialize();
 }

 
  
 private void initialize() {
 this.setVisible(true);
mainWindow = this;
 lobby =new DefaultListModel<GameLobby>();
 onlinePlayers = new DefaultListModel<Users>();

  this.setBounds(300, 300, 715, 335);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.getContentPane().setLayout(null);
 
  final JList list = new JList(lobby);
  list.setBounds(10, 69, 467, 156);
  
  lobby.addListDataListener(new ListDataListener()
		  {

			public void contentsChanged(ListDataEvent arg0) {
			System.out.println("hello");
				
			}

			public void intervalAdded(ListDataEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void intervalRemoved(ListDataEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	  
		  });
  list.addListSelectionListener(new ListSelectionListener() {

	public void valueChanged(ListSelectionEvent e) {
		
		GameLobby g = (GameLobby)list.getSelectedValue();
		
	
	selectedLobby = g;
		
	}
	  
  });
  this.getContentPane().add(list);
  JScrollPane listScroller = new JScrollPane(list);
  this.getContentPane().add(listScroller);
  
  listScroller.setBounds(10, 69, 900, 400);

  JList list_1 = new JList(onlinePlayers);
  list_1.setBounds(494, 69, 195, 216);
 this.getContentPane().add(list_1);
  JScrollPane listScrolle1r = new JScrollPane(list_1);
		 this.getContentPane().add(listScrolle1r);
		  listScrolle1r.setBounds(950, 69, 195, 400);
		  
  JComboBox<String> comboBox = new JComboBox();
  comboBox.setBounds(20, 480, 200, 20);
  this.getContentPane().add(comboBox);
  comboBox.addItem("Filter-None");
  comboBox.addItem("Practice game");
	comboBox.addItem("Friendly game");
	comboBox.addItem("Tournament game");
	
   btnNewButton = new JButton("Create Lobby...");
  btnNewButton.addActionListener(this);
  btnNewButton.setBounds(388, 480, 150, 23);
  this.getContentPane().add(btnNewButton);
  
 btnNewButton_1 = new JButton("Join");
  btnNewButton_1.setBounds(272, 480, 89, 23);
  this.getContentPane().add(btnNewButton_1);
  btnNewButton_1.addActionListener(this);
  
  praciteButton = new JButton("Practice Game");
  praciteButton.setBounds(604, 480, 89, 23);
  this.getContentPane().add(praciteButton);
  praciteButton.addActionListener(this);
  
  JLabel lblNewLabel = new JLabel("Lobby Name");
  lblNewLabel.setBounds(10, 22, 89, 14);
  this.getContentPane().add(lblNewLabel);
  
  JLabel lblNewLabel_1 = new JLabel("Game type");
  lblNewLabel_1.setBounds(280, 22, 89, 14);
  this.getContentPane().add(lblNewLabel_1);
  
  JLabel onlinePlayersLabel = new JLabel("Online Players");
  onlinePlayersLabel.setBounds(1000, 22, 89, 14);
  this.getContentPane().add(onlinePlayersLabel);
  
  this.addWindowListener(new WindowListener()
	{

		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowClosed(WindowEvent arg0) {
			System.out.println("HELLO");
			Authentication.logOut();
			
		}

		public void windowClosing(WindowEvent arg0) {
			System.out.println("HELLO");
			Authentication.logOut();
			
		}

		public void windowDeactivated(WindowEvent arg0) {
			System.out.println("HELLO");
			
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
  JLabel playerLabel = new JLabel("Players");
  playerLabel.setBounds(570, 22, 74, 14);
  this.getContentPane().add(playerLabel);
  
  new Updater(1000,new OnTickListener()
		  {

			public void onUpdate() {
				ReceiveOnlinePlayers();
				ReceiveLobby();
				
			}
	  
	  
		  }
  ).start();;
  
 }
 public void ReceiveLobby()
 {
	 Client client = ClientBuilder.newBuilder().build();

	 	
	 
		
	 		
	 			
	 	Future<ArrayList<GameLobby>> target = client.target("http://"+CWResources.IpAddress+":8080/ServerCW/Lobby/GetAll").request(MediaType.APPLICATION_JSON).async().get(new InvocationCallback<ArrayList<GameLobby>>()
	 			
	 			{
	 		
	 		
						public void completed(ArrayList<GameLobby> arg0) {
				
						
						for(GameLobby l : arg0)
						{
							if(!lobby.contains(l))
							{
								lobby.addElement(l);
							}
							
							for(int i =0; i < lobby.getSize();i++)
							{
								if(lobby.elementAt(i).getLobbyName().equals(l.getLobbyName()) )
								{
									if(lobby.elementAt(i).getPlayers().size() != l.getPlayers().size())
									{
									
									lobby.removeElementAt(i);
									lobby.add(i, l);
									}
								}
								else
								{
									continue;
								}
							}
						}
								
								
							
								
							
						}

						public void failed(Throwable arg0) {
						System.out.println("failed" +arg0.getMessage());
							
						}
	 		
	 			})
	 				;
	 	
	 		
	 		
	 	
	 }
 
 public void ReceiveOnlinePlayers()
 {
	 Client client = ClientBuilder.newBuilder().build();

 	
 	Boolean bol =true;
 
 		
 		
 			
 	Future<ArrayList<Users>> target = client.target("http://"+CWResources.IpAddress+":8080/ServerCW/Authentication/OnlineUsers").request(MediaType.APPLICATION_JSON).async().get(new InvocationCallback<ArrayList<Users>>()
 			
 			{

 		
					public void completed(ArrayList<Users> arg0) {
						
						
						onlinePlayers.clear();
						
							for(Users us : arg0)
							{
								if(!onlinePlayers.contains(us))
								{
									
									
									onlinePlayers.addElement(us);
								}
								
								
								
							}
						
						
					}

					public void failed(Throwable arg0) {
						// TODO Auto-generated method stub
						
					}
 		
 			})
 				;
 	
 		
 		
 	
 }
 
 public void CreateLobby(GameLobby gm)
 {
	 WebResource web = RESTConnection.getConnection().setResource("Lobby/CreateLobby").getWebResource();
	 ClientResponse response = web.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,gm );
	
 }
 
 
 private void CreateLobby()
 {
	  settings = new JFrame();
	settings.setVisible(true);
	settings.setBounds(300, 300, 415, 335);
	settings.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	settings.setLayout(null);
	JLabel LobbyLabel = new JLabel("Lobby name");

	settings.add(LobbyLabel);
	LobbyLabel.setBounds(20, 20, 180, 20);
	 LobbyName = new JTextField();
	 LobbyName.setText(Authentication.loggedIn.username+"slobby");
	LobbyName.setBounds(120, 20, 200, 20);
	settings.add(LobbyName);
	JLabel maxPlayers = new JLabel("Max Players");
	JLabel maxPlayersLabel= new JLabel("/10");
	maxPlayers.setBounds(20, 80, 180, 20);
	settings.add(maxPlayers);
	maxPlayersLabel.setBounds(170, 80, 20, 20);
	settings.add(maxPlayersLabel);
	 MaxPlayers = new JTextField();
	MaxPlayers.setBounds(120, 80, 40, 20);
	settings.add(MaxPlayers);
	
	
	JLabel maxQuestion = new JLabel("Max Questions");
	JLabel maxQuestionsLabel= new JLabel("/50");
	maxQuestion.setBounds(200, 80, 180, 20);
	settings.add(maxQuestion);
	maxQuestionsLabel.setBounds(350, 80, 20, 20);
	settings.add(maxQuestionsLabel);
	
	 QuestionNum = new JTextField();
	QuestionNum.setBounds(300, 80, 40, 20);
	settings.add(QuestionNum);
	
	LobbyName.setEditable(false);
	 comboBox = new JComboBox<String>();
	 comboBox.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent arg0) {
	
				if(comboBox.getSelectedItem().toString().equals("Practice game"))
				{
					MaxPlayers.setText(1+"");
					
				}
				
			
		}});
	JLabel ComboLab= new JLabel("GameMode");
	ComboLab.setBounds(20, 160, 180, 20);
	settings.add(ComboLab);

	comboBox.addItem("Friendly game");
	comboBox.addItem("Tournament game");
	comboBox.setBounds(120, 160, 200, 20);
	settings.add(comboBox);
	
	Button = new JButton("Create Lobby");
	Button.setBounds(100, 240, 200, 20);
	settings.add(Button);
	Button.addActionListener(this);
	
	
 }



public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button)
		{	int mode = 0;
			GameMode gameMode = null;
			if(comboBox.getSelectedItem().equals("Practice game"))
			{  mode = 1;
				gameMode = GameMode.FRIENDLY_MODE;
				
			}
			if(comboBox.getSelectedItem().equals("Friendly game"))
			{
				 mode=2;
				gameMode = GameMode.PRACTICE_MODE;
			}
			if(comboBox.getSelectedItem().equals("Tournament game"))
			{	mode =3;
				gameMode = GameMode.TOURNAMENT_MODE;
			}
			
			lobbyName = LobbyName.getText();
			createLobby= new GameLobby(Authentication.loggedIn, lobbyName,Integer.parseInt(QuestionNum.getText()), Integer.parseInt(MaxPlayers.getText()),mode);
			
			CreateLobby(createLobby);
			selectedLobby = createLobby;
			 GameInterface window = new GameInterface(getLobby());
			 
			 window.setVisible(true);
			 settings.setVisible(false);
			System.out.println("sad"+mode);
			
		}
		
		if(e.getSource() == praciteButton)
		{
		
			createLobby= new GameLobby(Authentication.loggedIn, Authentication.loggedIn.getUsername()+"Practicelobby",49,1,1);
			
			CreateLobby(createLobby);
			selectedLobby = createLobby;
			 GameInterface window = new GameInterface(getLobby());
			 window.setVisible(true);
			
		}
		if(e.getSource() == btnNewButton)
			
		{
			CreateLobby();
		}
		if(e.getSource() == btnNewButton_1 )
		{
			
				JoinLobby();
			
		}
	
}



private void JoinLobby()  {

	WebResource wb = RESTConnection.getConnection().setResource("Lobby/"+selectedLobby.getLobbyName()+"/AddPlayer").getWebResource();
	
	ClientResponse response = wb.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,Authentication.loggedIn);
	
	if(response.getStatus() == 404)
	{
		
	}else
	{
		
		
		 GameInterface window = new GameInterface(getLobby());
	
    window.setVisible(true);
    this.setVisible(false);;
	}
	
	
	
	
	
	
}

public GameLobby getLobby()
{

	WebResource wb = RESTConnection.getConnection().setResource("Lobby/"+selectedLobby.getLobbyName()+"/getLobby").getWebResource();
	
	ClientResponse response = wb.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
	
	GameLobby result = response.getEntity(GameLobby.class);
	return result;
	}

}