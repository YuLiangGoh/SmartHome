package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;
import com.suke.widget.SwitchButton;

public class AirConditionerSetting extends AppCompatActivity {

    private ImageView on,off,wind,aircon,back;
    private SharedPreferences sharedPreferences;
    private SwitchButton on_off_button;
    private Croller croller;
    private Button swing,turbo,timer;
    boolean swing_check,turbo_check,timer_check;

    private String TAG = AirConditionerSetting.class.getSimpleName();

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
                if(timer_check){
                    timer.getBackground().setTint(getResources().getColor(R.color.grey,getApplication().getTheme()));
                    timer_check = false;
                    editor.putBoolean("air_con_timer_check", timer_check);
                    editor.apply();
                }else if(!timer_check){
                    timer.getBackground().setTint(getResources().getColor(R.color.lightgreen,getApplication().getTheme()));
                    timer_check = true;
                    editor.putBoolean("air_con_timer_check", timer_check);
                    editor.apply();
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
}