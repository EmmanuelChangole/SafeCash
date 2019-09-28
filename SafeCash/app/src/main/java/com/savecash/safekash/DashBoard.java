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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {
       private DrawerLayout drawerLayout;
       private ActionBarDrawerToggle toggle;
       private int send_money=R.drawable.send_money;
       private int buyairtime=R.drawable.buyairtime;
       private int cashout=R.drawable.cashout;
       private int makepayment=R.drawable.makepayment;
       private int request_money=R.drawable.request_money;
       private int refer_to_friend=R.drawable.refer_to_friend;
       private final int REQUEST_ACCESS_CODE=123;
       Menu menu;
       MenuItem MenuItem;
       ArrayList<AdapterItems> adapterItems;
       CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawable2);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
       toggle.syncState();
       drawerLayout.addDrawerListener(toggle);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        GridView gridViewItems=(GridView)findViewById(R.id.gridItems);
        adapterItems=new ArrayList<AdapterItems>();
        adapterItems.add(new AdapterItems(R.drawable.send_money));
        adapterItems.add(new AdapterItems(R.drawable.buyairtime));
        adapterItems.add(new AdapterItems(R.drawable.cashout));
        adapterItems.add(new AdapterItems(R.drawable.makepayment));
        adapterItems.add(new AdapterItems(R.drawable.request_money));
        adapterItems.add(new AdapterItems(R.drawable.refer_to_friend));
        customAdapter=new CustomAdapter(adapterItems);
        gridViewItems.setAdapter(customAdapter);



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
                Intent intent=new Intent(this,Notification.class);
               startActivity(intent);
                break;
            case R.id.home:
                Intent home=new Intent(this,DashBoard.class);
                startActivity(home);
                break;




        }

        return super.onOptionsItemSelected(item);
    }

    private class CustomAdapter extends BaseAdapter
    {
        public ArrayList<AdapterItems> adapterItems=new ArrayList<AdapterItems>();

        public CustomAdapter(ArrayList<AdapterItems> adapterItems)
        {
            this.adapterItems=adapterItems;
        }


        @Override
        public int getCount() {
            return adapterItems.size();
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
            final AdapterItems items=adapterItems.get(position);
            LayoutInflater inflater=getLayoutInflater();
           View view1=(View)inflater.inflate(R.layout.list_items,null);
           final ImageView imageView=view1.findViewById(R.id.items);
           imageView.setImageResource(items.ID);
           imageView.animate().rotation(360).setDuration(3000).setStartDelay(100);
           imageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  if(items.getID()==send_money)
                  {
                    Intent intent=new Intent(getApplicationContext(),Contacts.class);
                    startActivity(intent);

                  }
                  else if(items.getID()==buyairtime)
                  {
                      Toast.makeText(getApplicationContext(),"Buy airtime",Toast.LENGTH_LONG).show();
                  }
                  else if(items.getID()==cashout)
                  {
                      Toast.makeText(getApplicationContext(),"Cash out",Toast.LENGTH_LONG).show();
                  }
                  else if (items.getID()==makepayment)
                  {
                      Toast.makeText(getApplicationContext(),"Make Payment",Toast.LENGTH_LONG).show();
                  }
                  else if(items.getID()==request_money)
                  {
                      Toast.makeText(getApplicationContext(),"Request money",Toast.LENGTH_LONG).show();
                  }
                  else if(items.getID()==refer_to_friend)
                  {
                      Toast.makeText(getApplicationContext(),"Refer Friends",Toast.LENGTH_LONG).show();
                  }




               }
           });



            return view1;
        }
    }







}
