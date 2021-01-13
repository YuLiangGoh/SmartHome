package com.example.smarthome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    SharedPreferences sharedPreferences;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification("Aircon","Smart home have just turn off the air conditioner for you.");
        notificationHelper.getManager().notify(1,nb.build());

        sharedPreferences = context.getSharedPreferences("Living_Room", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("air_con_timer_check", false);
        editor.putBoolean("air_con_check",false);
        editor.putBoolean("air_con_swing_check",false);
        editor.putBoolean("air_con_turbo_check",false);
        editor.apply();

        if(AirConditionerSetting.getInstance()!=null){
            AirConditionerSetting.getInstance().refresh();
        }

        if(LivingRoom.getInstance()!=null){
            LivingRoom.getInstance().refresh();
        }
    }
}
