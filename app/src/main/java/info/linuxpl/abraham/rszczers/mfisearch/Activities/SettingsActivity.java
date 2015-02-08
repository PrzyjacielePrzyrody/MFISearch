package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import info.linuxpl.abraham.rszczers.mfisearch.Features.ActivityFactory;
import info.linuxpl.abraham.rszczers.mfisearch.Features.Dates;
import info.linuxpl.abraham.rszczers.mfisearch.Features.PlanedActivity;
import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseAdapter;
import info.linuxpl.abraham.rszczers.mfisearch.R;


public class SettingsActivity extends ActionBarActivity {
    CaldroidFragment dialogCaldroidFragment;
    EditText dateField;
    SimpleDateFormat formatter;
    SimpleDateFormat form;
    TimePickerDialog tp;
    Calendar calendar;
    SharedPreferences.Editor editor;
    private Context context;

    final CaldroidListener listener = new CaldroidListener() {

        @Override
        public void onSelectDate(Date date, View view) {
            String selectedDay = formatter.format(date);
            String[] explodeDate = selectedDay.split(" ");
            selectedDay = explodeDate[2] + "-" + explodeDate[1] + "-" + explodeDate[0];
            dateField.setText(selectedDay);
            editor.putString("semesterEnd", selectedDay);
            editor.commit();
            dialogCaldroidFragment.dismiss();
        }

        @Override
        public void onCaldroidViewCreated() {
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("MFISettings", MODE_PRIVATE);
        editor = settings.edit();

        context=this;
        calendar = Calendar.getInstance();
        form=new SimpleDateFormat("yyyy-MM-dd");
        formatter = new SimpleDateFormat("dd MM yyyy");

        dateField = (EditText) findViewById(R.id.semester_end_date_field);
        dateField.setInputType(InputType.TYPE_NULL);

        String CurrentSemesterEnd = settings.getString("semesterEnd", Dates.calendarToString(calendar));
        dateField.setText(CurrentSemesterEnd);

        dateField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                if (action == MotionEvent.ACTION_DOWN) {
                    dialogCaldroidFragment = CaldroidFragment.newInstance("Wybierz termin",
                            calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
                    dialogCaldroidFragment.setCaldroidListener(listener);
                    dialogCaldroidFragment.show(getSupportFragmentManager(), "TAG");
                }
                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_exam, menu);
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
