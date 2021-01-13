package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.suke.widget.SwitchButton;


public class LivingRoom extends Fragment {

    private Button setting;
    private SwitchButton air_con, smart_light, smart_tv,main_door;
    private ImageView air_con_icon, smart_light_icon, smart_tv_icon,main_door_icon;
    private RelativeLayout degree, swing, timer, turbo;
    private TextView air_con_title,smart_light_title,smart_tv_title,temperature,main_door_title;
    private SharedPreferences sharedPreferences;
    
    private String TAG = LivingRoom.class.getSimpleName();

    private static LivingRoom livingRoom;

    public LivingRoom(){
        livingRoom = this;
    }

    public static LivingRoom getInstance() {
        return livingRoom;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_living_room, container, false);

        //Find view
        setting = rootView.findViewById(R.id.living_room_outlinedButton_setting);
        air_con = rootView.findViewById(R.id.living_room_air_con_switch);
        smart_light = rootView.findViewById(R.id.living_room_smart_light_switch);
        smart_tv = rootView.findViewById(R.id.living_room_smart_tv_switch);
        main_door = rootView.findViewById(R.id.living_room_main_door_switch);
        air_con_icon = rootView.findViewById(R.id.living_room_air_con_icon);
        smart_light_icon = rootView.findViewById(R.id.living_room_smart_light_icon);
        smart_tv_icon = rootView.findViewById(R.id.living_room_smart_tv_icon);
        main_door_icon = rootView.findViewById(R.id.living_room_main_door_icon);
        degree = rootView.findViewById(R.id.living_room_temperature_title);
        swing = rootView.findViewById(R.id.living_room_swing_title);
        turbo = rootView.findViewById(R.id.living_room_turbo_title);
        timer = rootView.findViewById(R.id.living_room_timer_title);
        air_con_title = rootView.findViewById(R.id.living_room_air_con_title);
        smart_light_title = rootView.findViewById(R.id.living_room_smart_light_title);
        smart_tv_title = rootView.findViewById(R.id.living_room_smart_tv_title);
        main_door_title = rootView.findViewById(R.id.living_room_main_door_title);
        temperature = rootView.findViewById(R.id.living_room_temperature);

        //pre new setting
        setAirConViewUnActive();
        setSmartLightViewUnActive();
        setSmartTvViewUnActive();
        setMainDoorViewUnActive();

