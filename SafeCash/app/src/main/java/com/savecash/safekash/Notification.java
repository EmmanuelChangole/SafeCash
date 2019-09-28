package com.savecash.safekash;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    ArrayList<NotificationLists> notificationLists;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ListView listView=(ListView)findViewById(R.id.notify_list);


        notificationLists=new ArrayList<NotificationLists>();
        notificationLists.add(new NotificationLists(R.drawable.avatar,"Emmanuel",": $100","12th sep","0702073189","buy Airtime"));
        notificationLists.add(new NotificationLists(R.drawable.avatar,"Mum",": $1000","13rd  sep","0720558327","send Money"));
        notificationLists.add(new NotificationLists(R.drawable.avatar,"Mercy",": $2500","20th sep","0702073134","buy Airtime"));
        notificationLists.add(new NotificationLists(R.drawable.avatar,"Dad",": $3400","25th sep","0722218476","Send money"));
        notificationLists.add(new NotificationLists(R.drawable.avatar,"Emmanuel",": $9400","29th sep","0702073167","Cash out"));
        customAdapter=new CustomAdapter(notificationLists);
        listView.setAdapter(customAdapter);


        drawerLayout=(DrawerLayout)findViewById(R.id.drawable2);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawable2);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        switch (item.getItemId())
        {
            case R.id.notify:
                Intent intent=new Intent(this,DashBoard.class);
                startActivity(intent);
                break;




        }

        return super.onOptionsItemSelected(item);
    }




    private class  CustomAdapter extends BaseAdapter
    {
        ArrayList<NotificationLists> notificationLists=new ArrayList<NotificationLists>();

        public CustomAdapter (ArrayList<NotificationLists> notificationLists)
        {
            this.notificationLists=notificationLists;
        }


        @Override
        public int getCount() {
            return notificationLists.size();
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

            final NotificationLists notifiction=notificationLists.get(position);
            LayoutInflater inflater=getLayoutInflater();
            View view=(View)inflater.inflate(R.layout.list_notifications,null);

            ImageView user_image=(ImageView)view.findViewById(R.id.notify_user);
            TextView name=(TextView)view.findViewById(R.id.notify_name);
            TextView balances=(TextView)view.findViewById(R.id.notify_balances);
            TextView date=(TextView)view.findViewById(R.id.notify_date);
            TextView action=(TextView)view.findViewById(R.id.notify_action);
            TextView phhone_num=(TextView)view.findViewById(R.id.notify_num);

            user_image.setImageResource(notifiction.ID);
            name.setText(notifiction.name);
            balances.setText(notifiction.amount);
            date.setText(notifiction.date);
            action.setText(notifiction.action);
            phhone_num.setText(notifiction.phoneNum);




            return view;
        }
    }

}
