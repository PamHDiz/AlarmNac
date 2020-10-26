package com.example.alarmnac;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TimePicker timePicker;

    int hora, min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        timePicker = findViewById(R.id.timePicker);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hora = hourOfDay;
                min = minute;
                textView.setText(textView.getText().toString() + " " + hora + ":" + min);
            }
        });
    }

    public void setTimer(View v){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Date data = new Date();
        Calendar calAlarme = Calendar.getInstance();
        Calendar calHoje = Calendar.getInstance();

        calHoje.setTime(data);
        calAlarme.setTime(data);

        calAlarme.set(Calendar.HOUR_OF_DAY, hora);
        calAlarme.set(Calendar.MINUTE, min);
        calAlarme.set(Calendar.SECOND, 0);

        if(calAlarme.before(calHoje)){
            calAlarme.add(Calendar.DATE,1);
        }

        Intent i = new Intent(MainActivity.this, MyBroadCastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                24444,i,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calAlarme.getTimeInMillis(),pendingIntent);
    }
}