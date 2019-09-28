package com.savecash.safekash.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.savecash.safekash.R;

public class SendMoney extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    TextView UserName;
    TextView PhoneNumber;
    String name;
    String sendName;
    EditText amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawable4);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        name=intent.getStringExtra("Name");
        String phone_number="Phone Number: "+intent.getStringExtra("Phone");
        String Id=intent.getStringExtra("Id");
        UserName=(TextView)findViewById(R.id.receiver_name);
        PhoneNumber=(TextView)findViewById(R.id.receiver_number);
        UserName.setText("Name: "+name);
        PhoneNumber.setText(phone_number);



    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.nav_menu,menu);
        return true;


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;

        switch (item.getItemId())
        {
            case R.id.home:
                Intent intent=new Intent(this, Notification.class);
                startActivity(intent);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    public void sendMoney(View view) {
        amount =(EditText)findViewById(R.id.amount);
        String amountText=amount.getText().toString();

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirmation");
        alertDialog.setMessage("Are you sure you want to send "+ amountText +" to "+name);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.show();

    }
}
