package com.socialcard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Cai on 14-4-26.
 */
public class MainActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                Intent it=new Intent(MainActivity.this,PrimaryActivity.class);
                startActivity(it);
                finish();
            }
        }, 1500);
    }
}