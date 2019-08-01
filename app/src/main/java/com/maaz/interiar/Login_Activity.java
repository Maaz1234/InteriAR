package com.maaz.interiar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Login_Activity extends AppCompatActivity {

    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        aSwitch= (Switch) findViewById(R.id.switch_button);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked==true)
                {
                    /*Intent intent = new Intent(Login_Activity.this,Signup_Activity.class);
                    startActivity(intent);
                    finish();*/
                }

            }
        });
    }
}
