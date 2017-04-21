package rx2network.toche.com.reactivenetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by admin on 3/14/2017 AD.
 */

public class AutoStartup extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        /*
            //To start new intent when phone starts up
            Intent i = new Intent(context, LoginActivity.class);
            // To put activity on the top of the stack since activity is launched from context outside activity
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // EDITED
            i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            context.startActivity(i);
*/
    }
}
