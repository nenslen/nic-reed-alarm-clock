package com.example.nicdesktop.nicreedalarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private AlarmManager alarmManager;
    private PendingIntent puzzleIntent;
    private TimePicker timePicker;
    private ToggleButton toglBtnSetAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, PuzzleActivity.class);
        setContentView(R.layout.activity_main);
        puzzleIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        toglBtnSetAlarm = (ToggleButton) findViewById(R.id.tgleBtnSetAlarm);

        toglBtnSetAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d("TIME", "" + (timePicker.getCurrentHour() * 60 * 60000 + timePicker.getCurrentMinute() * 60000));
                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                            (timePicker.getCurrentHour() * 60 * 60000 + timePicker.getCurrentMinute() * 60000),
                            AlarmManager.INTERVAL_DAY, puzzleIntent);
                } else {
                    alarmManager.cancel(puzzleIntent);
                }
            }
        });

    }
}
