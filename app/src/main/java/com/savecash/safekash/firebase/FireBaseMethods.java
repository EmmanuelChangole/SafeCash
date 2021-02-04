package com.savecash.safekash.firebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.savecash.safekash.R;
import com.savecash.safekash.activities.DashBoardActivity;
import com.savecash.safekash.activities.MainActivity;
import com.savecash.safekash.activities.Verification;
import com.savecash.safekash.models.Users;

public class FireBaseMethods
{
    /*FireBase tag*/
    private String tag="FireBaseMethod";
    /*FireBase instances*/
    private FirebaseAuth mAuth;
    private FirebaseDatabase myRefDataBase;
    private FirebaseUser user;
    private DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener  mAuthLitener;
    private String userId;
    private ProgressDialog progressDialog;
    private Context context;

    public FireBaseMethods(Context context)
    {
        this.context=context;
        mAuth = FirebaseAuth.getInstance();
        myRefDataBase=FirebaseDatabase.getInstance();
        myRef=myRefDataBase.getReference();
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle(context.getString(R.string.dialog_title));
        progressDialog.setMessage(context.getString(R.string.dialog_message));
        progressDialog.isShowing();

    }


    public void createAccount(final String userName, String email, final long phoneNumber, final String password)
    {
        Log.d(tag,"creating an account");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    progressDialog.dismiss();
                    userId=mAuth.getCurrentUser().getUid();
                    addUser(userName,phoneNumber);
                    sendVerificationLink();
                    Toast.makeText(context,context.getString(R.string.toast_success),Toast.LENGTH_LONG);
                    mAuth.signOut();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG);
            }
        });



    }
               /*This method is used to create dataBase and adding user to database*/
    private void addUser(String userName, long phoneNumber)
    {
        Users users=new Users(userName,phoneNumber);
        myRef.child(context.getString(R.string.data_base_user))
             .child(userId)
             .setValue(users);



    }
                    /*Method used to send verification link*/
    private void sendVerificationLink()
    {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(context, context.getString(R.string.verification_email), Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                            Toast.makeText(context, context.getString(R.string.failed_email), Toast.LENGTH_SHORT).show();
                        }
                }
            });
        }


    }
     /*Method listening to auth change state*/
    public void onChangeState()
    {
        mAuth.addAuthStateListener(mAuthLitener);
        checkifLoggedIn(mAuth.getCurrentUser());


    }


    public void initFirbase()
    {

        mAuthLitener=new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                checkifLoggedIn(user);

            }
        };

    }
    public void checkifLoggedIn(FirebaseUser currentUser)
    {
        if(currentUser==null)
        {
          /* Intent login=new Intent(context, Verification.class);
            login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(login);
*/
        }


    }
    public void clearState()
    {
        if(mAuthLitener!=null)
        {
            mAuth.removeAuthStateListener(mAuthLitener);
        }

    }

    public void loginUser(String email,String password)
    {

       progressDialog.show();
     mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task)
         {
            user= mAuth.getCurrentUser();
             if (task.isSuccessful())
             {
                 try
                 {
                     if(user.isEmailVerified())
                     {
                         progressDialog.dismiss();
                         Toast.makeText(context, "logged in successfully", Toast.LENGTH_SHORT).show();
                         Intent intent=new Intent(context, DashBoardActivity.class);
                         context.startActivity(intent);

                     }
                     else
                     {
                         progressDialog.dismiss();
                         Toast.makeText(context, "Email is not verified check your mail inbox", Toast.LENGTH_SHORT).show();
                         mAuth.signOut();
                     }

                 }
                 catch (NullPointerException e)
                 {
                     Log.e(tag,"On complete null pointer exception"+e);

                 }






             }
         }
     }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {

         }
     });


    }




}
