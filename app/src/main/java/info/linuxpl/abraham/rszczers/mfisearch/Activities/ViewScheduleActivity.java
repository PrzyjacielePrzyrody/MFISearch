package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_schedule);

        final DatabaseAdapter db = new DatabaseAdapter(this);
        Intent i=getIntent();
        String data;

        if(i.getStringExtra("date")!=null){
            data=i.getStringExtra("date");
        }else{
            Calendar current=Calendar.getInstance();
            data=db.calendarToString(current);
        }

        LinearLayout dayLayout= (LinearLayout) findViewById(R.id.dateLayout);
        final TextView datString=(TextView) dayLayout.findViewById(R.id.dayOfWeek);
        final TextView datWeekDay=(TextView) dayLayout.findViewById(R.id.textViewDayName);

        Button next= (Button) findViewById(R.id.btnNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar dat=db.stringToCalendar((String) datString.getText());
                dat.add(Calendar.DATE, 1);
                Intent update= new Intent(ViewScheduleActivity.this, ViewScheduleActivity.class);
                update.putExtra("date", db.calendarToString(dat));
                finish();
                startActivity(update);
            }
        });

        Button previous= (Button) findViewById(R.id.btnPrevious);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar dat = db.stringToCalendar((String) datString.getText());
                dat.add(Calendar.DATE, -1);
                Intent update= new Intent(ViewScheduleActivity.this, ViewScheduleActivity.class);
                update.putExtra("date", db.calendarToString(dat));
                finish();
                startActivity(update);
            }
        });

        Schedule pl= new Schedule();
        datString.setText(data);
        datWeekDay.setText(db.weekDayName(db.stringToCalendar(data)));


        TreeMap<Calendar, PlanedActivity> tree= pl.getDaySchedule(data, this);

        ArrayList<PlanedActivity> list= pl.treeToArray(tree);

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