package com.example.smarthome;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity{

    private TextView name,welcome_message;
    private TextView living_room, kitchen, dining_room, laundry_room, balcony, bedroom_primary, bedroom_secondary, toilet_primary, toilet_secondary, store_room_primary, store_room_secondary;
    private ScrollView scrollView;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private View view0,view1,view2,view3,view4,view5,view6,view7,view8,view9,view10;
    private ImageView menu;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private SharedPreferences sharedPreferences;


    private int NUM_PAGES = 11;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find ID
        welcome_message = findViewById(R.id.welcome_mesage);
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
        view0 = findViewById(R.id.main_living_room_view);
        view1 = findViewById(R.id.main_kitchen_view);
        view2 = findViewById(R.id.main_dining_room_view);
        view3 = findViewById(R.id.main_laundry_room_view);
        view4 = findViewById(R.id.main_balcony_view);
        view5 = findViewById(R.id.main_bedroom_primary_view);
        view6 = findViewById(R.id.main_bedroom_secondary_view);
        view7 = findViewById(R.id.main_toilet_primary_view);
        view8 = findViewById(R.id.main_toilet_secondary_view);
        view9 = findViewById(R.id.main_store_room_primary_view);
        view10 = findViewById(R.id.main_store_room_secondary_view);
        menu = findViewById(R.id.main_menu);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_item);

        //set Username
        name.setText("Thomas Oliver");
        navigationView.setItemIconTintList(null);

        //start animation
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_in_right_medium);
        Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_in_right_slow);
        welcome_message.startAnimation(animation);
        name.startAnimation(animation1);

        //Initiates View Pager
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                view0.getLayoutParams().width = living_room.getWidth();
                view0.setMinimumWidth(living_room.getWidth());
            }
        }, 100);

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
                        clearLineView();
                        living_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        living_room.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(0).getX(),0);
                        view0.getLayoutParams().width = living_room.getWidth();
                        break;
                    case 1:
                        clearTextStyle();
                        clearLineView();
                        kitchen.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        kitchen.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(1).getX(),0);
                        view1.getLayoutParams().width = kitchen.getWidth();
                        break;
                    case 2:
                        clearTextStyle();
                        clearLineView();
                        dining_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        dining_room.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(2).getX(),0);
                        view2.getLayoutParams().width = dining_room.getWidth();
                        break;
                    case 3:
                        clearTextStyle();
                        clearLineView();
                        laundry_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        laundry_room.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(3).getX(),0);
                        view3.getLayoutParams().width = laundry_room.getWidth();
                        break;
                    case 4:
                        clearTextStyle();
                        clearLineView();
                        balcony.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        balcony.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(4).getX(),0);
                        view4.getLayoutParams().width = balcony.getWidth();
                        break;
                    case 5:
                        clearTextStyle();
                        clearLineView();
                        bedroom_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        bedroom_primary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(5).getX(),0);
                        view5.getLayoutParams().width = bedroom_primary.getWidth();
                        break;
                    case 6:
                        clearTextStyle();
                        clearLineView();
                        bedroom_secondary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        bedroom_secondary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(6).getX(),0);
                        view6.getLayoutParams().width = bedroom_secondary.getWidth();
                        break;
                    case 7:
                        clearTextStyle();
                        clearLineView();
                        toilet_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        toilet_primary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(7).getX(),0);
                        view7.getLayoutParams().width = toilet_primary.getWidth();
                        break;
                    case 8:
                        clearTextStyle();
                        clearLineView();
                        toilet_secondary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        toilet_secondary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(8).getX(),0);
                        view8.getLayoutParams().width =toilet_secondary.getWidth();
                        break;
                    case 9:
                        clearTextStyle();
                        clearLineView();
                        store_room_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        store_room_primary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(9).getX(),0);
                        view9.getLayoutParams().width = store_room_primary.getWidth();
                        break;
                    case 10:
                        clearTextStyle();
                        clearLineView();
                        store_room_secondary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                        store_room_secondary.setTypeface(Typeface.DEFAULT_BOLD);
                        horizontalScrollView.smoothScrollTo((int) linearLayout.getChildAt(10).getX(),0);
                        view10.getLayoutParams().width = store_room_secondary.getWidth();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        living_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                clearLineView();
                living_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                living_room.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(0,true);
            }
        });

        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                clearLineView();
                kitchen.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                kitchen.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(1,true);
            }
        });

        dining_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                clearLineView();
                dining_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                dining_room.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(2,true);
            }
        });

        laundry_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                clearLineView();
                laundry_room.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                laundry_room.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(3,true);
            }
        });

        balcony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                clearLineView();
                balcony.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                balcony.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(4,true);
            }
        });

        bedroom_primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                clearLineView();
                bedroom_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                bedroom_primary.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(5,true);
            }
        });

        bedroom_secondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                clearLineView();
                bedroom_secondary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                bedroom_secondary.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(6,true);
            }
        });

        toilet_primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                clearLineView();
                toilet_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                toilet_primary.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(7,true);
            }
        });

        toilet_secondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                clearLineView();
                toilet_secondary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                toilet_secondary.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(8,true);
            }
        });

        store_room_primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                clearLineView();
                store_room_primary.setTextColor(getResources().getColor(R.color.primaryColor, getApplicationContext().getTheme()));
                store_room_primary.setTypeface(Typeface.DEFAULT_BOLD);
                viewPager.setCurrentItem(9,true);
            }
        });

        store_room_secondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextStyle();
                clearLineView();
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

    private void clearLineView(){
        view0.getLayoutParams().width = 0;
        view1.getLayoutParams().width = 0;
        view2.getLayoutParams().width = 0;
        view3.getLayoutParams().width = 0;
        view4.getLayoutParams().width = 0;
        view5.getLayoutParams().width = 0;
        view6.getLayoutParams().width = 0;
        view7.getLayoutParams().width = 0;
        view8.getLayoutParams().width = 0;
        view9.getLayoutParams().width = 0;
        view10.getLayoutParams().width = 0;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finishAndRemoveTask();
                    }
                }).create().show();
    }

}