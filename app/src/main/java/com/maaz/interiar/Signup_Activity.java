package com.maaz.interiar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Signup_Activity extends AppCompatActivity {

    private Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);

        aSwitch= (Switch) findViewById(R.id.switch_button);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked==false)
                {
                    Intent intent = new Intent(Signup_Activity.this,Login_Activity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });

    }
}
