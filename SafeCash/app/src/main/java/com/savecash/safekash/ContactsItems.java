package com.savecash.safekash;

public class ContactsItems {
    public String Name;
    public String PhoneNumber;
    public int ID;
    public ContactsItems(String Name,String phoneNumber,int ID)
    {
        this.Name=Name;
        this.PhoneNumber=phoneNumber;
        this.ID=ID;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public int getID() {
        return ID;
    }

}
