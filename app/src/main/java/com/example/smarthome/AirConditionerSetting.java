package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
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
            Log.d(TAG, "onCreate: " + temp);
            croller.setLabel( temp + " \u2103");
            int temp_integer = Integer.parseInt(temp) - 15;
            Log.d(TAG, "onCreate: " + temp_integer);
            croller.setProgress(temp_integer);
        }

        if(sharedPreferences.getBoolean("air_con_check", false)){
            on_off_button.setChecked(true);
            setAirConViewActive();
        }else{
            on_off_button.setChecked(false);
            setAirConViewUnActive();
        }

        croller.setLabel(String.valueOf(croller.getProgress()));
        croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                Log.d(TAG, "onProgressChanged: " + String.valueOf(progress + 15));
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