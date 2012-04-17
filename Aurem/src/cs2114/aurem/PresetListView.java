package cs2114.aurem;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.app.ListActivity;

/**
 * // -------------------------------------------------------------------------
/**
 *  This is the ListView containing all the Equalizer presets
 *  that the user can select to load.
 *
 *  @author Taylor
 *  @version Apr 16, 2012
 */
public class PresetListView extends ListActivity
{
    private String[] names;

    /**
     * This is called when the Activity is stared.
     * @param savedInstanceState Bundle the saved instance state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        names = getIntent().getStringArrayExtra("names");
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item
            , names));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent();
        intent.putExtra("index", position);
        setResult(RESULT_OK, intent);
        finish();
    }
}

