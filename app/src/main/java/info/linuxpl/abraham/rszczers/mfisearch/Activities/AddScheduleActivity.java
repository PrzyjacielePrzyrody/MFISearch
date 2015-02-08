package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


public class AddScheduleActivity extends ActionBarActivity {
    DatabaseAdapter adapter;
    CaldroidFragment dialogCaldroidFragment;
    EditText nameField;
    EditText dateField;
    EditText timeField;
    EditText lectorField;
    EditText descField;
    EditText duration;
    EditText period;
    RadioGroup activityTypes;
    SimpleDateFormat formatter;
    SimpleDateFormat form;
    Spinner roomPick;
    TimePickerDialog tp;
    Calendar calendar;
    TimePickerDialog.OnTimeSetListener timePickerListener;
    Button saveExam;
    private Context context;
    String dat;

    final CaldroidListener listener = new CaldroidListener() {

        @Override
        public void onSelectDate(Date date, View view) {
            dateField.setText(formatter.format(date));
            dat=form.format(date);
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
        setContentView(R.layout.activity_add_activity);


        context=this;
        form=new SimpleDateFormat("yyyy-MM-dd");
        formatter = new SimpleDateFormat("dd MMM yyyy");
        nameField = (EditText) findViewById(R.id.name_add_activity_field);
        duration = (EditText) findViewById(R.id.duration_add_activity);

        dateField = (EditText) findViewById(R.id.date_add_activity_field);
        dateField.setInputType(InputType.TYPE_NULL);

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

        calendar = Calendar.getInstance();

        SharedPreferences settings = getApplicationContext().getSharedPreferences("MFISettings", MODE_PRIVATE);
        final String endOfSemester = settings.getString("semesterEnd", Dates.calendarToString(calendar));

        timePickerListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String h = Integer.toString(hourOfDay);
                String m = Integer.toString(minute);

                if(h.length()==1) {
                    h = "0" + h;
                }
                if(m.length()==1) {
                    m = "0" + m;
                }
                timeField.setText(h + ":" + m);
            }
        };

        timeField = (EditText) findViewById(R.id.time_add_activity_field);
        timeField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                if (action == MotionEvent.ACTION_DOWN) {
                    tp = new TimePickerDialog(AddScheduleActivity.this,
                            TimePickerDialog.THEME_DEVICE_DEFAULT_DARK,
                            timePickerListener,
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true);
                    tp.setTitle("Wybierz godzinę");
                    tp.show();
                }
                return false;
            }
        });

        roomPick = (Spinner) findViewById(R.id.room_add_activity_spinner);
        adapter = new DatabaseAdapter(getApplicationContext());
        SimpleCursorAdapter sca = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item,
                adapter.getRoomNames(),
                new String[] {"name"},
                new int[] {android.R.id.text1}, 0);
        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomPick.setAdapter(sca);

        lectorField =(EditText) findViewById(R.id.instructor_add_activity_field);
        descField = (EditText) findViewById(R.id.description_add_activity_field);
        period=(EditText) findViewById(R.id.period_add_activity_field);
        activityTypes=(RadioGroup) findViewById(R.id.radio_group_types);


        saveExam = (Button) findViewById(R.id.button_add_activity);

        saveExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityFactory af=new ActivityFactory(context);
                String time=""+dat+" "+timeField.getText()+":00";



//                String howLong="2015-05-03 12:00:50";
                String howLong = endOfSemester;
                int per=Integer.parseInt(period.getText().toString());

              //  Log.d("times", time + "      " + howLong);
                Cursor cur=(Cursor) roomPick.getSelectedItem();
                String room= cur.getString(cur.getColumnIndex("name"));
                int selectedId=activityTypes.getCheckedRadioButtonId();
                String napi="";
                switch (selectedId){
                    case R.id.radio_type_exercise:
                        napi="exercise";
                        break;
                    case R.id.radio_type_lecture:
                        napi="lecture";
                        break;
                    case R.id.radio_type_other:
                        napi="other";
                        break;
                }

                PlanedActivity pa= af.make(napi, nameField.getText().toString(), time, howLong, per,
                        adapter.getClassroom(room), duration.getText().toString(),
                        lectorField.getText().toString(), descField.getText().toString());
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_schedule, menu);
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
