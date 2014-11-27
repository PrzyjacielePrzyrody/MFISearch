package info.linuxpl.abraham.rszczers.mfisearch;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import static info.linuxpl.abraham.rszczers.mfisearch.R.id.tabHost;


public class MapActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        TabHost tabhost= (TabHost) findViewById(tabHost);

        tabhost.setup();
        TabHost.TabSpec find=tabhost.newTabSpec("Find");
        find.setContent(R.id.tab1);
        find.setIndicator(getString(R.string.search_room));
        tabhost.addTab(find);

        find=tabhost.newTabSpec("View");
        find.setContent(R.id.tab2);
        find.setIndicator(getString(R.string.view_map));
        tabhost.addTab(find);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}