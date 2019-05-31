package coursework.GameClientCW;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Database.DatabaseRes;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LogInPage extends JFrame implements ActionListener{

 
 private JTextField passwordField;
 private String username;
 private String password;
 JButton btnNewButton;
 JButton btnNewButton_1; 
 private Authentication auth;
 JTextField textArea;
 JLabel infoLab; 
public LogInPage()
{
	initialize();
	new Updater(500,new OnTickListener()
			{

				public void onUpdate() {
				
					
				}
		
			}).start();
	}
 
 private void initialize() {
	 
  this.setVisible(true);
 
  this.setBounds(100, 100, 450, 300);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.getContentPane().setLayout(null);
  
  JPanel panel = new JPanel();
  panel.setBounds(0, 0, 1, 261);
  this.getContentPane().add(panel);
  panel.setLayout(null);
  
  textArea = new JTextField();
  textArea.setBounds(220, 108, 144, 22);
  this.getContentPane().add(textArea);
  textArea.setText("admin");
  passwordField = new JPasswordField();
  passwordField.setBounds(220, 156, 144, 22);
  this.getContentPane().add(passwordField);
  passwordField.setText("admin");
  
  JLabel lblNewLabel = new JLabel("Username");
  lblNewLabel.setBounds(55, 108, 144, 22);
  this.getContentPane().add(lblNewLabel);
  
   infoLab = new JLabel("");
   infoLab.setBounds(180, 20, 144, 22);
  this.getContentPane().add(infoLab);
  
  JLabel lblNewLabe2 = new JLabel("Password");
  lblNewLabe2.setBounds(55, 156, 144, 22);
  this.getContentPane().add(lblNewLabe2);
  
  
   btnNewButton = new JButton("Sing in");
  btnNewButton.setBounds(200, 209, 89, 23);
  this.getContentPane().add(btnNewButton);
  btnNewButton.addActionListener(this);
  
   btnNewButton_1 = new JButton("Sign up");
  btnNewButton_1.setBounds(312, 209, 89, 23);
  this.getContentPane().add(btnNewButton_1);
  btnNewButton_1.addActionListener(this);
 }

public void actionPerformed(ActionEvent e) {
	System.out.println("gee");
	if(e.getSource() ==  btnNewButton)
	{
		
	 LogIn();

	}
	if(e.getSource() == btnNewButton_1)
	{
		Register();
	}

}


private void Register() {
	username = textArea.getText();
	password = passwordField.getText();
	 auth= new Authentication(username,password);
	
 SwingUtilities.invokeLater(new Runnable()
		 {
	
	public void run() {
		 
	
		 auth.Register(new OnLoginListener() {

			public void onSuccess(DatabaseRes res) {
			
				if(res == Database.DatabaseRes.DATABASE_ACCOUNT_REGISTERED)
				{
					
					infoLab.setText("Register Succesful");
					
				}
				
			}
			

			public void onFail(DatabaseRes res) {
			
		
			if(DatabaseRes.DATABASE_ACCOUNT_ALREADY_EXISTS== res)
			{
				infoLab.setText("Account already exists");
			}
			
		
				
			}
			  
		  });
			
		
	}
		 }); 
	
}

private void LogIn()
{
	username = textArea.getText();
	password = passwordField.getText();
	 auth= new Authentication(username,password);
	
 SwingUtilities.invokeLater(new Runnable()
		 {
	
	public void run() {
		 
		 auth.LogIn(new OnLoginListener() {

			public void onSuccess(DatabaseRes res) {
			
				if(res == Database.DatabaseRes.DATABASE_ACCOUNT_LOGIN_SUCCESSFUL)
				{
					
					infoLab.setText("Login Succesful");
					LogInPage.this.setVisible(false);
					EventQueue.invokeLater(new Runnable() {
			    		   public void run() {
			    		    try {
			    		    	auth.joinOnline();
			    		    	 new MenuWindow().setSize(new Dimension(1200,600));;
			    		     
			    		    } catch (Exception e) {
			    		     e.printStackTrace();
			    		    }
			    		   }
			    		  });
				}
				
			}

			public void onFail(DatabaseRes res) {
			
		
			if(DatabaseRes.DATABASE_ACCOUNT_NOT_FOUND== res)
			{
				infoLab.setText("Account not found");
			}
			if(DatabaseRes.DATABASE_ACCOUNT_PASSOWRD_INCORECT== res)
			{
				infoLab.setText("Incorrect password");
			}
		
				
			}
			  
		  });
		
	}
		 }); 
 
	}
public void mouseClicked(MouseEvent arg0) {
	System.out.println("click");
	
}

public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


}