        //get history setting
        sharedPreferences = getActivity().getSharedPreferences("Living_Room", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        air_con.setChecked(sharedPreferences.getBoolean("air_con_check",false));
        if(sharedPreferences.getBoolean("air_con_check", false)){
            setAirConViewActive();
        }
        smart_light.setChecked(sharedPreferences.getBoolean("smart_light_check",false));
        if(sharedPreferences.getBoolean("smart_light_check", false)){
           setSmartLightViewActive();
        }
        smart_tv.setChecked(sharedPreferences.getBoolean("smart_tv_check",false));
        if(sharedPreferences.getBoolean("smart_tv_check", false)){
            setSmartTvViewActive();
        }
        main_door.setChecked(sharedPreferences.getBoolean("main_door_check",false));
        if(sharedPreferences.getBoolean("main_door_check", false)){
            setMainDoorViewActive();
        }
        if(sharedPreferences.getString("temperature","").equals("")){
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    temperature.setText("16 \u2103");
                }
            }, 100);
        }else{
            String temp = sharedPreferences.getString("temperature","");
            temperature.setText( temp + " \u2103");
        }

        if(sharedPreferences.getBoolean("air_con_swing_check",false)){
            swing.setVisibility(View.VISIBLE);
        }else{
            swing.setVisibility(View.GONE);
        }

        if(sharedPreferences.getBoolean("air_con_turbo_check",false)){
            turbo.setVisibility(View.VISIBLE);
        }else{
            turbo.setVisibility(View.GONE);
        }

        if(sharedPreferences.getBoolean("air_con_timer_check",false)){
            swing.setVisibility(View.VISIBLE);
        }else{
            swing.setVisibility(View.GONE);
        }


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AirConditionerSetting.class);
                startActivity(intent);
            }
        });

        air_con.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
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

        smart_light.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                editor.putBoolean("smart_light_check",isChecked);
                editor.apply();
                if (isChecked){
                    setSmartLightViewActive();
                }else{
                    setSmartLightViewUnActive();
                }
            }
        });

        smart_tv.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                editor.putBoolean("smart_tv_check", isChecked);
                editor.apply();
                if (isChecked){
                    setSmartTvViewActive();
                }else{
                    setSmartTvViewUnActive();
                }
            }
        });

        main_door.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                editor.putBoolean("main_door_check", isChecked);
                editor.apply();
                if (isChecked){
                    setMainDoorViewActive();
                }else{
                    setMainDoorViewUnActive();
                }
            }
        });

        return  rootView;
    }

    private void setMainDoorViewActive() {
        main_door_icon.setAlpha(0.25f);
        main_door_icon.setVisibility(View.VISIBLE);
        main_door_icon.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);

        main_door_title.setAlpha(0.25f);
        main_door_title.setVisibility(View.VISIBLE);
        main_door_title.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);
    }

    private void setMainDoorViewUnActive(){
        main_door_icon.setAlpha(1f);
        main_door_icon.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);

        main_door_title.setAlpha(1f);
        main_door_title.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);
    }

    private void setAirConViewUnActive(){
        air_con_icon.setAlpha(1f);
        air_con_icon.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);

        air_con_title.setAlpha(1f);
        air_con_title.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);

        setting.setAlpha(1f);
        setting.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);
        setting.setClickable(false);

        degree.setAlpha(1f);
        degree.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);

        swing.setAlpha(1f);
        swing.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);

        turbo.setAlpha(1f);
        turbo.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);

        timer.setAlpha(1f);
        timer.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);
    }

    private void setAirConViewActive(){
        air_con_icon.setAlpha(0.25f);
        air_con_icon.setVisibility(View.VISIBLE);
        air_con_icon.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);

        air_con_title.setAlpha(0.25f);
        air_con_title.setVisibility(View.VISIBLE);
        air_con_title.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);


        setting.setAlpha(0.25f);
        setting.setVisibility(View.VISIBLE);
        setting.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);
        setting.setClickable(true);

        degree.setAlpha(0.25f);
        degree.setVisibility(View.VISIBLE);
        degree.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);

        if(swing.getVisibility()!=View.GONE){
            swing.setAlpha(0.25f);
            swing.setVisibility(View.VISIBLE);
            swing.animate()
                    .alpha(1f)
                    .setDuration(400)
                    .setListener(null);
        }

        if(turbo.getVisibility()!=View.GONE){
            turbo.setAlpha(0.25f);
            turbo.setVisibility(View.VISIBLE);
            turbo.animate()
                    .alpha(1f)
                    .setDuration(400)
                    .setListener(null);
        }

        if(timer.getVisibility()!=View.GONE){
            timer.setAlpha(0.25f);
            timer.setVisibility(View.VISIBLE);
            timer.animate()
                    .alpha(1f)
                    .setDuration(400)
                    .setListener(null);
        }
    }

    private void setSmartLightViewUnActive(){
        smart_light_icon.setAlpha(1f);
        smart_light_icon.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);

        smart_light_title.setAlpha(1f);
        smart_light_title.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);
    }

    private void setSmartLightViewActive(){
        smart_light_icon.setAlpha(0.25f);
        smart_light_icon.setVisibility(View.VISIBLE);
        smart_light_icon.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);

        smart_light_title.setAlpha(0.25f);
        smart_light_title.setVisibility(View.VISIBLE);
        smart_light_title.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);
    }

    private void setSmartTvViewUnActive(){
        smart_tv_icon.setAlpha(1f);
        smart_tv_icon.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);

        smart_tv_title.setAlpha(1f);
        smart_tv_title.animate()
                .alpha(0.25f)
                .setDuration(400)
                .setListener(null);
    }

    private void setSmartTvViewActive(){
        smart_tv_icon.setAlpha(0.25f);
        smart_tv_icon.setVisibility(View.VISIBLE);
        smart_tv_icon.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);

        smart_tv_title.setAlpha(0.25f);
        smart_tv_title.setVisibility(View.VISIBLE);
        smart_tv_title.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = getActivity().getSharedPreferences("Living_Room", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        air_con.setChecked(sharedPreferences.getBoolean("air_con_check",false));
        if(sharedPreferences.getBoolean("air_con_check", false)){
            setAirConViewActive();
        }else{
            setAirConViewUnActive();
        }
        String temp = sharedPreferences.getString("temperature","");
        temperature.setText( temp + " \u2103");

        if(sharedPreferences.getBoolean("air_con_swing_check",false)){
            swing.setVisibility(View.VISIBLE);
            swing.setAlpha(0.25f);
            swing.setVisibility(View.VISIBLE);
            swing.animate()
                    .alpha(1f)
                    .setDuration(400)
                    .setListener(null);
        }else{
            swing.setVisibility(View.GONE);
        }

        if(sharedPreferences.getBoolean("air_con_turbo_check",false)){
            turbo.setVisibility(View.VISIBLE);
            turbo.setAlpha(0.25f);
            turbo.setVisibility(View.VISIBLE);
            turbo.animate()
                    .alpha(1f)
                    .setDuration(400)
                    .setListener(null);
        }else{
            turbo.setVisibility(View.GONE);
        }

        if(sharedPreferences.getBoolean("air_con_timer_check",false)){
            timer.setVisibility(View.VISIBLE);
            timer.setAlpha(0.25f);
            timer.setVisibility(View.VISIBLE);
            timer.animate()
                    .alpha(1f)
                    .setDuration(400)
                    .setListener(null);
        }else{
            timer.setVisibility(View.GONE);
        }
    }

    public void refresh(){
        //refresh Logic here
        if(sharedPreferences.getBoolean("air_con_timer_check",false)){
            timer.setVisibility(View.VISIBLE);
            timer.setAlpha(0.25f);
            timer.setVisibility(View.VISIBLE);
            timer.animate()
                    .alpha(1f)
                    .setDuration(400)
                    .setListener(null);
        }else{
            timer.setVisibility(View.GONE);
        }

        if(sharedPreferences.getBoolean("air_con_turbo_check",false)){
            turbo.setVisibility(View.VISIBLE);
            turbo.setAlpha(0.25f);
            turbo.setVisibility(View.VISIBLE);
            turbo.animate()
                    .alpha(1f)
                    .setDuration(400)
                    .setListener(null);
        }else{
            turbo.setVisibility(View.GONE);
        }

        if(sharedPreferences.getBoolean("air_con_swing_check",false)){
            swing.setVisibility(View.VISIBLE);
            swing.setAlpha(0.25f);
            swing.setVisibility(View.VISIBLE);
            swing.animate()
                    .alpha(1f)
                    .setDuration(400)
                    .setListener(null);
        }else{
            swing.setVisibility(View.GONE);
        }

        if(sharedPreferences.getBoolean("air_con_check", false)){
            setAirConViewActive();
        }else{
            air_con.setChecked(false);
            setAirConViewUnActive();
        }
    }
}