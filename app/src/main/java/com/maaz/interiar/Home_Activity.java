package com.maaz.interiar;

import android.content.Intent;
import android.graphics.Camera;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Switch;
import android.widget.TableLayout;

public class Home_Activity extends AppCompatActivity {

    float x1, x2, y1, y2;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerViewAdapter adapter;

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        BottomNavigationView bottomnav = findViewById(R.id.navi_bar);
        bottomnav.setOnNavigationItemSelectedListener(navlistener);
        bottomnav.setSelectedItemId(R.id.nav_home); // setting home item as default selection

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Homefragment()).commit();

        viewPager = (ViewPager) findViewById(R.id.fragment_container_home);

        adapterViewPager = new PagerViewAdapter (getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(1);


        /*        SetupTabLayout(); */

       /* tabLayout = (TabLayout) findViewById(R.id.tablayout_home);
        viewPager = (ViewPager) findViewById(R.id.fragment_container_home);
*//*
        adapter = new PagerViewAdapter(getActivity().getSupportFragmentManager());
*//*
        adapter = new PagerViewAdapter (getSupportFragmentManager());

        //Add fragment Here

        adapter.AddFragment(new Camfragment());//index 0
        adapter.AddFragment(new Homefragment());//index 1
        adapter.AddFragment(new Assistant_Fragment());//index 2
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_photo_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_interiar_logo_with_transparent_background_without_name_svg);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_assistant_black_24dp);*/

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);*/
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter
    {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return Camfragment.newInstance();

                case 1:
                    return Homefragment.newInstance();

                case 2:
                    return Assistant_Fragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount()
        {
            return 3;
        }
    }


    /* Return the current tab number
            0 = Camfragment
            1 = Homefragment
            2 = Assistant_Fragment
    */
    public int getCurrentTabNumber()
    {
        return viewPager.getCurrentItem();
    }

  /*  private void SetupViewPager()
    {

        tabLayout = (TabLayout) findViewById(R.id.tablayout_home);
        viewPager = (ViewPager) findViewById(R.id.fragment_container_home);
*//*
        adapter = new PagerViewAdapter(getActivity().getSupportFragmentManager());
*//*
                adapter = new PagerViewAdapter (getSupportFragmentManager());

        //Add fragment Here

        adapter.AddFragment(new Camfragment());//index 0
        adapter.AddFragment(new Homefragment());//index 1
        adapter.AddFragment(new Assistant_Fragment());//index 2
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_photo_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_interiar_logo_with_transparent_background_without_name_svg);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_assistant_black_24dp);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }*/

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.nav_cam:
                    /*selectedFragment = null;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);*/

                    selectedFragment = new Camfragment();
                    break;

                case R.id.nav_photo:
                    selectedFragment = new Photofragment();
                    break;

                case R.id.nav_home:
                    selectedFragment = new Homefragment();
                    break;

                case R.id.nav_cart:
                    selectedFragment = new cartfragment();
                    break;

                case R.id.nav_profile:
                    selectedFragment = new Profilefragment();
                    break;
            }

            if (selectedFragment != null)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
            }
            /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();*/

            return true;
        }
    };

    /*public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;

            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if(x1 < x2)
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);

                    if(x2 > x1)
                    {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Homefragment()).commit();
                    }

                   *//* Intent intent = new Intent(MainActivity.this, SwipeLeftActivity.class);
                    Toast.makeText(this, "Swipe left", Toast.LENGTH_SHORT).show();
                    startActivity(intent);*//*
                }
                else if(x1 > x2)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new Assistant_Fragment()).commit();


                   *//* Intent intent = new Intent(MainActivity.this, SwipeLeftActivity.class);
                    Toast.makeText(this, "Swipe left", Toast.LENGTH_SHORT).show();
                    startActivity(intent);*//*
                }
                break;
        }
        return false;
    }*/



  /*  *//*Responsible for adding the 3 tabs: Camera, Home, Assistant*//*
    private void setupViewPager()
    {
        PagerViewAdapter adapter1 = new PagerViewAdapter(getSupportFragmentManager());
        adapter1.AddFragment(new Camera_Fragment());//index 0
        adapter1.AddFragment(new Homefragment());//index 1
        adapter1.AddFragment(new Assistant_Fragment());//index 2
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_home);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_photo_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_interiar_logo_with_transparent_background_without_name_svg);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_assistant_black_24dp);

    }*/
}
