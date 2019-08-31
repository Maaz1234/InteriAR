package com.maaz.interiar.ui.activities;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.maaz.interiar.R;

public class MainActivity extends AppCompatActivity {

    private static int timeout=3000; //splash timeout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(MainActivity.this,SignIn_Activity.class);
                startActivity(intent);
                finish();
            }
        },timeout);
    }
}
