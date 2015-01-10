package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import info.linuxpl.abraham.rszczers.mfisearch.Features.Schedule;
import info.linuxpl.abraham.rszczers.mfisearch.R;

/**
 * Splash.java
 *
 * @author Rafał Szczerski
 *        <rafal.szczerski(at)gmail.com>
 */

public class Splash extends Activity  {
    LinearLayout layout;
    Thread timer;

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        layout = (LinearLayout) findViewById(R.id.splash_screen_layout);


        timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent openMainActivity = new Intent("info.linuxpl.abraham.rszczers.MAINACTIVITY");
                    startActivity(openMainActivity);
                }
            }
        };
        timer.start();

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.interrupt();
//                Intent start = new Intent(Splash.this, MainActivity.class);
//                startActivity(start);
            }
        });
    }
}
