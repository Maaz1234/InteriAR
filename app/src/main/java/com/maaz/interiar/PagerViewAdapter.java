package com.maaz.interiar;



import android.content.Intent;
import android.graphics.Camera;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/*
    Class that stores fragments for tabs
*/

public class PagerViewAdapter extends FragmentPagerAdapter {

    private static final String TAG = "PagerViewAdapter";
    private final List<Fragment> listFragments = new ArrayList<>();


    /*private final List<String> listTitles = new ArrayList<>();*/

    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return listFragments.get(position);

    }

    @Override
    public int getCount() {
        /*return listTitles.size();*/

        return listFragments.size();
    }

    //Adding fragment with title

  /*@Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitles.get(position);
    }

    public void AddFragment (Fragment fragment, String title)
    {
        listFragments.add(fragment);
        listTitles.add(title);
    }*/



    //Adding Fragment without title
    public void AddFragment (Fragment fragment)
    {
        listFragments.add(fragment);
    }

}
