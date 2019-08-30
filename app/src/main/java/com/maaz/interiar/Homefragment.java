package com.maaz.interiar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;



public class Homefragment extends Fragment {

    SearchView mysearchview;
    FragmentPagerAdapter adapterViewPager;



    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerViewAdapter adapter;


    public static Homefragment newInstance(){
        Homefragment fragment = new Homefragment();
        return fragment;
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
/*

        tabLayout = (TabLayout) v.findViewById(R.id.tablayout_home);
        viewPager = (ViewPager) v.findViewById(R.id.fragment_container_home);
        adapter = new PagerViewAdapter(getActivity().getSupportFragmentManager());


*/
/*
        adapter = new PagerViewAdapter (getSupportFragmentManager());
*//*


        //Add fragment Here

        adapter.AddFragment(new Camfragment());//index 0
        adapter.AddFragment(new Homefragment());//index 1
        adapter.AddFragment(new Assistant_Fragment());//index 2
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_photo_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_interiar_logo_with_transparent_background_without_name_svg);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_assistant_black_24dp);
*/

        //Remove Shadow From the Action bar

       /* ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);*/



        //Search Bar
        mysearchview = (SearchView) v.findViewById(R.id.searchView);
        mysearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return v;
    }

}
