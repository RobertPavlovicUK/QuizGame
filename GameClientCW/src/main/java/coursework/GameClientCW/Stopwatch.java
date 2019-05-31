package coursework.GameClientCW;

import javax.swing.JLabel;

public class Stopwatch {
	
	
	int  setSeconds;
	int currentSec;
	Boolean shouldRun= true;
	private TimersListnner tt;
	int mil;
	int sec;
	
	public Stopwatch(int setSeconds)
	{
		this.setSeconds=setSeconds;
		this.setSeconds=setSeconds*1000;
	}
	
	
	public void start(final TimersListnner tt)
	{
		Thread t = new Thread(new Runnable()
				{

			long currentTime = System.currentTimeMillis();
					public void run() {
					
					while(true)
					{
						if(shouldRun)
						{
						if(System.currentTimeMillis()-currentTime>= 1 && setSeconds>0 )
						{
							setSeconds = setSeconds-1;
								System.out.println(""+setSeconds);
								currentTime= System.currentTimeMillis();
								
							
						}
						if(setSeconds == 0)
						{
							tt.OnTimeOut();
							break;
						}
						}
						
					}
						
					}
			
				});
		t.start();
	}
	public void update(JLabel l)
	{
			l.setText(setSeconds/1000+"");
	}
	
	public int stop()
	{
		shouldRun= false;
		return setSeconds;
	}

}