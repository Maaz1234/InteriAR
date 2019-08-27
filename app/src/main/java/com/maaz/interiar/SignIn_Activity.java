package com.maaz.interiar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Region;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private Button loginButton, googleButton, facebookButton;
    private ProgressDialog loadingBar;
    //private String parentDBName = "Users";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

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
        googleButton = findViewById(R.id.google_button);
        facebookButton = findViewById(R.id.fb_button);
        loadingBar = new ProgressDialog(this);
        //Paper.init(this);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestId()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null || account != null)
        {
            loadingBar.setTitle("Already Logged in");
            loadingBar.setMessage("Please wait...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            Intent intent = new Intent(SignIn_Activity.this, Home_Activity.class);
            startActivity(intent);
            loadingBar.dismiss();
        }

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
                    SignUpUser();
                }
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInWithGoogle();
            }
        });
    }

    private void SignUpUser()
    {
        String email = signUpEmail.getText().toString();
        String password = signUpPassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            signUpEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            signUpPassword.requestFocus();
        }
        else
        {
            loadingBar.setTitle("Creating Account");
            loadingBar.setMessage("Please wait. While we are checking credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignIn_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Intent intent = new Intent(SignIn_Activity.this, Home_Activity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(SignIn_Activity.this, "SignUp UnSuccessful, EmailID Already Exists", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    private void SignInUser()
    {
        String email = signInEmail.getText().toString();
        String password = signInPassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            signInEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            signInPassword.requestFocus();
        }
        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait. While we are checking credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignIn_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Intent intent = new Intent(SignIn_Activity.this, Home_Activity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(SignIn_Activity.this, "Account with this EmailID does not exists", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    private void SignInWithGoogle()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                signInEmail.setText(account.getEmail());
                Intent intent = new Intent(SignIn_Activity.this, Home_Activity.class);
                startActivity(intent);
            }
        } catch (ApiException e) {
            Toast.makeText(SignIn_Activity.this, "signInResult:failed code=" + e.getStatusCode(), Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();
        }
    }
}
