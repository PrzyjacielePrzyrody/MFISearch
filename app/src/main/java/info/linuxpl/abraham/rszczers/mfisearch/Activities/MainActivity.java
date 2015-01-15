package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import info.linuxpl.abraham.rszczers.mfisearch.Features.ActivityFactory;
import info.linuxpl.abraham.rszczers.mfisearch.Features.ExamPlaned;
import info.linuxpl.abraham.rszczers.mfisearch.Features.Schedule;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.TreeMap;

import info.linuxpl.abraham.rszczers.mfisearch.Features.PlanedActivity;
import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseAdapter;
import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseHelper;
import info.linuxpl.abraham.rszczers.mfisearch.R;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button soon_class= (Button) findViewById(R.id.soonclasse_bottom);
        soon_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change_to_soon= new Intent(MainActivity.this, ShowSoonClass.class);
                startActivity(change_to_soon);
            }
        });

        Button schedule= (Button) findViewById(R.id.schedule_bottom);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change_to_schedule= new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(change_to_schedule);
            }
        });

        Button map= (Button) findViewById(R.id.map_button);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change_to_map= new Intent(MainActivity.this, MapActivity.class);
                startActivity(change_to_map);
            }
        });

        Button exams= (Button) findViewById(R.id.exam_button);
        exams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change_to_exam = new Intent(MainActivity.this, ExamsActivity.class);
                startActivity(change_to_exam);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
