package coursework.GameClientCW;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Updater {

	public int mil;
	OnTickListener listener;
	Timer t;
	public Updater(int mil, OnTickListener l)
	{
		this.mil = mil;
		this.listener = l;
	}
	
	public void start()
	{
		t = new Timer(mil,new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0) {
				if(listener != null)
				{
					listener.onUpdate();
				}
				
			}
	
		});
		t.start();
		
	}
}
