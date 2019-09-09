package com.maaz.interiar.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.maaz.interiar.R;
import com.maaz.interiar.ui.Models.HorizontalProductScrollModel;
import com.maaz.interiar.ui.Models.SliderModel;
import com.maaz.interiar.ui.adapters.PagerViewAdapter;
import com.maaz.interiar.ui.adapters.SliderAdapter;
import com.maaz.interiar.ui.fragments.BrandsFragment;
import com.maaz.interiar.ui.fragments.CameraFragment;
import com.maaz.interiar.ui.fragments.CartFragment;
import com.maaz.interiar.ui.fragments.HomeFragment;
import com.maaz.interiar.ui.fragments.Photofragment;
import com.maaz.interiar.ui.fragments.Profilefragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeActivity extends AppCompatActivity {

    Fragment currentFragment;
    ViewPager viewPagerMain;
    PagerViewAdapter mainPagerAdapter;
    AppBarLayout appBarLayout;

    ///////////// Banner Slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    //////////// Banner Slider


    /*Strip Ad*/
    private ImageView stripAdImage;
    private ConstraintLayout  stripAdContainer;

    FrameLayout homeFragmentContainerForStripAd;
    /*Strip Ad*/

    /*Horizontal Product Layout*/
    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllBtn;
    private RecyclerView horizontalRecyclerView;
    /*Horizontal Product Layout*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //currentFragment = new HomeFragment();

        final BottomNavigationView bottomNavigationView = findViewById(R.id.navi_bar);

        appBarLayout = findViewById(R.id.appBarLayoutHome);

        viewPagerMain = findViewById(R.id.viewPagerMain);

        homeFragmentContainerForStripAd = findViewById(R.id.home_fragment_container_for_strip_ad);

        mainPagerAdapter = new PagerViewAdapter(getSupportFragmentManager());

        mainPagerAdapter.AddFragment(new CameraFragment());//index 0
        mainPagerAdapter.AddFragment(new HomeFragment());//index 1
        mainPagerAdapter.AddFragment(new Profilefragment());//index 2

        viewPagerMain.setAdapter(mainPagerAdapter);
        viewPagerMain.setCurrentItem(1);

        viewPagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0 || position == 2)
                {
                    bottomNavigationView.setVisibility(View.INVISIBLE);
                    appBarLayout.setVisibility(View.INVISIBLE);
                    bannerSliderViewPager.setVisibility(View.INVISIBLE);

                    homeFragmentContainerForStripAd.setVisibility(View.INVISIBLE);
                }
                else if (position == 1)
                {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    appBarLayout.setVisibility(View.VISIBLE);
                    bannerSliderViewPager.setVisibility(View.VISIBLE);

                    homeFragmentContainerForStripAd.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        currentFragment = new HomeFragment();
        loadFragment(new HomeFragment());
        bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                currentFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        appBarLayout.setVisibility(View.VISIBLE);
                        bannerSliderViewPager.setVisibility(View.VISIBLE);
                        fragment = new HomeFragment();
                        bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        loadFragment(fragment);
                        break;
                    case R.id.nav_cart:
                        /*bannerSliderViewPager.setVisibility(View.INVISIBLE);
                        appBarLayout.setVisibility(View.INVISIBLE);*/
                        fragment = new CartFragment();
                        bottomNavigationView.getMenu().findItem(R.id.nav_cart).setChecked(true);
                        loadFragment(fragment);
                        break;
                    case R.id.nav_photo:
                        bannerSliderViewPager.setVisibility(View.INVISIBLE);
                        appBarLayout.setVisibility(View.INVISIBLE);
                        fragment = new Photofragment();
                        bottomNavigationView.getMenu().findItem(R.id.nav_photo).setChecked(true);
                        loadFragment(fragment);
                        break;
                    case R.id.nav_profile:
                        bannerSliderViewPager.setVisibility(View.INVISIBLE);
                        appBarLayout.setVisibility(View.INVISIBLE);
                        fragment = new Profilefragment();
                        bottomNavigationView.getMenu().findItem(R.id.nav_profile).setChecked(true);
                        loadFragment(fragment);
                        break;
                    case R.id.nav_cam:
                        bannerSliderViewPager.setVisibility(View.INVISIBLE);
                        appBarLayout.setVisibility(View.INVISIBLE);
                        fragment = new BrandsFragment();
                        bottomNavigationView.getMenu().findItem(R.id.nav_cam).setChecked(true);
                        loadFragment(fragment);
                        break;
                }
                return false;
            }
        });

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        /////////Banner Slider

        bannerSliderViewPager = findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_foreground,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_round,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_interiar_launcher,"#FFFFFF"));

        sliderModelList.add(new SliderModel(R.mipmap.ic_interiar_launcher_round,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_background,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_foreground,"#FFFFFF"));

        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_round,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_interiar_launcher,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_interiar_launcher_round,"#FFFFFF"));


        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if( state == ViewPager.SCROLL_STATE_IDLE)
                {
                    pageLooper();
                }

            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pageLooper();
                stopBannerSlideShow();
                if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    startBannerSlideShow();
                }
                return false;
            }
        });

        /////////Banner Slider

        /*Strip Ad*/
        stripAdImage = findViewById(R.id.strip_ad_image);
        stripAdContainer = findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.strip_ad);
        stripAdContainer.setBackgroundColor(Color.parseColor("#000000"));
        /*Strip Ad*/

        /*Horizontal Product Layout*/
        horizontalLayoutTitle = findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllBtn = findViewById(R.id.horizontal_scroll_view_all_btn);
        horizontalRecyclerView = findViewById(R.id.horizontal_product_recyclerview);


        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.relaxer,"Relaxer", "Grey Relaxer", "Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.table,"Relaxer", "Grey Relaxer", "Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sofa,"Relaxer", "Grey Relaxer", "Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.chair,"Relaxer", "Grey Relaxer", "Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.kitchen,"Relaxer", "Grey Relaxer", "Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bath,"Relaxer", "Grey Relaxer", "Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.outdoor,"Relaxer", "Grey Relaxer", "Rs.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ligthing,"Relaxer", "Grey Relaxer", "Rs.5999/-"));
/*
        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);*/

        /*Horizontal Product Layout*/
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_home, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /////////Banner Slider

    private void pageLooper()
    {
        if (currentPage == sliderModelList.size() -2)
        {
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1)
        {
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
    }

    private void startBannerSlideShow()
    {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size())
                {
                    currentPage = 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }

    private void stopBannerSlideShow()
    {
        timer.cancel();
    }

    /////////Banner Slider

}
