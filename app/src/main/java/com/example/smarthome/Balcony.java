package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

public class Balcony extends Fragment {

    private TextView temperature_desc, humidity_desc;
    private SwitchButton balcony_smart_light_switch, rain_protection_switch;
    private SharedPreferences sharedPreferences , sharedPreferencesSensors;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_balcony, container, false);

        temperature_desc = rootView.findViewById(R.id.temperature_desc);
        humidity_desc = rootView.findViewById(R.id.humidity_desc);
        balcony_smart_light_switch = rootView.findViewById(R.id.balcony_smart_light_switch);
        rain_protection_switch = rootView.findViewById(R.id.rain_protection_switch);

        sharedPreferences = getActivity().getSharedPreferences("balcony", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        sharedPreferencesSensors = getActivity().getSharedPreferences("sensors", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferencesSensors.edit();

        if(sharedPreferencesSensors.getString("current_temp","").equals("")){
            temperature_desc.setText("Current Temperature: ");
            editor1.putString("current_temp","24");
            editor1.apply();
        } else {
            String temp = sharedPreferencesSensors.getString("current_temp","");
            temperature_desc.setText("Current Temperature: " + temp  + "\u2103");
        }

        if(sharedPreferencesSensors.getString("current_humid","").equals("")){
            humidity_desc.setText("Current Humidity: ");
            editor1.putString("current_humid","20");
            editor1.apply();
        } else {
            String humid = sharedPreferencesSensors.getString("current_humid","");
            humidity_desc.setText("Current Humidity: " + humid + "%");
        }

        if(Integer.parseInt(sharedPreferencesSensors.getString("current_humid",""))>=50
                && Integer.parseInt(sharedPreferencesSensors.getString("current_temp","0"))<=24){
            rain_protection_switch.setChecked(true);
        } else{
            rain_protection_switch.setChecked(false);
        }

        return  rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        sharedPreferences = getActivity().getSharedPreferences("balcony", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        sharedPreferencesSensors = getActivity().getSharedPreferences("sensors", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferencesSensors.edit();

        if(sharedPreferencesSensors.getString("current_temp","").equals("")){
            temperature_desc.setText("Current Temperature: ");
            editor1.putString("current_temp","24");
            editor1.apply();
        } else {
            String temp = sharedPreferencesSensors.getString("current_temp","");
            temperature_desc.setText("Current Temperature: " + temp  + "\u2103");
        }
        if(sharedPreferencesSensors.getString("current_humid","").equals("")){
            humidity_desc.setText("Current Humidity: ");
            editor1.putString("current_humid","20");
            editor1.apply();
        } else {
            String humid = sharedPreferencesSensors.getString("current_humid","");
            humidity_desc.setText("Current Humidity: " + humid + "%");
        }

        if(Integer.parseInt(sharedPreferencesSensors.getString("current_humid",""))>=50
                && Integer.parseInt(sharedPreferencesSensors.getString("current_temp",""))<=24){
            rain_protection_switch.setChecked(true);
        } else{
            rain_protection_switch.setChecked(false);
        }


    }
}