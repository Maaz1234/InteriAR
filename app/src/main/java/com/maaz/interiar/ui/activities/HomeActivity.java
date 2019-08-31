package com.maaz.interiar.ui.activities;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.maaz.interiar.R;
import com.maaz.interiar.ui.adapters.PagerViewAdapter;
import com.maaz.interiar.ui.fragments.CameraFragment;
import com.maaz.interiar.ui.fragments.HomeFragment;
import com.maaz.interiar.ui.fragments.Profilefragment;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPagerMain = findViewById(R.id.viewPagerMain);

        PagerViewAdapter mainPagerAdapter = new PagerViewAdapter(getSupportFragmentManager());


        mainPagerAdapter.AddFragment(new CameraFragment());//index 0
        mainPagerAdapter.AddFragment(new HomeFragment());//index 1
        mainPagerAdapter.AddFragment(new Profilefragment());//index 2

        viewPagerMain.setAdapter(mainPagerAdapter);
        viewPagerMain.setCurrentItem(1);
    }

}
