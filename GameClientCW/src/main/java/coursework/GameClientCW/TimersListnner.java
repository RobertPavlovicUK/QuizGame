package coursework.GameClientCW;

import javax.swing.JLabel;

public interface TimersListnner {

	void OnTimeOut();
	void OnPause();
	void Tick(int i);
}
