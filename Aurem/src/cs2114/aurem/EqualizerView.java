package cs2114.aurem;

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
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

    private int height;
    private int width;

    private Rect onOffSwitch;
    private Rect savePreset;
    private Rect loadPreset;

    private Bitmap onOffImage;
    private Bitmap savePresetImage;
    private Bitmap loadPresetImage;


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
        savePresetImage = BitmapFactory.decodeResource(
            getResources(), R.drawable.save);
        if (model == null)
        {
            return;
        }
        onOffSwitch = new Rect(canvas.getWidth() / 6 - 50,
            canvas.getHeight() / 16,
            canvas.getWidth() / 6 + 50,
            canvas.getHeight() / 16 + 100);

        savePreset = new Rect(canvas.getWidth() * 3 / 6 - 50,
            canvas.getHeight() / 16,
            canvas.getWidth() * 3 / 6 + 50,
            canvas.getHeight() / 16 + 100);

        loadPreset = new Rect(canvas.getWidth() * 5 / 6 - 50,
            canvas.getHeight() / 16,
            canvas.getWidth() * 5 / 6 + 50,
            canvas.getHeight() / 16 + 100);

        Paint paint = new Paint();
        paint.setARGB(255,255,255,255);
        canvas.drawRect(onOffSwitch, paint);
        canvas.drawRect(loadPreset, paint);
        canvas.drawBitmap(savePresetImage, savePreset, savePreset, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
           //.
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
