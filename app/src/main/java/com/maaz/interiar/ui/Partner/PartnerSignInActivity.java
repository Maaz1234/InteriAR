package com.maaz.interiar.ui.Partner;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.maaz.interiar.R;

public class PartnerSignInActivity extends AppCompatActivity {


    private Button partnerChangeToSignupActivity_btn, partnerLoginBtn;
    private EditText signInEmail, signInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_sign_in);

        signInEmail = findViewById(R.id.partner_signIn_email);
        signInPassword = findViewById(R.id.partner_signIn_password);
        partnerChangeToSignupActivity_btn = findViewById(R.id.partner_change_to_signup_button);
        partnerLoginBtn = findViewById(R.id.partner_login_btn);

        partnerChangeToSignupActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PartnerSignInActivity.this, PartnerSignUpActivity.class);
                startActivity(intent);
            }
        });

        partnerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PartnerSignInActivity.this, PartnerHomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
