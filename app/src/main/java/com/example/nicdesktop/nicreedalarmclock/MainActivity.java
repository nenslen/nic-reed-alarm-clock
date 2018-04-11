package com.example.nicdesktop.nicreedalarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private AlarmManager alarmManager;
    private PendingIntent puzzleIntent;
    private Intent intent;
    private TimePicker timePicker;
    private ToggleButton toglBtnSetAlarm;
    private TextView puzzleNum;
    private SeekBar puzzleNumBar;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, PuzzleActivity.class);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();


        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        toglBtnSetAlarm = (ToggleButton) findViewById(R.id.tgleBtnSetAlarm);
        puzzleNum = (TextView) (findViewById(R.id.puzzleNum));
        puzzleNumBar = (SeekBar) (findViewById(R.id.seekBarNumPuzzles));


        toglBtnSetAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    intent.putExtra("numPuzles", Integer.parseInt(puzzleNum.getText().toString()));
                    puzzleIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                            getTimeMilli(timePicker),
                            AlarmManager.INTERVAL_DAY, puzzleIntent);
                } else {
                    alarmManager.cancel(puzzleIntent);
                }
            }
        });

        puzzleNumBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                puzzleNum.setText(String.valueOf(progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private long getTimeMilli(TimePicker timePicker){
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long millis = calendar.getTimeInMillis();

        return millis;
    }
}
