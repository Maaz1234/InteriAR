package com.maaz.interiar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Assistant_Fragment extends Fragment {

    public static Assistant_Fragment newInstance(){
        Assistant_Fragment fragment = new Assistant_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_assistant_, container, false);
        return v;
    }

}
