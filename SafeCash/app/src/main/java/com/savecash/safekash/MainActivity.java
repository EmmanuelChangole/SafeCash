package com.savecash.safekash;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
private TabLayout tabLayout;
private AppBarLayout appBarLayout;
private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar_layout);
        viewPager=(ViewPager)findViewById(R.id.viewer_page);

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentLogin(),"Login");
        adapter.AddFragment(new FragmentSignUp(),"SignUp");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }


    public void register(View view) {
        Intent intent=new Intent(getApplicationContext(),DashBoard.class);
        startActivity(intent);
    }

    public void signUp(View view) {

    }
}
