package cs2114.aurem;

/**
 * // -------------------------------------------------------------------------
/**
 *  This is a class to model an Equalizer preset.
 *
 *  @author Taylor
 *  @version Apr 16, 2012
 */
public class Preset
{
    private short index;

    private short[] bandLevels;

    private String name;

    /**
     * This is the constructor for Preset objects.
     * @param index short the index.
     * @param name String the name.
     */
    public Preset(short index, String name)
    {
        this.index = index;
        this.name = name;
        bandLevels = new short[5];
    }

    /**
     * Set all the band levels for a preset.
     * @param bands short[] the band levels in milliBels.
     */
    public void setBands(short[] bands)
    {
        for (int i = 0 ; i < 5; i++) {
            bandLevels[i] = bands[i];
        }
    }

    /**
     *  Returns the index of the preset.
     *  @return short the index.
     */
    public short index()
    {
        return index;
    }

    /**
     * Returns the levels of the bands of the preset.
     * @return bandLevels short[] an array of the band levels.
     */
    public short[] getBands()
    {
        return bandLevels;
    }

    /**
     * Returns the name of the preset.
     * @return name String the name of the preset.
     */
    public String getName()
    {
        return name;
    }


}
