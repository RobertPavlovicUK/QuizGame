package coursework.GameClientCW;

import Database.DatabaseRes;

public interface OnRegisterListener {
	void onSuccess(DatabaseRes res);
	 void onFail(DatabaseRes res);
}
