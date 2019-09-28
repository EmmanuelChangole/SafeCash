package com.savecash.safekash.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.savecash.safekash.R;

import java.util.concurrent.TimeUnit;

public class ConfirmCode extends AppCompatActivity {
    private String verificationId;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_code);

        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        editText=(EditText)findViewById(R.id.editTextCode);

        mAuth=FirebaseAuth.getInstance();
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        sendVerificationCode(phoneNumber);
        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String code=editText.getText().toString().trim();
                if(code.isEmpty()||code.length()<6)
                {
                    editText.setError("Enter a valid code");
                    editText.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        });

    }
    private void verifyCode(String code)
    {
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationId,code);
        signInWithCredential(credential);


    }

    private void signInWithCredential(PhoneAuthCredential credential)
    {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent=new Intent(ConfirmCode.this, DashBoardActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(ConfirmCode.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    private void sendVerificationCode(String number)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    verificationId=s;
                }

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
                {
                    String code=phoneAuthCredential.getSmsCode();
                    if(code!=null)
                    {
                        progressBar.setVisibility(View.VISIBLE);
                        verifyCode(code);
                    }

                }

                @Override
                public void onVerificationFailed(FirebaseException e)
                {
                    Toast.makeText(ConfirmCode.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();

                }
            };

}
