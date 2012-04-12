package cs2114.aurem;

import android.os.Binder;
import android.content.Intent;
import android.os.IBinder;
import android.app.Service;

/**
 * // -------------------------------------------------------------------------
/**
 *  This is the service that runs the EQ effect in the background.
 *
 *  @author Joseph O'Connor (jto2e)
 *  @version 2012.04.11
 */
public class EqualizerService extends Service
{
    private final ServiceBinder binder = new ServiceBinder();

    /**
     * This gets called first when the service is created.
     */
    @Override
    public void onCreate()
    {
        super.onCreate();
        //. THIS GETS CALLED FIRST.
    }
    /**
     * This gets called after onCreate() and only gets called
     * when another component calls startService(Intent);
     * @param intent the Intent
     * @param flags int argument 1
     * @param startId int argument 2
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY; //This makes the system restart our
                             //process if it gets interrupted.
    }

    /**
     * This is called when another component calls bindService()
     * @return IBinder the binder used to communicate with other
     * components.
     */
    @Override
    public IBinder onBind(Intent arg0)
    {
       return binder;
    }

    /**
     * This gets called when the service is stopped.
     */
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        //.
    }

    /**
     * This is only a test to make sure that the activity
     * has access to the methods of the service.
     * @return String the printout
     */
    public String testPrintout()
    {
        return "THIS IS A TEST!!!";
    }

    /**
     * T// ----------------------------------------------------------------
    /**
     *  This inner binder class allows the activity to interact with the
     *  service.
     *
     *  @author J. Taylor O'Connor
     *  @version 2012.04.11
     */
    public class ServiceBinder extends Binder {
        /**
         * This returns an instance of this service for whoever
         * called bindservice().
         * @return EqualizerService this
         */
        EqualizerService getService() {
            return EqualizerService.this;
        }
    }



}
