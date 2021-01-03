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
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity{

    private TextView name;
    private TextView living_room, kitchen, dining_room, laundry_room, balcony, bedroom_primary, bedroom_secondary, toilet_primary, toilet_secondary, store_room_primary, store_room_secondary;
    private ScrollView scrollView;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private View view;

    private int NUM_PAGES = 11;
    private String TAG = MainActivity.class.getSimpleName();

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
        scrollView = findViewById(R.id.main_scroll_view);
        horizontalScrollView = findViewById(R.id.main_horizontal_scroll_view);
        horizontalScrollView.setSmoothScrollingEnabled(true);
        linearLayout = findViewById(R.id.main_item_linear_layout);
        viewPager = findViewById(R.id.main_view_pager);
        view = findViewById(R.id.main_view);

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
                //Toast.makeText(MainActivity.this, "Selected page position: " + position, Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        clearTextStyle();
                        living_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        living_room.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(0).getX()-10,0);
                        view.getLayoutParams().width = linearLayout.getChildAt(0).getWidth();
                        break;
                    case 1:
                        clearTextStyle();
                        kitchen.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        kitchen.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(1).getX()-10,0);
                        view.getLayoutParams().width = linearLayout.getChildAt(1).getWidth();
                        break;
                    case 2:
                        clearTextStyle();
                        dining_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        dining_room.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(2).getX()-10,0);
                        view.getLayoutParams().width = linearLayout.getChildAt(2).getWidth();
                        break;
                    case 3:
                        clearTextStyle();
                        laundry_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        laundry_room.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(3).getX()-10,0);
                        view.getLayoutParams().width = linearLayout.getChildAt(3).getWidth();
                        break;
                    case 4:
                        clearTextStyle();
                        balcony.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        balcony.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(4).getX()-10,0);
                        view.getLayoutParams().width = linearLayout.getChildAt(4).getWidth();
                        break;
                    case 5:
                        clearTextStyle();
                        bedroom_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        bedroom_primary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(5).getX()-10,0);
                        view.getLayoutParams().width = linearLayout.getChildAt(5).getWidth();
                        break;
                    case 6:
                        clearTextStyle();
                        bedroom_secondary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        bedroom_secondary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(6).getX()-10,0);
                        view.getLayoutParams().width = linearLayout.getChildAt(6).getWidth();
                        break;
                    case 7:
                        clearTextStyle();
                        toilet_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        toilet_primary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(7).getX()-10,0);
                        view.getLayoutParams().width = linearLayout.getChildAt(7).getWidth();
                        break;
                    case 8:
                        clearTextStyle();
                        toilet_secondary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        toilet_secondary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(8).getX()-10,0);
                        view.getLayoutParams().width = linearLayout.getChildAt(8).getWidth();
                        break;
                    case 9:
                        clearTextStyle();
                        store_room_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        store_room_primary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(9).getX()-10,0);
                        view.getLayoutParams().width = linearLayout.getChildAt(9).getWidth();
                        break;
                    case 10:
                        clearTextStyle();
                        store_room_secondary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        store_room_secondary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(10).getX()-10,0);
                        view.getLayoutParams().width = linearLayout.getChildAt(10).getWidth();
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

        dining_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                dining_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                dining_room.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(2,true);
            }
        });

        laundry_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                laundry_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                laundry_room.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(3,true);
            }
        });

        balcony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                balcony.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                balcony.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(4,true);
            }
        });

        bedroom_primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                bedroom_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                bedroom_primary.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(5,true);
            }
        });

        bedroom_secondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                bedroom_secondary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                bedroom_secondary.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(6,true);
            }
        });

        toilet_primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                toilet_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                toilet_primary.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(7,true);
            }
        });

        toilet_secondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                toilet_secondary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                toilet_secondary.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(8,true);
            }
        });

        store_room_primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                store_room_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                store_room_primary.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(9,true);
            }
        });

        store_room_secondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                store_room_secondary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                store_room_secondary.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(10,true);
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
                case 2:
                    return new DiningRoom();
                case 3:
                    return new LaundryRoom();
                case 4:
                    return new Balcony();
                case 5:
                    return new BedroomPrimary();
                case 6:
                    return new BedroomSecondary();
                case 7:
                    return new ToiletPrimary();
                case 8:
                    return new ToiletSecondary();
                case 9:
                    return new StoreRoomPrimary();
                case 10:
                    return new StoreRoomSecondary();
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