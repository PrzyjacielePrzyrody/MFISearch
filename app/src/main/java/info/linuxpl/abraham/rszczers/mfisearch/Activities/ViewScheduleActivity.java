package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

import info.linuxpl.abraham.rszczers.mfisearch.Features.Dates;
import info.linuxpl.abraham.rszczers.mfisearch.Features.PlanedActivity;
import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseAdapter;
import info.linuxpl.abraham.rszczers.mfisearch.Features.Schedule;
import info.linuxpl.abraham.rszczers.mfisearch.R;


public class ViewScheduleActivity extends ActionBarActivity {

    private ArrayList<PlanedActivity> list;
    private ActivityRowAdapter adapter;
    CaldroidFragment dialogCaldroidFragment;
    Calendar calendar;
    SimpleDateFormat formatter;
    LinearLayout dayLayout;
    TextView datString;
    TextView datWeekDay;
    Context context;


    final CaldroidListener listener = new CaldroidListener() {
        @Override
        public void onSelectDate(Date date, View view) {
            String selectedDay = formatter.format(date);
            String[] explodeDate = selectedDay.split(" ");
            selectedDay = explodeDate[2] + "-" + explodeDate[1] + "-" + explodeDate[0];
            Calendar tmp = Dates.stringToCalendar(selectedDay);
            String dayOfWeek = Dates.weekDayName(tmp);
            datString.setText(selectedDay);
            datWeekDay.setText(dayOfWeek);
            dialogCaldroidFragment.dismiss();
        }
        @Override
        public void onCaldroidViewCreated() {
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_schedule);
        calendar = Calendar.getInstance();
        Intent i = getIntent();
        String data;
        context = this;
        if (i.getStringExtra("date") != null) {
            data = i.getStringExtra("date");
        } else {
            Calendar current = Calendar.getInstance();
            data = Dates.calendarToString(current);
        }

         dayLayout = (LinearLayout) findViewById(R.id.dateLayout);
         datString = (TextView) dayLayout.findViewById(R.id.dayOfWeek);
         datWeekDay = (TextView) dayLayout.findViewById(R.id.textViewDayName);

        Button next = (Button) findViewById(R.id.btnNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar dat = Dates.stringToCalendar((String) datString.getText());
                dat.add(Calendar.DATE, 1);
                Intent update = new Intent(ViewScheduleActivity.this, ViewScheduleActivity.class);
                update.putExtra("date", Dates.calendarToString(dat));
                finish();
                startActivity(update);
            }
        });

        Button previous = (Button) findViewById(R.id.btnPrevious);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar dat = Dates.stringToCalendar((String) datString.getText());
                dat.add(Calendar.DATE, -1);
                Intent update = new Intent(ViewScheduleActivity.this, ViewScheduleActivity.class);
                update.putExtra("date", Dates.calendarToString(dat));
                finish();
                startActivity(update);
            }
        });

        Schedule pl = new Schedule();
        datString.setText(data.split(" ")[0]);
        datWeekDay.setText(Dates.weekDayName(Dates.stringToCalendar(data)));


        TreeMap<Calendar, PlanedActivity> tree = pl.getDaySchedule(Dates.dateToString(data), this, new String[]{"EXAMS", "EXERCISES", "LECTURES", "OTHER"});

        list = pl.treeToArray(tree);

        adapter = new ActivityRowAdapter(this, R.layout.row_activity_list, list);
        final ListView lv = (ListView) findViewById(R.id.activitiesListView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanedActivity s = (PlanedActivity) lv.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), "Wybrałeś z listy " + s.getID(), Toast.LENGTH_SHORT).show();
            }

        });

        registerForContextMenu(lv);

        formatter = new SimpleDateFormat("dd MM yyyy");

        dayLayout.setOnTouchListener(new View.OnTouchListener() {
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
        public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo
        menuInfo){
            super.onCreateContextMenu(menu, v, menuInfo);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.schedule_menu, menu);
        }

        @Override
        public boolean onContextItemSelected (MenuItem item){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            PlanedActivity s = (PlanedActivity) list.get(info.position);
            switch (item.getItemId()) {
                case R.id.find_map_menu:
                    Intent found = new Intent(context, FindOnMapActivity.class);
                    String tmp = s.getRoom().getName();
                    found.putExtra("roomID", tmp);
                    startActivity(found);
                    return true;

                case R.id.delete_from_shedule:
                    Toast.makeText(getBaseContext(), "Wybrałeś z listy" + s.getID(), Toast.LENGTH_SHORT).show();
                    final DatabaseAdapter db = new DatabaseAdapter(this);
                    db.delete(s);
                    list.remove(info.position);
                    adapter.notifyDataSetChanged();
                    return true;

                case R.id.edit_schedule_menu:
                    Intent update = new Intent(ViewScheduleActivity.this, EditScheduleActivity.class);
                    Log.d("id przed inteenetm", ""+s.getID());
                    update.putExtra("id", ""+s.getID());
                    update.putExtra("table", s.getTable());
                    startActivity(update);
                    return true;
                default:
                    return super.onContextItemSelected(item);
            }
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