package cs2114.aurem;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

        ListView listView = getListView();
        listView.setOnItemClickListener(new ListClickListener());
    }

    /**
     * // -------------------------------------------------------------------------
    /**
     *  A listener for the list.
     *
     *  @author J. Taylor O'Connor (jto2e)
     *  @version 2012.04.16
     */
    public class ListClickListener implements OnItemClickListener
    {
        /**
         * Method that is called when a list item is clicked.
         * @param parent AdapterView<?> the parent.
         * @param view View the view.
         * @param location int the index of the item.
         * @param id long the id of the item selected.
         */
        public void onItemClick(
            AdapterView<?> parent,
            View view,
            int location,
            long id)
        {
            Intent intent = new Intent();
            intent.putExtra("index", location);
            setResult(RESULT_OK, intent);

        }

    }


}
