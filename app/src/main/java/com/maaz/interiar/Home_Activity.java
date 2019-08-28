package com.maaz.interiar;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.google.firebase.auth.FirebaseAuth;

public class Home_Activity extends AppCompatActivity {


    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        BottomNavigationView bottomnav = findViewById(R.id.navi_bar);
        bottomnav.setOnNavigationItemSelectedListener(navlistener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Homefragment()).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.nav_cam:
                    selectedFragment = null;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);

                    /*selectedFragment = new Camfragment();*/
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

    public boolean onTouchEvent(MotionEvent touchevent)
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

                    /*Intent intent = new Intent(MainActivity.this, SwipeLeftActivity.class);
                    Toast.makeText(this, "Swipe left", Toast.LENGTH_SHORT).show();
                    startActivity(intent);*/
                }
                else if(x1 > x2)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new ChatBotFragment()).commit();


                    /*Intent intent = new Intent(MainActivity.this, SwipeLeftActivity.class);
                    Toast.makeText(this, "Swipe left", Toast.LENGTH_SHORT).show();
                    startActivity(intent);*/
                }
                break;
        }
        return false;
    }

}
