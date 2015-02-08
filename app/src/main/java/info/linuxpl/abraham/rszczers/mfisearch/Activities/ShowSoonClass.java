package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import info.linuxpl.abraham.rszczers.mfisearch.Features.Dates;
import info.linuxpl.abraham.rszczers.mfisearch.Features.PlanedActivity;
import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseAdapter;
import info.linuxpl.abraham.rszczers.mfisearch.Features.Schedule;
import info.linuxpl.abraham.rszczers.mfisearch.R;


public class ShowSoonClass extends ActionBarActivity {
    private SimpleDateFormat form;
    private SimpleDateFormat formatter;

    Schedule s=new Schedule();
    PlanedActivity pa;
    TextView name;
    TextView time;
    TextView room;
    TextView free;
    TextView labelFree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_soon_class);

        ShowSoonClass context = this;
        form = new SimpleDateFormat("yyyy-MM-dd");
        formatter = new SimpleDateFormat("dd MMM yyyy");

        s = new Schedule();
        pa=s.findNextClasses(this);
        name = (TextView) findViewById(R.id.soonActName);
        time = (TextView) findViewById(R.id.actTime);
        room = (TextView) findViewById(R.id.actRoom);
        free = (TextView) findViewById(R.id.lateOrBefore);
        labelFree = (TextView) findViewById(R.id.lateOrBe);

        if(pa!=null) {
            name.setText(pa.getName());
            time.setText(pa.getDate());
            room.setText(pa.getRoom().getName());
            labelFree.setText(Dates.timeBetween(pa)[1]);
            free.setText(Dates.timeBetween(pa)[0]);
//            Log.d("data w soon class", pa.getDate());
        } else {
            name.setText("Nie masz najbliższy zajęć w tym miesiącu");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_soon_class, menu);
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
