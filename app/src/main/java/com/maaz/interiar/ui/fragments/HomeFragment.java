package com.maaz.interiar.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.maaz.interiar.R;
import com.maaz.interiar.ui.Models.HorizontalProductScrollModel;
import com.maaz.interiar.ui.Models.SliderModel;
import com.maaz.interiar.ui.adapters.SliderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

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
    private ConstraintLayout stripAdContainer;

    FrameLayout homeFragmentContainerForStripAd;
    /*Strip Ad*/

    /*Horizontal Product Layout*/
    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllBtn;
    private RecyclerView horizontalRecyclerView;
    /*Horizontal Product Layout*/


    FrameLayout frameLayout;
    FrameLayout frameLayoutStripAd;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_home, container, false);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        homeFragmentContainerForStripAd = view.findViewById(R.id.home_fragment_container_for_strip_ad);

        appBarLayout = view.findViewById(R.id.appBarLayoutHome);
        frameLayout = view.findViewById(R.id.home_fragment_container);
        frameLayoutStripAd = view.findViewById(R.id.home_fragment_container_for_strip_ad);

        SearchView searchView = view.findViewById(R.id.searchView);
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

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

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
        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.strip_ad);
        stripAdContainer.setBackgroundColor(Color.parseColor("#000000"));
        /*Strip Ad*/

        /*Horizontal Product Layout*/
        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllBtn = view.findViewById(R.id.horizontal_scroll_view_all_btn);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_product_recyclerview);


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


        return view;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
