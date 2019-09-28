package com.savecash.safekash.utils;

public class CredentialVerification
{




    String account;
    String password;
    String creAccount="0702073189";
    String crePass="7304";


    public CredentialVerification(String account, String password) {
        this.account = account;
        this.password = password;

    }

    public boolean check()
    {
        if(crePass.matches(password)&&creAccount.matches(account))
        {
          return true;
        }

     return  false;

    }


}
