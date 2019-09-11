package com.maaz.interiar.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.facebook.FacebookSdk;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.maaz.interiar.R;
import com.maaz.interiar.ui.Partner.PartnerSignInActivity;

import java.util.HashMap;

public class SignIn_Activity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioSigninBtn, radioSignupBtn;
    private TextView forgetPassword, divider_text, become_partner_text;
    private EditText signInEmail, signInPassword, signUpName, signUpEmail, signUpPassword;
    private Button loginButton, googleButton;
    private LoginButton facebookButton;
    private ProgressDialog loadingBar;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private boolean checkGoogleOrFbSignIn = false;
    String personId, personName, personEmail;
    Uri personPhoto;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_);

        radioGroup = findViewById(R.id.radio_group);
        radioSigninBtn = findViewById(R.id.radio_signin_button);
        radioSignupBtn = findViewById(R.id.radio_signup_button);
        forgetPassword = findViewById(R.id.forget_password);
        divider_text = findViewById(R.id.divider_txt);
        signInEmail = findViewById(R.id.signIn_email);
        signInPassword = findViewById(R.id.signIn_password);
        signUpName = findViewById(R.id.signUp_name);
        signUpEmail = findViewById(R.id.signUp_email);
        signUpPassword = findViewById(R.id.signUp_password);
        loginButton = findViewById(R.id.login_btn);
        googleButton = findViewById(R.id.google_button);
        facebookButton = findViewById(R.id.fb_button);
        loadingBar = new ProgressDialog(this);
        become_partner_text = findViewById(R.id.txt_Partner);


        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestId()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //checkingForAlreadyLoggedInUser();

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

                /*Intent intent = new Intent(SignIn_Activity.this, HomeActivity.class);
                startActivity(intent);
                finish();*/

                if (radioSigninBtn.isChecked())
                {
                    SignInUser();
                }
                else if (radioSignupBtn.isChecked())
                {
                    SignUpUser();
                }
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                loadingBar.setTitle("Logging in with Google");
                loadingBar.setMessage("Please Wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                SignInWithGoogle();
            }
        });

        callbackManager = CallbackManager.Factory.create();

        facebookButton.setReadPermissions("email", "public_profile");

        facebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel()
            {

            }

            @Override
            public void onError(FacebookException error)
            {

            }
        });

        become_partner_text.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                loadingBar.setTitle("Loading");
                loadingBar.setMessage("Please wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                Intent i = new Intent(SignIn_Activity.this, PartnerSignInActivity.class);
                startActivity(i);
                loadingBar.dismiss();
            }
        });
    }

    ////// Function for checking if user is already logged in

    private void checkingForAlreadyLoggedInUser()
    {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null || account != null)
        {
            loadingBar.setTitle("Already Logged in");
            loadingBar.setMessage("Please wait...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            Intent intent = new Intent(SignIn_Activity.this, HomeActivity.class);
            startActivity(intent);
            loadingBar.dismiss();
        }
    }

    ////// Function for signing up the new user

    private void SignUpUser()
    {
        String email = signUpEmail.getText().toString();
        String password = signUpPassword.getText().toString();
        final String name = signUpName.getText().toString();

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
        else if (TextUtils.isEmpty(name) )
        {
            Toast.makeText(this, "Please Enter your full name", Toast.LENGTH_SHORT).show();
            signUpPassword.requestFocus();
        }
        else if (password.length() < 8)
        {
            Toast.makeText(this, "Your password must be atleast of 8 characters", Toast.LENGTH_SHORT).show();
            signUpPassword.requestFocus();
        }
        else if (!email.matches(emailPattern))
        {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            signUpEmail.requestFocus();
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
                                personName = name;
                                AddUserToDatabase();
                            }
                            else
                            {
                                String Error = task.getException().getMessage();
                                Toast.makeText(SignIn_Activity.this, Error, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    ////// Function for signing in the user

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
                                //AddUserToDatabase();
                                Intent intent = new Intent(SignIn_Activity.this, HomeActivity.class);
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

    ////// Functions for google sign in

    private void SignInWithGoogle()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        loadingBar.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);

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
                checkGoogleOrFbSignIn = true;
                loadingBar.dismiss();
                AddUserToDatabase();
            }
        } catch (ApiException e) {
            Toast.makeText(SignIn_Activity.this, "signInResult:failed code=" + e.getStatusCode(), Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();
        }
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            AddUserToDatabase();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignIn_Activity.this, "signInResult:failed code=" + task.getException(), Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    ////// Function for adding user to database

    private void AddUserToDatabase()
    {
        HashMap<Object,String> userdata = new HashMap<>();

        if (checkGoogleOrFbSignIn == true)
        {
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(SignIn_Activity.this);
            personName = acct.getDisplayName();
            personEmail = acct.getEmail();
            personId = acct.getId();
            personPhoto = acct.getPhotoUrl();

        }
        else
        {
            FirebaseUser User = mAuth.getCurrentUser();
            personEmail = User.getEmail();
            personId = User.getUid();
            personPhoto = null;
        }

        userdata.put("Name",personName);
        userdata.put("Email",personEmail);
        userdata.put("UserID",personId);

//        final DatabaseReference RootRef;
//        RootRef = FirebaseDatabase.getInstance().getReference();

        firebaseFirestore.collection("Users")
                .document(personId)
                .set(userdata)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(SignIn_Activity.this, HomeActivity.class);
                        startActivity(intent);
                        loadingBar.dismiss();
                    }
                });

/*                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(SignIn_Activity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            String Error = task.getException().getMessage();
                            Toast.makeText(SignIn_Activity.this, Error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

        //RootRef.child("Users").child(personId).child("email").setValue(personEmail);
        //RootRef.child("Users").child(personId).child("picture").setValue(personPhoto);
    }
}
