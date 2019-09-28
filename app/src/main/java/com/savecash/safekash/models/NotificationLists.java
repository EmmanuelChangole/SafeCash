package com.savecash.safekash.models;

public class NotificationLists {
  int ID;
 String name;
 String amount;
 String date;
 String phoneNum;
 String action;

public  NotificationLists(int ID,String name,String amount,String date,String phoneNum,String action)
{
    this.ID=ID;
    this.name=name;
    this.amount=amount;
    this.date=date;
    this.phoneNum=phoneNum;
    this.action=action;
}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "NotificationLists{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}


