package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import info.linuxpl.abraham.rszczers.mfisearch.R;


public class Schedule extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Button check_schedule= (Button) findViewById(R.id.see_schedule_button);
        check_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change_to_sched= new Intent(Schedule.this, ViewScheduleActivity.class);
                startActivity(change_to_sched);
            }
        });

        Button edit_schedul= (Button) findViewById(R.id.edit_schedule_button);
        edit_schedul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change_to_edit_schedule= new Intent(Schedule.this, EditScheduleActivity.class);
                startActivity(change_to_edit_schedule);
            }
        });

        Button add_schedule=(Button) findViewById(R.id.add_schedule_button);
        add_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change_to_add_schedule= new Intent(Schedule.this, AddScheduleActivity.class);
                startActivity(change_to_add_schedule);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
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
