package com.example.nicdesktop.nicreedalarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAlarm();
    }


    /** Called when the user taps the Send button */
    public void startAlarm() {
        Intent intent = new Intent(this, PuzzleActivity.class);
        startActivity(intent);
    }
}
