package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.polites.android.GestureImageView;

import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseAdapter;
import info.linuxpl.abraham.rszczers.mfisearch.R;

import static info.linuxpl.abraham.rszczers.mfisearch.R.id.tabHost;

public class MapActivity extends ActionBarActivity {
    DatabaseAdapter dbAdapter;
    LayoutParams params;
    GestureImageView level;
    Context context;

    TabHost tabhost;

    Button searchButton;
    EditText searchField;
    Spinner searchPick;

    ViewGroup layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_map);

        dbAdapter = new DatabaseAdapter(this);

        tabhost = (TabHost) findViewById(tabHost);

        tabhost.setup();
        TabHost.TabSpec find = tabhost.newTabSpec("Find");
        find.setContent(R.id.tab1);
        find.setIndicator(getString(R.string.search_room));
        tabhost.addTab(find);

        find = tabhost.newTabSpec("View");
        find.setContent(R.id.tab2);
        find.setIndicator(getString(R.string.view_map));

        tabhost.addTab(find);

        final Spinner lev = (Spinner) findViewById(R.id.choose_level);
        final String[] levels = getResources().getStringArray(R.array.levels);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, levels);

        lev.setAdapter(adapter);

        // Obsługa pola wyszukiwania
        searchPick = (Spinner) findViewById(R.id.search_spinner_on_map);
        searchField = (EditText) findViewById(R.id.search_on_map_edittext);
        searchButton = (Button) findViewById(R.id.search_on_map_button);
        layout = (ViewGroup)findViewById(R.id.plan_viewer);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent found = new Intent(context, FindOnMapActivity.class);
                String tmp = searchField.getText().toString();
                if(tmp.equals("")) {
                    tmp = ((Cursor) searchPick.getSelectedItem()).getString(1);
                }
                found.putExtra("roomID", tmp);
                startActivity(found);
            }
        });

        DatabaseAdapter roomAdapter = new DatabaseAdapter(getApplicationContext());
        SimpleCursorAdapter sca = new SimpleCursorAdapter(MapActivity.this, android.R.layout.simple_spinner_item,
                roomAdapter.getRoomNames(),
                new String[] {"name"},
                new int[] {android.R.id.text1}, 0);
        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchPick.setAdapter(sca);

        lev.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index= parent.getSelectedItemPosition(); // index zwraca wybraną pozycje
                layout = (ViewGroup) findViewById(R.id.plan_viewer);
                params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                int childCount = 0;
                switch (index) {
                    case 0:
                        level = new GestureImageView(context);
                        childCount = layout.getChildCount();
                        if(childCount>0) {
                            layout.removeAllViewsInLayout();
                        }
                        level.setImageResource(R.drawable.level0);
                        level.setLayoutParams(params);
                        level.setMinScale(0.5f);
                        level.setMaxScale(3.0f);
                        layout.addView(level);
                        break;
                    case 1:
                        level = new GestureImageView(context);
                        childCount = layout.getChildCount();
                        if(childCount>0) {
                            layout.removeAllViewsInLayout();
                        }
                        level.setImageResource(R.drawable.level1);
                        level.setLayoutParams(params);
                        level.setMinScale(0.5f);
                        level.setMaxScale(3.0f);
                        layout.addView(level);
                        break;
                    case 2:
                        level = new GestureImageView(context);
                        childCount = layout.getChildCount();
                        if(childCount>0) {
                            layout.removeAllViewsInLayout();
                        }
                        level.setImageResource(R.drawable.level2);
                        level.setLayoutParams(params);
                        level.setMinScale(0.5f);
                        level.setMaxScale(3.0f);
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

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


}