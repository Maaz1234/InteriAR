package com.maaz.interiar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Partner_SignIn_Activity extends AppCompatActivity {


    private Button partnerChangeToSignupActivity_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner__sign_in_);


        partnerChangeToSignupActivity_btn = (Button) findViewById(R.id.partner_change_to_signup_button);
        partnerChangeToSignupActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Partner_SignIn_Activity.this,Partner_SignUp_Activity.class);
                startActivity(intent);
            }
        });


        /*loginButton = (Button) findViewById(R.id.login_btn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(com.maaz.interiar.SignIn_Activity.this,Home_Activity.class);
                startActivity(intent);
                finish();
            }
        });*/
    }
}
