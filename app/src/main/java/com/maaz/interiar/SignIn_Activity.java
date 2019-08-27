package com.maaz.interiar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maaz.interiar.Model.Users;
import com.maaz.interiar.Prevalent.Prevalent;

import io.paperdb.Paper;

public class SignIn_Activity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioSigninBtn, radioSignupBtn;
    private TextView forgetPassword, divider_text;
    private EditText signInEmail, signInPassword, signUpName, signUpEmail, signUpPassword;
    private Button loginButton;
    private ProgressDialog loadingBar;
    private String parentDBName = "Users";



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
        loadingBar = new ProgressDialog(this);
        Paper.init(this);


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
                if (radioSigninBtn.isChecked())
                {
                    SignInUser();
                    //Intent intent = new Intent(SignIn_Activity.this, Home_Activity.class);
                    //startActivity(intent);
                    //finish();
                }
                else if (radioSignupBtn.isChecked())
                {

                }
            }
        });
    }

    private void SignInUser()
    {
        String email = signInEmail.getText().toString();
        String password = signInPassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please Write Your Email", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please Write Your Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait. While we are checking credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(email, password);
        }
    }

    private void AllowAccessToAccount(final String username, final String password)
    {
        //FirebaseApp.initializeApp(this);
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDBName).child(username).exists())
                {
                    Users userData = dataSnapshot.child(parentDBName).child(username).getValue(Users.class);

                    if(userData.getUsername().equals(username))
                    {
                        if (userData.getPassword().equals(password))
                        {
                            if(parentDBName.equals("Admins")) {
                                Paper.book().write(Prevalent.UserEmailKey, username);
                                Paper.book().write(Prevalent.UserPasswordKey, password);

                                Toast.makeText(SignIn_Activity.this, "Welcome Admin you are logged in Successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(SignIn_Activity.this, Home_Activity.class);
                                startActivity(intent);
                            }
                            else if (parentDBName.equals("Users"))
                            {
                                Paper.book().write(Prevalent.UserEmailKey, username);
                                Paper.book().write(Prevalent.UserPasswordKey, password);

                                Toast.makeText(SignIn_Activity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(SignIn_Activity.this, Home_Activity.class);
                                Prevalent.currentOnlineUser = userData;
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            Toast.makeText(SignIn_Activity.this,"Password is incorrect.",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                }
                else
                {
                    Toast.makeText(SignIn_Activity.this, "Account with this number does not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });

    }

}
