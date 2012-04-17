package cs2114.aurem;

import java.io.File;
import android.os.Environment;
import java.io.FileOutputStream;
import java.io.InputStream;
import android.app.Activity;
import java.io.FileInputStream;
import java.util.ArrayList;
import android.util.Log;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Observable;

/**
 * // -------------------------------------------------------------------------
/**
 *  This is the model representing the equalizer.
 *  @author Joseph O'Connor (jto2e)
 *  @version 2012.04.16
 */
public class EqualizerModel extends Observable
{
    private ArrayList<Preset> presets;

    private Scanner inStream;

    private PrintWriter outStream;

    private AuremActivity parent;

    private short[] bandLevels;
    /**
     * This is the constructor for EqualizerModel objects.
     */
    public EqualizerModel(AuremActivity parent)
    {
        presets = new ArrayList<Preset>();
        this.parent = parent;
        bandLevels = new short[5];
    }

    /**
     * Writes all the presets to a text file.
     */
    public void writePresetFile()
    {
        File directory = new File("/sdcard/Aurem/");
        directory.mkdirs();
        File outputFile = new File(directory, "presets.txt");
        FileOutputStream outputStream;
        String output = "";
        try {
            outputStream = new FileOutputStream(outputFile);
            outStream = new PrintWriter(outputStream);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < presets.size(); i++) {
            short index = (short) i;
            Preset preset = presets.get(index);

            output += preset.index() + ",";
            short[] bands = preset.getBands();
            for(int j = 0; j < 5; j++) {
                output += bands[j] + ",";
            }
            output += preset.getName() + "\n";
        }
        outStream.print(output);
        outStream.close();
    }

    /**
     * This reads the preset text file and loads all
     * user presets into the HashMap.
     */
    public void readPresetFile()
    {
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream("/sdcard/Aurem/presets.txt");
            inStream = new Scanner(inputStream);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        inStream.useDelimiter("\n");
        while(inStream.hasNext() ) {
            String line = inStream.next();
            String[] data = line.split(",");
            short index = Short.parseShort(data[0]);
            short[] bands = new short[5];
            Preset preset = new Preset(index, data[6]);
            for (int i = 1; i < 6; i++) {
                bands[i - 1] = Short.parseShort(data[i]);
            }
            preset.setBands(bands);
            presets.add(index, preset);
        }
        inStream.close();
    }

    /**
     * Return a preset object.
     * @param index int the preset index.
     * @return Preset the desired preset.
     */
    public Preset getPreset(short index)
    {
        return presets.get(index);
    }

    /**
     * Sets a specific band to a specific level.
     * @param index the band index.
     * @param bandLevel the band level in milliBels.
     */
    public void setBandLevel(short index, short bandLevel)
    {
        bandLevels[index] = bandLevel;
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the level for a given band
     * @param index the index of the band
     * @return short the band level.
     */
    public short getBandLevel(short index)
    {
        if(index < 0 || index >= bandLevels.length) {
            return 0;
        }
        return bandLevels[index];
    }

    /**
     * This creates a new preset and adds it to the list of
     * presets.
     */
    public void createPreset(String name, short[] bands)
    {
        int index = presets.size();
        Preset preset = new Preset((short)(index), name);
        preset.setBands(bands);
        presets.add(preset);


    }

    /**
     * Returns the HashMap of presets.
     * @return presets the hashMap of presets.
     */
    public ArrayList<Preset> getPresets()
    {
        return presets;
    }

}
