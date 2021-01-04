package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.suke.widget.SwitchButton;

public class AirConditionerSetting extends AppCompatActivity {

    private ImageView on,off,wind,aircon,back;
    private SharedPreferences sharedPreferences;
    private SwitchButton on_off_button;

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sharedPreferences = getSharedPreferences("Living_Room", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences.getBoolean("air_con_check", false)){
            on_off_button.setChecked(true);
            setAirConViewActive();
        }else{
            on_off_button.setChecked(false);
            setAirConViewUnActive();
        }

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