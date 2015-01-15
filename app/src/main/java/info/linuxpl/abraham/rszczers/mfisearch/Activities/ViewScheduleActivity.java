package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeMap;

import info.linuxpl.abraham.rszczers.mfisearch.Features.ActivityFactory;
import info.linuxpl.abraham.rszczers.mfisearch.Features.Classroom;
import info.linuxpl.abraham.rszczers.mfisearch.Features.PlanedActivity;
import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseAdapter;
import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseHelper;
import info.linuxpl.abraham.rszczers.mfisearch.Features.Schedule;
import info.linuxpl.abraham.rszczers.mfisearch.R;


public class ViewScheduleActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_schedule);

        TabHost tabhost= (TabHost) findViewById(R.id.dayOrWeek);

        tabhost.setup();
        TabHost.TabSpec find=tabhost.newTabSpec("Find");
        find.setContent(R.id.day);
        find.setIndicator(getString(R.string.viewDay));
        tabhost.addTab(find);


        find=tabhost.newTabSpec("View");
        find.setContent(R.id.week);
        find.setIndicator(getString(R.string.viewWeek));
        tabhost.addTab(find);


        Schedule pl= new Schedule();
        TreeMap<Calendar, PlanedActivity> tree= pl.getDaySchedule("2014-1-12 ", this);
        PlanedActivity p;
        Calendar key;
        ArrayList<PlanedActivity> list=new ArrayList<PlanedActivity>();
        while(!tree.isEmpty()){
            key=tree.firstKey();
            p=tree.remove(key);
            list.add(p);

        }
        ActivityRowAdapter adapter=new ActivityRowAdapter(this, R.layout.row_activity_list, list);
        ListView lv=(ListView) findViewById(R.id.activitiesListView);
        lv.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_schedule, menu);
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