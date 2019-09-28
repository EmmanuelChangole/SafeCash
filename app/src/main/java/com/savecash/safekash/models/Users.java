package com.savecash.safekash.models;

public class Users
{
    private String user_name;
    private long phone_number;

    public Users()
    {

    }

    public Users(String user_name, long phone_number) {
        this.user_name = user_name;
        this.phone_number = phone_number;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_name='" + user_name + '\'' +
                ", phone_number=" + phone_number +
                '}';
    }
}
