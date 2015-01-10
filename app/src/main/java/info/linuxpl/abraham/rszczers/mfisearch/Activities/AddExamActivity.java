package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseAdapter;
import info.linuxpl.abraham.rszczers.mfisearch.R;


public class AddExamActivity extends ActionBarActivity {
    CaldroidFragment dialogCaldroidFragment;
    EditText nameField;
    EditText dateField;
    EditText timeField;
    SimpleDateFormat formatter;
    Spinner roomPick;
    TimePickerDialog tp;
    Calendar calendar;
    TimePickerDialog.OnTimeSetListener timePickerListener;

    final CaldroidListener listener = new CaldroidListener() {

        @Override
        public void onSelectDate(Date date, View view) {
            dateField.setText(formatter.format(date));
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
        setContentView(R.layout.activity_add_exam);

        formatter = new SimpleDateFormat("dd MMM yyyy");
        nameField = (EditText) findViewById(R.id.name_add_exam_field);

        dateField = (EditText) findViewById(R.id.date_add_exam_field);
        dateField.setInputType(InputType.TYPE_NULL);

        dateField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                if (action == MotionEvent.ACTION_DOWN) {
                    dialogCaldroidFragment = CaldroidFragment.newInstance("Wybierz termin", 3, 2013);
                    dialogCaldroidFragment.setCaldroidListener(listener);
                    dialogCaldroidFragment.show(getSupportFragmentManager(), "TAG");
                }
                return false;
            }
        });

        calendar = Calendar.getInstance();

        timePickerListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeField.setText(hourOfDay + ":" + minute);
            }
        };

        timeField = (EditText) findViewById(R.id.time_add_exam_field);
        timeField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                if (action == MotionEvent.ACTION_DOWN) {
                    tp = new TimePickerDialog(AddExamActivity.this,
                            TimePickerDialog.THEME_DEVICE_DEFAULT_DARK,
                            timePickerListener,
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true);
                    tp.show();
                }
                return false;
            }
        });


        roomPick = (Spinner) findViewById(R.id.room_add_exam_spinner);
        DatabaseAdapter adapter = new DatabaseAdapter(getApplicationContext());
        SimpleCursorAdapter sca = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item,
                adapter.getRoomNames(),
                new String[] {"name"},
                new int[] {android.R.id.text1}, 0);
        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomPick.setAdapter(sca);
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
