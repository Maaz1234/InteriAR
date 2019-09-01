package com.maaz.interiar.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.maaz.interiar.R;
import com.maaz.interiar.ui.adapters.PagerViewAdapter;
import com.maaz.interiar.ui.fragments.BrandsFragment;
import com.maaz.interiar.ui.fragments.CameraFragment;
import com.maaz.interiar.ui.fragments.CartFragment;
import com.maaz.interiar.ui.fragments.HomeFragment;
import com.maaz.interiar.ui.fragments.Photofragment;
import com.maaz.interiar.ui.fragments.Profilefragment;


public class HomeActivity extends AppCompatActivity {

    Fragment currentFragment;
    ViewPager viewPagerMain;
    PagerViewAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //currentFragment = new HomeFragment();

        final BottomNavigationView bottomNavigationView = findViewById(R.id.navi_bar);

        viewPagerMain = findViewById(R.id.viewPagerMain);

        mainPagerAdapter = new PagerViewAdapter(getSupportFragmentManager());

        mainPagerAdapter.AddFragment(new CameraFragment());//index 0
        mainPagerAdapter.AddFragment(new HomeFragment());//index 1
        mainPagerAdapter.AddFragment(new Profilefragment());//index 2

        viewPagerMain.setAdapter(mainPagerAdapter);
        viewPagerMain.setCurrentItem(1);

        currentFragment = new HomeFragment();
        loadFragment(new HomeFragment());
        bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                currentFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        loadFragment(fragment);
                        break;
                    case R.id.nav_cart:
                        fragment = new CartFragment();
                        bottomNavigationView.getMenu().findItem(R.id.nav_cart).setChecked(true);
                        loadFragment(fragment);
                        break;
                    case R.id.nav_photo:
                        fragment = new Photofragment();
                        bottomNavigationView.getMenu().findItem(R.id.nav_photo).setChecked(true);
                        loadFragment(fragment);
                        break;
                    case R.id.nav_profile:
                        fragment = new Profilefragment();
                        bottomNavigationView.getMenu().findItem(R.id.nav_profile).setChecked(true);
                        loadFragment(fragment);
                        break;
                    case R.id.nav_cam:
                        fragment = new BrandsFragment();
                        bottomNavigationView.getMenu().findItem(R.id.nav_cam).setChecked(true);
                        loadFragment(fragment);
                        break;
                }
                return false;
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_home, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
