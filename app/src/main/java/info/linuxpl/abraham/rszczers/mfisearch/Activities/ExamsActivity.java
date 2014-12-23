package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import info.linuxpl.abraham.rszczers.mfisearch.R;


public class ExamsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        Button check_exams= (Button) findViewById(R.id.check_exam_button);
        check_exams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change_to_check= new Intent(ExamsActivity.this, CheckExamActivity.class);
                startActivity(change_to_check);
            }
        });

        Button edit_exam= (Button) findViewById(R.id.edit_exam_button);
        edit_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change_to_edit_exam= new Intent(ExamsActivity.this, EditExamActivity.class);
                startActivity(change_to_edit_exam);
            }
        });

        Button add_exam= (Button) findViewById(R.id.add_exam_butoon);
        add_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change_to_add_exam= new Intent(ExamsActivity.this, AddExamActivity.class);
                startActivity(change_to_add_exam);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exams, menu);
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
