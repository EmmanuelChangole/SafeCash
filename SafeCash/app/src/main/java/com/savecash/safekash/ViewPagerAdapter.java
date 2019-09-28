package com.savecash.safekash;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
  private final List<Fragment> fragmentsList=new ArrayList<>();
  private final List<String>  fragmentsListTitle=new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsListTitle.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsListTitle.size();
    }
    public void AddFragment(Fragment fragment,String title)
    {
        fragmentsList.add(fragment);
        fragmentsListTitle.add(title);
    }
}
