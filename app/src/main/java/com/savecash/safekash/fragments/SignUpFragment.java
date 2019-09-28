package com.savecash.safekash.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.savecash.safekash.R;
import com.savecash.safekash.firebase.FireBaseMethods;

public class SignUpFragment extends Fragment
{
          /*Log*/
     private String Tag="SignUpFragment";
        /*Widgets Decalaraations*/
        private EditText signUpName;
        private EditText signUpEmail;
        private EditText signUpPhoneNumber;
        private EditText signUpPassword;
        private EditText signUpConfrimPassword;
        private Button signUpButton;
        private View view;


    @Nullable
    @Override
              /*Creates the sign up view*/
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        Log.d(Tag,"Starting Sign up fragment");
        view=inflater.inflate(R.layout.fragment_sign_up,container,false);
        initWigets();
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatesData();
            }
        });

        return view;
    }

         /*Method used to initialize the widgets*/
    private void initWigets()
    {
        signUpName=view.findViewById(R.id.ed_sign_up_userName);
        signUpEmail=view.findViewById(R.id.ed_sign_up_Email);
        signUpPhoneNumber=view.findViewById(R.id.ed_sign_up_phone);
        signUpPassword=view.findViewById(R.id.ed_sign_up_Password);
        signUpConfrimPassword=view.findViewById(R.id.ed_sign_up_ConfrimPassword);
        signUpButton=view.findViewById(R.id.ed_sign_up_button);



    }

          /*Method that's gets and validates data provided by the user*/
    private void validatesData()
    {
        String userName=signUpName.getText().toString().trim();
        String email=signUpEmail.getText().toString().trim();
        String phoneNumber=signUpPhoneNumber.getText().toString().trim();
        String password=signUpPassword.getText().toString().trim();
        String confirmPassword=signUpConfrimPassword.getText().toString();

        if(isEmpty(userName))
        {
            signUpName.setError(getActivity().getString(R.string.user_name_error));
            signUpName.requestFocus();
            return;
        }
        if(isLengthMin(userName,"password","phoneNumber"))
        {
        signUpName.setError(getActivity().getString(R.string.user_name_error_length));
        signUpName.requestFocus();
        return;
        }
        if(isEmpty(email))
        {
            signUpEmail.setError(getActivity().getString(R.string.email_error));
            signUpEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signUpEmail.setError(getActivity().getString(R.string.error_invalid_mail));
            signUpEmail.requestFocus();
            return;

        }

        if(isEmpty(phoneNumber))
        {
            signUpPhoneNumber.setError(getActivity().getString(R.string.phone_number_error));
            signUpPhoneNumber.requestFocus();
            return;
        }
        if(isLengthMin("name","password",phoneNumber))
        {
            signUpPhoneNumber.setError(getActivity().getString(R.string.phone_number_length_error));
            return;
        }

        if(isEmpty(password))
        {
            signUpPassword.setError(getActivity().getString(R.string.password_error));
            signUpPassword.requestFocus();
            return;
        }
        if(isLengthMin("name",password,"PhoneNumber"))
        {
            signUpPassword.setError(getActivity().getString(R.string.password_length_error));
            signUpPassword.requestFocus();
            return;
        }

        if(!confirmPassword.matches(password))
        {
            signUpConfrimPassword.setError(getActivity().getString(R.string.confrim_password_error));
            signUpConfrimPassword.requestFocus();
            return;
        }


        registerUser(userName,email,phoneNumber,password);



    }

          /*Method that register user with new account*/

    private void registerUser(String userName, String email, String phoneNumber, String password)
    {

        long phoneNum=Long.parseLong(phoneNumber);
        FireBaseMethods fireBaseMethods=new FireBaseMethods(getContext());
        fireBaseMethods.createAccount(userName,email,phoneNum,password);



    }
            /*Method that validates the length*/
    private boolean isLengthMin(String string,String pass,String phone)
    {
        if(string.length()<4)
            return  true;
        if(pass.length()<6)
            return true;
        if(phone.length()<10)
            return true;
        return false;
    }
                /*Method that check if data provided is empty*/

    private boolean isEmpty(String string)
    {
        if(string.isEmpty())
            return true;
        return false;

    }
}
