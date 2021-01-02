package com.example.smarthome;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

        //set view pager on page change listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this, "Selected page position: " + position, Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        clearTextStyle();
                        living_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        living_room.setTypeface(Typeface.DEFAULT_BOLD);
                        break;
                    case 1:
                        clearTextStyle();
                        kitchen.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        kitchen.setTypeface(Typeface.DEFAULT_BOLD);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        living_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                living_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                living_room.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(0,true);
            }
        });

        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                kitchen.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                kitchen.setTypeface(Typeface.DEFAULT_BOLD);
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
            switch (position){
                case 0:
                    return new LivingRoom();
                case 1:
                    return new Kitchen();
                default:
                    return new LivingRoom();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private void clearTextStyle(){
        living_room.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        living_room.setTypeface(Typeface.DEFAULT);
        kitchen.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        kitchen.setTypeface(Typeface.DEFAULT);
        dining_room.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        dining_room.setTypeface(Typeface.DEFAULT);
        laundry_room.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        laundry_room.setTypeface(Typeface.DEFAULT);
        balcony.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        balcony.setTypeface(Typeface.DEFAULT);
        bedroom_primary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        bedroom_primary.setTypeface(Typeface.DEFAULT);
        bedroom_secondary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        bedroom_secondary.setTypeface(Typeface.DEFAULT);
        toilet_primary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        toilet_primary.setTypeface(Typeface.DEFAULT);
        toilet_secondary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        toilet_secondary.setTypeface(Typeface.DEFAULT);
        store_room_primary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        store_room_primary.setTypeface(Typeface.DEFAULT);
        store_room_secondary.setTextColor(getResources().getColor(R.color.grey, this.getTheme()));
        store_room_secondary.setTypeface(Typeface.DEFAULT);
    }
}