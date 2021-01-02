package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView name;
    private TextView living_room, kitchen, dining_room, laundry_room, balcony, bedroom_primary, bedroom_secondary, toilet_primary, toilet_secondary, store_room_primary, store_room_secondary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find ID
        name = findViewById(R.id.main_username);
        living_room = findViewById(R.id.main_living_room);
        kitchen = findViewById(R.id.main_dining_room);
        dining_room = findViewById(R.id.main_dining_room);
        laundry_room = findViewById(R.id.main_laundry_room);
        balcony = findViewById(R.id.main_balcony);
        bedroom_primary = findViewById(R.id.main_bedroom_primary);
        bedroom_secondary = findViewById(R.id.main_bedroom_secondary);
        toilet_primary = findViewById(R.id.main_toilet_primary);
        toilet_secondary = findViewById(R.id.main_toilet_secondary);
        store_room_primary = findViewById(R.id.main_store_room_primary);
        store_room_secondary = findViewById(R.id.main_store_room_secondary);

        //set Username
        name.setText("Thomas Oliver");
    }
}