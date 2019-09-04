package com.maaz.interiar.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.maaz.interiar.R;
import com.maaz.interiar.ui.adapters.PagerViewAdapter;
import com.maaz.interiar.ui.fragments.CameraFragment;
import com.maaz.interiar.ui.fragments.CartFragment;
import com.maaz.interiar.ui.fragments.HomeFragment;
import com.maaz.interiar.ui.fragments.Photofragment;
import com.maaz.interiar.ui.fragments.Profilefragment;


public class HomeActivity extends AppCompatActivity {

    private Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logoutBtn = (Button) findViewById(R.id.logout_button);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(HomeActivity.this, SignIn_Activity.class);
                startActivity(i);
                Toast.makeText(HomeActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
            }
        });


        /*
        BottomNavigationView bottomNavigationView = findViewById(R.id.navi_bar);
        ViewPager viewPagerMain = findViewById(R.id.viewPagerMain);

        PagerViewAdapter mainPagerAdapter = new PagerViewAdapter(getSupportFragmentManager());


        mainPagerAdapter.AddFragment(new CameraFragment());//index 0
        mainPagerAdapter.AddFragment(new HomeFragment());//index 1
        mainPagerAdapter.AddFragment(new Profilefragment());//index 2

        viewPagerMain.setAdapter(mainPagerAdapter);
        //viewPagerMain.setCurrentItem(1);

        loadFragment(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.nav_cart:
                        fragment = new CartFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.nav_photo:
                        fragment = new Photofragment();
                        loadFragment(fragment);
                        break;
                    case R.id.nav_profile:
                        fragment = new CartFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.nav_cam:
                        fragment = new Photofragment();
                        loadFragment(fragment);
                        break;
                }
                return true;
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_home, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/

    }
}
