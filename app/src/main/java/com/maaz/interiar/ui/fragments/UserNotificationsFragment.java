package com.maaz.interiar.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maaz.interiar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserNotificationsFragment extends Fragment {


    public UserNotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_notifications, container, false);
    }

}
