package com.maaz.interiar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Camfragment extends Fragment {

    private static final String TAG = "Camfragment";
  /*  @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.startActivityForResult(i, 1);

    }*/

    /*@Nullable*/

/*
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
*/
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cam,container,false);

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.startActivityForResult(i, 1);
        return view;
    }

    public static Camfragment newInstance()
    {
        Camfragment fragment = new Camfragment();
        return fragment;
    }

   /* @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cam,container,false);
       *//* Log.d(TAG, "onCreateView: started.");

        if (((Home_Activity)getActivity()).getCurrentTabNumber() == 0)
        Log.d(TAG, "onCreateView: launching camera");*//*

        
        return view;
    }*/
}
