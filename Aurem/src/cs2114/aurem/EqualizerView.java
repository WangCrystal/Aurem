package cs2114.aurem;

import android.graphics.RectF;
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

    private RectF onOffSwitch;
    private RectF savePreset;
    private RectF loadPreset;

    private Bitmap onOffImage;
    private Bitmap savePresetImage;
    private Bitmap loadPresetImage;

    AuremActivity activity;


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
        width = canvas.getWidth();
        height = canvas.getHeight();


        if (model == null)
        {
            return;
        }


        savePresetImage = BitmapFactory.decodeResource(
            getResources(), R.drawable.save);
        savePresetImage = Bitmap.createScaledBitmap(savePresetImage,
            (int) (savePresetImage.getWidth() * 1.25),
            (int) (savePresetImage.getHeight() * 1.25), true);

        loadPresetImage = BitmapFactory.decodeResource(getResources(),
            R.drawable.open);
        loadPresetImage = Bitmap.createScaledBitmap(loadPresetImage,
            (int) (loadPresetImage.getWidth() * 1.25),
            (int) (loadPresetImage.getHeight() * 1.25), true);

        Paint paint = new Paint();


        float left = (width * 8 / 10) - (savePresetImage.getWidth() / 2) ;
        float top = height / 10;
        canvas.drawBitmap(savePresetImage, left , top, null);
        left = (width * 5 / 10) - (loadPresetImage.getWidth() / 2);
        canvas.drawBitmap(loadPresetImage, left, top, null);



        onOffSwitch = new RectF(
            (width * 2 / 10) - (savePresetImage.getWidth() / 2),
            height / 10,
            (width * 2 / 10) + (savePresetImage.getWidth() / 2),
            (height / 10) + savePresetImage.getHeight());

        savePreset = new RectF(
            (width * 8 / 10) - (savePresetImage.getWidth() / 2),
            height / 10,
            (width * 8 / 10) + (savePresetImage.getWidth() / 2),
            (height / 10) + savePresetImage.getHeight());

        loadPreset = new RectF(
            (width * 5 / 10) - (loadPresetImage.getWidth() / 2),
            height / 10,
            (width * 5 / 10) + (loadPresetImage.getWidth() / 2),
            (height / 10) + loadPresetImage.getHeight());

        paint.setARGB(255,255,255,255);
        canvas.drawRect(onOffSwitch, paint);
        paint.setARGB(0,0,0,0);
        canvas.drawRect(loadPreset, paint);
        canvas.drawRect(savePreset, paint);

    }

    /**
     *
     */
    public void setActivity(AuremActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        if(e.getAction() == MotionEvent.ACTION_UP) {
            if (loadPreset.contains(e.getX(), e.getY())) {
                activity.loadPresetClicked(this);
            }
            else if (savePreset.contains(e.getX(), e.getY())) {
                activity.savePresetClicked(this);
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
