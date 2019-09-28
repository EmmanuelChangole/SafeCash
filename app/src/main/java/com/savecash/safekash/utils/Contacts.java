package com.savecash.safekash.utils;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.savecash.safekash.models.ContactsItems;
import com.savecash.safekash.R;
import com.savecash.safekash.activities.SendMoney;
import com.savecash.safekash.activities.DashBoardActivity;
import com.savecash.safekash.activities.Notification;

import java.util.ArrayList;

public class Contacts extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private final int REQUEST_ACCESS_CODE=123;
    ArrayList<ContactsItems> contactsItems=new ArrayList<ContactsItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawable3);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        checkPermission();
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        switch (item.getItemId())
        {
            case R.id.notify:
                Intent intent=new Intent(this, Notification.class);
                startActivity(intent);
                break;
            case R.id.home:
                Intent home=new Intent(this, DashBoardActivity.class);
                startActivity(home);
                break;




        }

        return super.onOptionsItemSelected(item);
    }




    public void checkPermission()
    {
        if(Build.VERSION.SDK_INT>=23)
        {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},REQUEST_ACCESS_CODE);
                return;
            }

        }

        readContacts();

    }
    public void onRequestPermissionsResult(int requestCode,String[]permissions,int grantResult[])
    {
        switch (requestCode)
        {
            case REQUEST_ACCESS_CODE:
                if(grantResult[0]== PackageManager.PERMISSION_GRANTED)
                {
                    readContacts();
                }
                else
                {
                    Toast.makeText(this,"You have denied the Contacts access",Toast.LENGTH_LONG).show();


                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode,permissions,grantResult);





        }
    }
    public void readContacts()
    {
        Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            int imageId=cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_ID));
            contactsItems.add(new ContactsItems(name,phone,imageId));
        }
        ContactAdapter contactAdapter=new ContactAdapter(contactsItems);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();

    }

    public class ContactAdapter extends BaseAdapter
    {




        public ArrayList<ContactsItems> contactList;
        public ContactAdapter(ArrayList<ContactsItems> contactList)
        {
            this.contactList=contactList;
        }

        @Override
        public int getCount() {
            return contactList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater=getLayoutInflater();
            View view1= inflater.inflate(R.layout.list_contacts,null);
            final TextView name=(TextView) view1.findViewById(R.id.contact_name);
            final TextView phone=(TextView) view1.findViewById(R.id.contact_phone);
            ImageView profile=(ImageView)view1.findViewById(R.id.contact_image);

            final ContactsItems contactsItems=contactList.get(position);
            name.setText(contactsItems.Name);
            phone.setText(contactsItems.PhoneNumber);
            profile.setImageResource(contactsItems.ID);
            /*name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(getApplicationContext(),SendMoney.class);
                    intent.putExtra("Name",contactsItems.getName());
                    intent.putExtra("Phone",contactsItems.getPhoneNumber());
                    intent.putExtra("Id",contactsItems.getID());
                    startActivity(intent);


                }
            });*/
            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(), SendMoney.class);
                    intent.putExtra("Name",contactsItems.getName());
                    intent.putExtra("Phone",contactsItems.getPhoneNumber());
                    intent.putExtra("Id",contactsItems.getID());
                    startActivity(intent);
                }
            });



            return view1;
        }

    }}

