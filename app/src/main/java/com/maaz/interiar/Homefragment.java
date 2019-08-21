package com.maaz.interiar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

public class Homefragment extends Fragment {

    SearchView mysearchview;

    TextView categories, brands, products, favorites;
    ViewPager viewPager;
    PagerViewAdapter pagerViewAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);


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

        categories = (TextView) v.findViewById(R.id.cat);
        brands = (TextView) v.findViewById(R.id.br);
        products = (TextView) v.findViewById(R.id.ltpro);
        favorites = (TextView) v.findViewById(R.id.fav);

        viewPager = (ViewPager) v.findViewById(R.id.fragment_container11);

        // loadFragment(new Fragment_Home());

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        brands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3);
            }
        });

        pagerViewAdapter = new PagerViewAdapter(getActivity().getSupportFragmentManager());

        viewPager.setAdapter(pagerViewAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                onChangeTab(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });




     return v;
    }

    private boolean loadFragment(Fragment fragment) {
        // switching fragment
        if (fragment != null) {
              getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @SuppressLint("ResourceAsColor")
    private void onChangeTab(int i){

    if (i == 0) {
            categories.setTextSize(23);


            brands.setTextSize(19);
            products.setTextSize(19);
            favorites.setTextSize(19);
        }

        if (i == 1) {
            brands.setTextSize(23);


            categories.setTextSize(19);
            products.setTextSize(19);
            favorites.setTextSize(19);
        }

        if (i == 2) {
            products.setTextSize(23);


            brands.setTextSize(19);
            categories.setTextSize(19);
            favorites.setTextSize(19);
        }

        if (i == 3) {
            favorites.setTextSize(23);


            brands.setTextSize(19);
            products.setTextSize(19);
            categories.setTextSize(19);
        }
    }



}


