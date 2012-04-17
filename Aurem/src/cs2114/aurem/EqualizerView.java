package cs2114.aurem;

import android.view.MotionEvent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Canvas;
import java.util.Observable;
import java.util.Observer;
import android.content.Context;
import android.util.AttributeSet;

public class EqualizerView extends android.view.View
{
    private EqualizerModel model;

    private Rect[] tracks;
    private Rect[] sliders;


    /**
     * Constructor for the EqualizerView class.
     * @param context Context the context
     * @param attrs AttributeSet the attributes.
     */
    public EqualizerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     * Allows the view to get an instance of the model.
     */
    public void setModel(EqualizerModel model)
    {
        this.model = model;
        model.addObserver(new EqualizerObserver());
    }

    /**
     * This is what draws the view.
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        if (model == null)
        {
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        Paint tempPaint = new Paint();
        tempPaint.setARGB(255, 0, 166, 255);

        tracks = new Rect[5];
        sliders = new Rect[5];
        for(int i = 0; i < 5; i ++) {
            tracks[i] = new Rect(
                (width * (i + 2) / 7) - 8,
                height * 1 / 8,
                (width * (i + 2) / 7) + 8,
                (height * 6 / 8));
            canvas.drawRect(tracks[i], tempPaint);
        }
        float[] bandLevels = new float[5];
        Paint sliderPaint = new Paint();
        sliderPaint.setARGB(255, 255, 255, 255);
        for(short i = 0; i < 5; i++) {
            bandLevels[i] = (float) model.getBandLevel(i) / 1500;
            sliders[i] = new Rect(
                (width * (i + 2) / 7) - 20,
                (int) (tracks[i].centerY() - bandLevels[i] *
                tracks[i].height() - 25),
                (width * (i + 2) / 7) + 20,
                (int) (tracks[i].centerY() - bandLevels[i] *
                tracks[i].height() + 25));
            canvas.drawRect(sliders[i], sliderPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            for(int i = 0; i < 5; i++) {
                if(e.getX() < tracks[i].centerX() + 10 &&
                    e.getX() > tracks[i].centerX() - 10) {
                    short bandLevel = (short)((short) (tracks[i].height() / 2
                        - (sliders[i].centerX() /
                        (tracks[i].height() / 2))) * 1500);
                    model.setBandLevel((short) i, bandLevel);

                }
            }
        }
        return true;
    }


    /**
     * // -------------------------------------------------------------------------
    /**
     *  Write a one-sentence summary of your class here.
     *  Follow it with additional details about its purpose, what abstraction
     *  it represents, and how to use it.
     *
     *  @author Joseph O'Connor (jto2e)
     *  @version 04.17.2012
     */
    public class EqualizerObserver implements Observer
    {
        /**
         * This is called whenever something changes in the
         * Observable EqualizerModel object.
         * @param observable Observable the observable object.
         * @param data Object the data.
         */
        public void update(Observable observable, Object data)
        {
            invalidate();

        }

    }

}
