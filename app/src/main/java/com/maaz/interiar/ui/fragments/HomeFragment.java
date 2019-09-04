package com.maaz.interiar.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.maaz.interiar.R;
import com.maaz.interiar.ui.Models.SliderModel;
import com.maaz.interiar.ui.adapters.SliderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    ///////////// Banner Slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    //////////// Banner Slider

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_home, container, false);

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        /////////Banner Slider

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_foreground,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_round,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_interiar_launcher,"#FFFFFF"));

        sliderModelList.add(new SliderModel(R.mipmap.ic_interiar_launcher_round,"#FFFFFF"));
        /*sliderModelList.add(new SliderModel(R.drawable.banner_example1,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner_example2,"#FFFFFF"));*/
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
