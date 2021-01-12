package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;
import com.suke.widget.SwitchButton;

import java.text.DateFormat;
import java.util.Calendar;

public class AirConditionerSetting extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private ImageView on,off,wind,aircon,back;
    private SharedPreferences sharedPreferences;
    private SwitchButton on_off_button;
    private Croller croller;
    private Button swing,turbo,timer;
    boolean swing_check,turbo_check,timer_check;

    private NotificationHelper notificationHelper;

    private String TAG = AirConditionerSetting.class.getSimpleName();

    private static AirConditionerSetting airConditionerSetting;

    public AirConditionerSetting(){
        airConditionerSetting = this;
    }

    public static AirConditionerSetting getInstance() {
        return airConditionerSetting;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioner_setting);

        //Declare
        back = findViewById(R.id.air_conditioner_back);
        aircon = findViewById(R.id.aircon_icon);
        on = findViewById(R.id.aircon_on_icon);
        off = findViewById(R.id.aircon_off_icon);
        wind = findViewById(R.id.aircon_wind_icon);
        on_off_button = findViewById(R.id.on_off_button);
        croller = findViewById(R.id.croller);
        swing = findViewById(R.id.button_air_con_swing);
        turbo = findViewById(R.id.button_air_con_turbo);
        timer = findViewById(R.id.button_air_con_timer);
        notificationHelper = new NotificationHelper(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sharedPreferences = getSharedPreferences("Living_Room", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences.getString("temperature","").equals("")){
            croller.setLabel("16 \u2103");
        }else{
            String temp = sharedPreferences.getString("temperature","");
            croller.setLabel( temp + " \u2103");
            int temp_integer = Integer.parseInt(temp) - 15;
            croller.setProgress(temp_integer);
        }

        if(sharedPreferences.getBoolean("air_con_check", false)){
            on_off_button.setChecked(true);
            setAirConViewActive();
        }else{
            on_off_button.setChecked(false);
            setAirConViewUnActive();
        }

        if(sharedPreferences.getBoolean("air_con_swing_check",false)){
            swing_check = true;
            swing.getBackground().setTint(getResources().getColor(R.color.lightorange,getApplication().getTheme()));
        }else{
            swing_check = false;
            swing.getBackground().setTint(getResources().getColor(R.color.grey,getApplication().getTheme()));
        }

        if(sharedPreferences.getBoolean("air_con_turbo_check",false)){
            turbo_check = true;
            turbo.getBackground().setTint(getResources().getColor(R.color.lightyellow,getApplication().getTheme()));
        }else{
            turbo_check = false;
            turbo.getBackground().setTint(getResources().getColor(R.color.grey,getApplication().getTheme()));
        }

        if(sharedPreferences.getBoolean("air_con_timer_check",false)){
            timer_check = true;
            timer.getBackground().setTint(getResources().getColor(R.color.lightgreen,getApplication().getTheme()));
        }else{
            timer_check = false;
            timer.getBackground().setTint(getResources().getColor(R.color.grey,getApplication().getTheme()));
        }

        croller.setLabel(String.valueOf(croller.getProgress()));
        croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                croller.setLabel(String.valueOf(progress + 15));
            }

            @Override
            public void onStartTrackingTouch(Croller croller) {

            }

            @Override
            public void onStopTrackingTouch(Croller croller) {
                editor.putString("temperature",String.valueOf(croller.getProgress() + 15));
                editor.apply();
            }
        });

        on_off_button.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                editor.putBoolean("air_con_check",isChecked);
                editor.apply();
                if (isChecked){
                    setAirConViewActive();
                }else{
                    setAirConViewUnActive();
                }
            }
        });

        swing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swing_check){
                    swing.getBackground().setTint(getResources().getColor(R.color.grey,getApplication().getTheme()));
                    swing_check = false;
                    editor.putBoolean("air_con_swing_check", swing_check);
                    editor.apply();
                }else if(!swing_check){
                    swing.getBackground().setTint(getResources().getColor(R.color.lightorange,getApplication().getTheme()));
                    swing_check = true;
                    editor.putBoolean("air_con_swing_check", swing_check);
                    editor.apply();
                }
            }
        });

        turbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turbo_check){
                    turbo.getBackground().setTint(getResources().getColor(R.color.grey,getApplication().getTheme()));
                    turbo_check = false;
                    editor.putBoolean("air_con_turbo_check", turbo_check);
                    editor.apply();
                }else if(!turbo_check){
                    turbo.getBackground().setTint(getResources().getColor(R.color.lightyellow,getApplication().getTheme()));
                    turbo_check = true;
                    editor.putBoolean("air_con_turbo_check", turbo_check);
                    editor.apply();
                }
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendNotificationTimer("Alarm","Aircon open!");
                if(timer_check){
                    timer.getBackground().setTint(getResources().getColor(R.color.grey,getApplication().getTheme()));
                    timer_check = false;
                    editor.putBoolean("air_con_timer_check", timer_check);
                    editor.apply();
                    cancelAlarm();
                }else if(!timer_check){
                    DialogFragment dialogFragment = new TimePickerFragment();
                    dialogFragment.show(getSupportFragmentManager(),"time picker");
                }
            }
        });
    }

    private void setAirConViewActive(){
        wind.setAlpha(0f);
        wind.setVisibility(View.VISIBLE);
        wind.animate()
                .alpha(1f)
                .setDuration(1000)
                .setListener(null);

        //wind.setVisibility(View.VISIBLE);
        on.setVisibility(View.VISIBLE);
        off.setVisibility(View.INVISIBLE);
    }

    private void setAirConViewUnActive(){
        wind.setAlpha(1f);
        wind.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(null);

        //wind.setVisibility(View.INVISIBLE);
        on.setVisibility(View.INVISIBLE);
        off.setVisibility(View.VISIBLE);
    }

    public void sendNotificationTimer(String title, String message){
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification(title,message);
        notificationHelper.getManager().notify(1,nb.build());
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timer.getBackground().setTint(getResources().getColor(R.color.lightgreen,getApplication().getTheme()));
        timer_check = true;
        sharedPreferences = getSharedPreferences("Living_Room", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("air_con_timer_check", timer_check);
        editor.apply();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);

        updateTimeText(c);
        startAlarm(c);
    }

    private void updateTimeText(Calendar c){
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        Toast.makeText(this,timeText,Toast.LENGTH_SHORT);
    }

    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE,1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }

    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this,"Alarm canceled.",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("Living_Room", Context.MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("air_con_timer_check",false)){
            timer.getBackground().setTint(getResources().getColor(R.color.grey,getApplication().getTheme()));
            timer_check = false;
        }
        if(!sharedPreferences.getBoolean("air_con_check", false)){
            on_off_button.setChecked(false);
            finish();
        }
    }

    public void refresh(){
        //refresh Logic here
        if(!sharedPreferences.getBoolean("air_con_timer_check",false)){
            timer.getBackground().setTint(getResources().getColor(R.color.grey,getApplication().getTheme()));
            timer_check = false;
        }
        if(!sharedPreferences.getBoolean("air_con_check", false)){
            on_off_button.setChecked(false);
            setAirConViewUnActive();
        }
    }

}