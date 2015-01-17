package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import info.linuxpl.abraham.rszczers.mfisearch.Features.PlanedActivity;
import info.linuxpl.abraham.rszczers.mfisearch.Features.Schedule;
import info.linuxpl.abraham.rszczers.mfisearch.R;


public class ShowSoonClass extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_soon_class);

        Schedule s=new Schedule();
        PlanedActivity pa=s.findNextClasses(this);
        TextView name=(TextView) findViewById(R.id.soonActName);
        TextView time=(TextView) findViewById(R.id.actTime);
        TextView room=(TextView) findViewById(R.id.actRoom);
        TextView free=(TextView) findViewById(R.id.lateOrBefore);

        name.setText(pa.getName());
        time.setText(pa.getDate());
        room.setText(pa.getRoom().getName());

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
