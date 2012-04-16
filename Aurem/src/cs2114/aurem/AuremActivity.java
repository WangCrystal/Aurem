package cs2114.aurem;

import android.app.PendingIntent;
import android.app.Notification;
import android.app.NotificationManager;
import android.view.View;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.Intent;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;

/**
 * // -------------------------------------------------------------------------
/**
 *  This is the Activity for the Aurem Equalizer.
 *
 *  @author Joseph Taylor O'Connor (jto2e)
 *  @version 2012.04.10
 */
public class AuremActivity extends Activity {


    private TextView debug;

    private Intent intent;
    private EqualizerService eqService;

    private NotificationManager notificationManager;

    private EqualizerModel model;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        debug = (TextView) findViewById(R.id.debug);

        intent = new Intent(this, EqualizerService.class);
        startService(intent);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        //All this stuff has to do with setting up a persisent
        //notification icon to let the user return to the app.

        PendingIntent contentIntent =
            PendingIntent.getActivity(this, 1, new Intent(this,
                AuremActivity.class), 0);
        notificationManager = (NotificationManager)
            getSystemService(Context.NOTIFICATION_SERVICE);
        int icon = R.drawable.ic_launcher;
        Notification notification = new Notification(icon,
            "Aurem EQ", System.currentTimeMillis());
        Context context = getApplicationContext();
        CharSequence contentTitle = "Aurem EQ";
        CharSequence contentText = "Tap to return to Aurem EQ";
        notification.setLatestEventInfo(context, contentTitle,
            contentText, contentIntent);
        notificationManager.notify(1, notification);

        model = new EqualizerModel();
        model.readPresetFile();
    }

    /**
     * This is called when the test button is clicked.
     * @param view The view.
     */
    public void testButtonClicked(View view) {

        debug.setText(eqService.testPrintout());
    }

    /**
     * Called when the Activity is exited.
     */
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className,
            IBinder service) {
            eqService =
                ((EqualizerService.ServiceBinder) service).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            eqService = null;
        }
    };
}