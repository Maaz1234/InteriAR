package com.maaz.interiar;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerViewAdapter extends FragmentPagerAdapter {

    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new Categories_fragment();
                break;

            case 1:
                fragment = new Brands_fragment();
                break;
            case 2:
                fragment = new Products_fragment();
                break;
            case 3:
                fragment = new Favorites_fragment();
                break;
        }



        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
