package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import info.linuxpl.abraham.rszczers.mfisearch.Features.Dates;
import info.linuxpl.abraham.rszczers.mfisearch.Features.PlanedActivity;
import info.linuxpl.abraham.rszczers.mfisearch.Features.Schedule;
import info.linuxpl.abraham.rszczers.mfisearch.R;


public class ShowSoonClass extends ActionBarActivity {
    private SimpleDateFormat form;
    private SimpleDateFormat formatter;

    Schedule s;
    PlanedActivity pa;
    TextView name;
    TextView time;
    TextView room;
    TextView free;
    TextView labelFree;
    Button findButton;
    Context context;
    TextView actroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_soon_class);

        context = this;
        form = new SimpleDateFormat("yyyy-MM-dd");
        formatter = new SimpleDateFormat("dd MMM yyyy");

        s = new Schedule();
        pa = s.findNextClasses(context);
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

        findButton = (Button) findViewById(R.id.button_find_on_plan);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent found = new Intent(context, FindOnMapActivity.class);
                String tmp = room.getText().toString();
                found.putExtra("roomID", tmp);
                startActivity(found);
            }
        });

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
