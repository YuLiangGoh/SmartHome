package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import org.w3c.dom.Text;

public class Sensors extends AppCompatActivity {

    private TextView door, smoke, gas, temperature, humidity;
    private SwitchButton door_button, smoke_button, gas_button;
    private ImageView back;
    private SharedPreferences sharedPreferences;
    private EditText currTemp, currHumid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(Sensors.this,R.color.white));// set status background white
        setContentView(R.layout.activity_sensors);

        door = findViewById(R.id.door);
        smoke = findViewById(R.id.smoke);
        gas = findViewById(R.id.gas);
        temperature = findViewById(R.id.temperature);
        humidity = findViewById(R.id.humidity);
        door_button = findViewById(R.id.door_button);
        smoke_button = findViewById(R.id.smoke_button);
        gas_button = findViewById(R.id.gas_button);
        currTemp = findViewById(R.id.curr_temp);
        currHumid = findViewById(R.id.curr_humid);
        back = findViewById(R.id.sensor_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sharedPreferences = getSharedPreferences("sensors", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences.getBoolean("door_status",false)){
            door_button.setChecked(true);
            door.setText("Door is unlocked");
        } else {
            door_button.setChecked(false);
            door.setText("Door is locked");
        }

        door_button.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                editor.putBoolean("door_status",isChecked);
                editor.apply();
                if (isChecked){
                    door.setText("Door is unlocked");
                }else{
                    door.setText("Door is locked");
                }
            }
        });

        if(sharedPreferences.getBoolean("smoke_status",false)){
            smoke_button.setChecked(true);
            smoke.setText("Presence of smoke");
        } else {
            smoke_button.setChecked(false);
            smoke.setText("Absence of smoke");
        }

        smoke_button.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                editor.putBoolean("smoke_status",isChecked);
                editor.apply();
                if (isChecked){
                    smoke.setText("Presence of smoke");
                }else{
                    smoke.setText("Absence of smoke");
                }
            }
        });

        if(sharedPreferences.getBoolean("gas_status",false)){
            gas_button.setChecked(true);
            gas.setText("Presence of gas leaking");
        } else {
            gas_button.setChecked(false);
            gas.setText("Absence of gas leaking");
        }

        gas_button.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                editor.putBoolean("smoke_status",isChecked);
                editor.apply();
                if (isChecked){
                    gas.setText("Presence of gas leaking");
                }else{
                    gas.setText("Absence of gas leaking");
                }
            }
        });

        if(sharedPreferences.getString("current_temp","").equals("")){
            temperature.setText("Current Temperature: ");
        } else {
            String temp = sharedPreferences.getString("current_temp","");
            temperature.setText("Current Temperature: " + temp  + "\u2103");
        }

        if(sharedPreferences.getString("current_humid","").equals("")){
            humidity.setText("Current Humidity: ");
        } else {
            String humid = sharedPreferences.getString("current_humid","");
            humidity.setText("Current Humidity: " + humid + "%");
        }

        currTemp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString("current_temp",s.toString());
                editor.apply();
                temperature.setText("Current Temperature: " + s.toString() + "\u2103");
            }
        });
        currHumid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString("current_humid",s.toString());
                editor.apply();
                humidity.setText("Current Humidity: " + s.toString() + "%");
            }
        });

    }

    //Dispatch Touch Event
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
////      setResult(Activity.RESULT_CANCELED,intent);
//        finish();
//    }




}