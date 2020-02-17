package com.maaz.interiar.ui.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maaz.interiar.R;
import com.maaz.interiar.ui.adapters.NotificationListAdapter;

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

        RecyclerView notificationlist = (RecyclerView) findViewById.(R.id.notification_list);
        notificationlist.setLayoutManager(new LinearLayoutManager(this));
        String[] language = {"new product","50% discount","habit's new arrival","update","office collection","italian lamps","single beds"};
        notificationlist.setAdapter(new NotificationListAdapter(language));
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_notifications, container, false);
     return view;
    }

}
