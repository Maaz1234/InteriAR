package com.maaz.interiar.ui.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.maaz.interiar.R;

public class PartnerSignInActivity extends AppCompatActivity {


    private Button partnerChangeToSignupActivity_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_sign_in);


        partnerChangeToSignupActivity_btn = (Button) findViewById(R.id.partner_change_to_signup_button);
        partnerChangeToSignupActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PartnerSignInActivity.this, PartnerSignUpActivity.class);
                startActivity(intent);
            }
        });


        /*loginButton = (Button) findViewById(R.id.login_btn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(com.maaz.interiar.ui.activities.SignIn_Activity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });*/
    }
}
