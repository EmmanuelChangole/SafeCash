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

public class LoginFragment extends Fragment {
    /*Login log*/
    private String tag = "LoginFragment";
    /*Widget  declaration*/
    private View view;
    private EditText loginEmail;
    private EditText loginPassword;
    private Button loginButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(tag, getActivity().getString(R.string.loginFragment_Log));
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        initWidgets();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatesData();
            }
        });
        return view;
    }

    /*Method used to initialize the widgets*/
    private void initWidgets()
    {
        loginEmail=view.findViewById(R.id.loginEdAccount);
        loginPassword=view.findViewById(R.id.loginEdPass);
        loginButton=view.findViewById(R.id.but_sign_in);

    }
    /*Method that's gets and validates data provided by the user*/
    private void validatesData()
    {
        String email=loginEmail.getText().toString().trim();
        String password=loginPassword.getText().toString().trim();

        if(isEmpty(email))
        {
            loginEmail.setError(getActivity().getString(R.string.email_error));
            loginEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            loginEmail.setError(getActivity().getString(R.string.error_invalid_mail));
            loginEmail.requestFocus();
            return;

        }

        if(isEmpty(password))
        {
            loginPassword.setError(getActivity().getString(R.string.password_error));
            loginPassword.requestFocus();
            return;
        }

        if(isLengthMin("name",password,"PhoneNumber"))
        {
            loginPassword.setError(getActivity().getString(R.string.password_length_error));
            loginPassword.requestFocus();
            return;
        }





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
