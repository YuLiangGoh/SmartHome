package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    private TextView name;
    private TextView living_room, kitchen, dining_room, laundry_room, balcony, bedroom_primary, bedroom_secondary, toilet_primary, toilet_secondary, store_room_primary, store_room_secondary;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private int NUM_PAGES = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find ID
        name = findViewById(R.id.main_username);
        living_room = findViewById(R.id.main_living_room);
        kitchen = findViewById(R.id.main_kitchen);
        dining_room = findViewById(R.id.main_dining_room);
        laundry_room = findViewById(R.id.main_laundry_room);
        balcony = findViewById(R.id.main_balcony);
        bedroom_primary = findViewById(R.id.main_bedroom_primary);
        bedroom_secondary = findViewById(R.id.main_bedroom_secondary);
        toilet_primary = findViewById(R.id.main_toilet_primary);
        toilet_secondary = findViewById(R.id.main_toilet_secondary);
        store_room_primary = findViewById(R.id.main_store_room_primary);
        store_room_secondary = findViewById(R.id.main_store_room_secondary);
        viewPager = findViewById(R.id.main_view_pager);

        //set Username
        name.setText("Thomas Oliver");

        //Initiates View Pager
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        living_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                living_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                viewPager.setCurrentItem(0,true);
            }
        });

        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                kitchen.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                viewPager.setCurrentItem(1,true);
            }
        });

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new Living_Room();
            }
            if(position == 1){
                return new Kitchen();
            }
            return new Living_Room();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private void clearTextStyle(){
        living_room.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        kitchen.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        dining_room.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        laundry_room.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        balcony.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        bedroom_primary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        bedroom_secondary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        toilet_primary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        toilet_secondary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        store_room_primary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        store_room_secondary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
    }
}