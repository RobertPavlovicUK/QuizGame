package coursework.GameClientCW;

import Database.DatabaseRes;

public interface OnLoginListener {
 void onSuccess(DatabaseRes res);
 void onFail(DatabaseRes res);
}
