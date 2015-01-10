package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import info.linuxpl.abraham.rszczers.mfisearch.Features.Building;
import info.linuxpl.abraham.rszczers.mfisearch.R;

import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.polites.android.GestureImageView;

public class FindOnMapActivity extends ActionBarActivity {
    Building mfi;
    String roomID;
    GestureImageView view;
    Bitmap gestureBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_find_on_map);

        roomID = getIntent().getExtras().getString("roomID");
        mfi = new Building(this);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        view = new GestureImageView(this);

        gestureBitmap = mfi.find(roomID, this);
        view.setImageBitmap(gestureBitmap);

        view.setLayoutParams(params);
        view.setMinScale(0.5f);
        view.setMaxScale(3.0f);

        ViewGroup layout = (ViewGroup) findViewById(R.id.find_on_map_layout);

        layout.addView(view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gestureBitmap = null;
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        gestureBitmap = null;
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_on_map, menu);
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
