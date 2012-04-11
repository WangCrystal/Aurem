package cs2114.aurem;

import android.widget.TextView;
import android.media.audiofx.Equalizer;
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

    private Equalizer eq;

    private TextView debug;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        eq = new Equalizer(1, 0);
        eq.usePreset((short) 9);

        debug = (TextView) findViewById(R.id.debug);
        String output = eq.getProperties().toString() + "\n";
        for(short i = 0; i < 5; i ++) {
            output += eq.getBandLevel(i) + " ";
        }
        output += "\n";
        short band = 1;
        short level = 300;
        eq.setBandLevel(band, level);
        for(short i = 0; i < 5; i ++) {
            output += eq.getBandLevel(i) + " ";
        }
        output += "\n\n";
        for(short i = 0; i < 10; i ++) {
            output += i + "  " + eq.getPresetName(i) + "\n";
        }
        short[] range = eq.getBandLevelRange();
        output += "\n" +"Level Range:  " + range[0] + " to " + range[1] + "\n";

        output += "\nThe Center Frequencies of each band\n";
        for(short i = 0; i < 5; i++) {
            output += i + "  " + eq.getCenterFreq(i) + "\n";
        }
        debug.setText(output);
    }
}