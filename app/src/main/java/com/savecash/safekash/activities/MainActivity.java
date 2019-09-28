package com.savecash.safekash.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.savecash.safekash.R;
import com.savecash.safekash.utils.ViewPagerAdapter;
import com.savecash.safekash.fragments.SignUpFragment;
import com.savecash.safekash.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {
private TabLayout tabLayout;
private AppBarLayout appBarLayout;
private ViewPager viewPager;
private static final String TAG = "MainActivity";
private AdView mAdView;
private FirebaseAuth  mAuth;

EditText LoginAccount;
EditText LoginPassword;

EditText SignUpName;
EditText SignUpPassword;
EditText SignUpAccount;
EditText LoginEmail;
EditText SignUpEmail;
EditText SignUpConfrimPassword;
ProgressBar loginProBar;
TextView LoginForgottenPin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar_layout);
        viewPager=(ViewPager)findViewById(R.id.viewer_page);

        mAuth=FirebaseAuth.getInstance();

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new LoginFragment(),"Login");
        adapter.AddFragment(new SignUpFragment(),"SignUp");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        MobileAds.initialize(this, "ca-app-pub-9190831403912836~7358674798");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }



    public void register(View view) {
        LoginEmail = findViewById(R.id.loginEdAccount);
        LoginPassword = findViewById(R.id.loginEdPass);
        loginProBar=findViewById(R.id.loginProBar);


        String account = LoginEmail.getText().toString();
        String password = LoginPassword.getText().toString();


       /* if (account.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(account).matches()) {
            LoginAccount.setError("Please enter a valid email");
            LoginAccount.requestFocus();
            return;

        }*/
        if (password.isEmpty() || password.length() < 6) {
            LoginPassword.setError("Enter a password with six as the minimum character");
            LoginPassword.requestFocus();
            return;

        }
        loginProBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(account,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                loginProBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {





                }
                else { Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();}
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null)
        {



        }
    }

    public void signUp(View view)
    {
       SignUpName=findViewById(R.id.ed_sign_up_userName);
       SignUpEmail=findViewById(R.id.ed_sign_up_Email);
       SignUpAccount=findViewById(R.id.ed_sign_up_phone);
       SignUpPassword=findViewById(R.id.ed_sign_up_Password);
       SignUpConfrimPassword=findViewById(R.id.ed_sign_up_ConfrimPassword);

       String name=SignUpName.getText().toString().trim();
        String email=SignUpEmail.getText().toString().trim();
        String account=SignUpAccount.getText().toString().trim();
        String pass=SignUpPassword.getText().toString().trim();
        String pass2=SignUpConfrimPassword.getText().toString().trim();

        boolean check=false;
         if(pass.matches(pass2))
          check=true;

         if(name.isEmpty()||name.length()<4)
        {
            SignUpName.setError("Enter a correct name");
            SignUpName.requestFocus();
            return;
        }
        if(email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            SignUpEmail.setError("Enter a valid email");
            SignUpEmail.requestFocus();
            return;

        }

        if(account.isEmpty()||account.length()<10 ||!Patterns.PHONE.matcher(account).matches())
        {
           SignUpAccount.setError("Please enter a valid number");
           SignUpAccount.requestFocus();
           return;

        }

        if(pass.isEmpty()|| pass.length()<6)
        {
            SignUpPassword.setError("Enter a password with six as minimum character");
            SignUpPassword.requestFocus();
            return;

        }

        if(!check)
        {
           SignUpConfrimPassword.setError(" The password does not match");
           SignUpConfrimPassword.requestFocus();
           return;

        }
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent intent=new Intent(getApplicationContext(), DashBoardActivity.class);

                    startActivity(intent);

                }
                else
                    {
                        if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        {
                            Toast.makeText(MainActivity.this, "This email haas been used", Toast.LENGTH_SHORT).show();

                        }

                }
            }
        });

        Intent intent=new Intent(getApplicationContext(), Verification.class);
        startActivity(intent);

    }

    public void nextActivity()
    {
        Intent intent=new Intent(this, DashBoardActivity.class);
        startActivity(intent);

    }


}
