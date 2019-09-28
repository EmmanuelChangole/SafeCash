package com.savecash.safekash;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbManager
{
   SQLiteDatabase database;
    static String DataBaseName="SafeCash";
    static String TableName="SafeCashUsers";
    static String ColName="Name";
    static String ColEmail ="Email";
    static String ColAccountNumber="AccountNumber";
    static String ColPassword ="Password";
    static int DataBaseVersion=2;
    static String CreateTable="CREATE TABLE IF NOT EXISTS "+TableName+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ColName+"TEXT," +ColEmail+"TEXT," +
            ColAccountNumber+"TEXT," + ColPassword +"TEXT);";

    static  class SafeCashData extends SQLiteOpenHelper
    {
        Context context;

        public SafeCashData(Context context) {
            super(context, DataBaseName, null, DataBaseVersion);
            this.context=context;
        }


        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CreateTable);
            Toast.makeText(context,"Table created",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    public DbManager(Context context)
    {
      SafeCashData safeCashData=new SafeCashData(context);
      database=safeCashData.getWritableDatabase();


    }
    public long insertRecords(ContentValues values)
    {
      return database.insert(TableName,"",values);

    }





}
