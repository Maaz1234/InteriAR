package com.maaz.interiar.ui.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.maaz.interiar.R;

public class SignIn_Activity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioSigninBtn, radioSignupBtn;
    private TextView forgetPassword, divider_text;
    private EditText signInEmail, signInPassword, signUpName, signUpEmail, signUpPassword;
    private Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioSigninBtn = (RadioButton) findViewById(R.id.radio_signin_button);
        radioSignupBtn = (RadioButton) findViewById(R.id.radio_signup_button);
        forgetPassword = (TextView) findViewById(R.id.forget_password);
        divider_text = (TextView) findViewById(R.id.divider_txt);
        signInEmail = (EditText) findViewById(R.id.signIn_email);
        signInPassword = (EditText) findViewById(R.id.signIn_password);
        signUpName = (EditText) findViewById(R.id.signUp_name);
        signUpEmail = (EditText) findViewById(R.id.signUp_email);
        signUpPassword = (EditText) findViewById(R.id.signUp_password);

        loginButton = (Button) findViewById(R.id.login_btn);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                if(radioSigninBtn.isChecked())
                {
                    signUpName.setVisibility(View.INVISIBLE);
                    signUpEmail.setVisibility(View.INVISIBLE);
                    signUpPassword.setVisibility(View.INVISIBLE);
                    signInEmail.setVisibility(View.VISIBLE);
                    signInPassword.setVisibility(View.VISIBLE);
                    forgetPassword.setVisibility(View.VISIBLE);
                    divider_text.setText("Or SignIn with");
                }
                else
                {
                    signUpName.setVisibility(View.VISIBLE);
                    signUpEmail.setVisibility(View.VISIBLE);
                    signUpPassword.setVisibility(View.VISIBLE);
                    signInEmail.setVisibility(View.INVISIBLE);
                    signInPassword.setVisibility(View.INVISIBLE);
                    forgetPassword.setVisibility(View.INVISIBLE);
                    divider_text.setText("Or SignUp with");
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SignIn_Activity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
