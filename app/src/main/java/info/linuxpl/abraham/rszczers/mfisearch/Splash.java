package info.linuxpl.abraham.rszczers.mfisearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by r on 23.11.14.
 */
public class Splash extends Activity  {
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Thread timer = new Thread(){
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
    }
}
