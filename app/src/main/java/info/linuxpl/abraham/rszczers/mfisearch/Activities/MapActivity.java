package info.linuxpl.abraham.rszczers.mfisearch.Activities;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.polites.android.GestureImageView;

import info.linuxpl.abraham.rszczers.mfisearch.Features.Building;
import info.linuxpl.abraham.rszczers.mfisearch.R;

import static info.linuxpl.abraham.rszczers.mfisearch.R.id.tabHost;

public class MapActivity extends ActionBarActivity {
    LayoutParams params;
    GestureImageView level;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_map);

        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

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

        Spinner lev= (Spinner) findViewById(R.id.choose_level);
        final String[] levels= getResources().getStringArray(R.array.levels);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, levels);
        lev.setAdapter(adapter);

        lev.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index= parent.getSelectedItemPosition(); // index zwraca wybraną pozycje
                ViewGroup layout = (ViewGroup) findViewById(R.id.plan_viewer);
                level = new GestureImageView(context);
                switch(index) {
                    case 0:

                        break;
                    case 1:
                        layout.removeAllViewsInLayout();
                        level.setImageResource(R.drawable.level1);
                        level.setLayoutParams(params);
                        level.setMinScale(0.5f);
                        level.setMaxScale(2.0f);
                        layout.addView(level);
                        break;
                    case 2:
                        Building tmp = new Building(context);
                        layout.removeAllViewsInLayout();
                        level.setImageBitmap(tmp.find("F2", context));
                        //level.setImageResource(R.drawable.level2);
                        level.setLayoutParams(params);
                        level.setMinScale(0.5f);
                        level.setMaxScale(2.0f);
                        layout.addView(level);
                        break;
                }


                Toast.makeText(getBaseContext(), "Piętro "+levels[index], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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