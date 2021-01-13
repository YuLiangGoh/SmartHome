package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

public class LaundryRoom extends Fragment {

    private SwitchButton laundrySwitch;
    private TextView timer;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_laundry_room, container, false);

        laundrySwitch = rootView.findViewById(R.id.laundry_switch);
        timer = rootView.findViewById(R.id.timer);

        //sharedPreferences = getActivity().getSharedPreferences("Laundry_Room", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPreferences.edit();

        laundrySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {

            CountDownTimer CDTimer = new CountDownTimer(3000, 1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    double time_left = millisUntilFinished/1000+1;
                    timer.setText("remaining time: " + time_left);
                }

                @Override
                public void onFinish() {
                    //editor.putBoolean("laundry_ready", laundry_done);
                    //editor.apply();
                    timer.setText("laundry is ready!");
                }
            };

            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if(isChecked){
                    CDTimer.start();
                }
                else{
                    CDTimer.cancel();
                    timer.setText("");
                }
            }
        });

        return  rootView;
    }
}