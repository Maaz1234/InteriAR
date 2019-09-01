package com.maaz.interiar.ui.fragments;

import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.maaz.interiar.utils.Constant;

import java.util.ArrayList;

class BaseFragment extends Fragment {
    boolean checkOrAskForMultipleRunTimePermission(String... permission) {
        ArrayList permissionsNeeded = new ArrayList<String>();

        for (String getPermission : permission) {
            int askPermission = ActivityCompat.checkSelfPermission(
                    requireContext(),
                    getPermission
            );

            if (askPermission != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(getPermission);
            }
        }

        if (!permissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    (String[]) permissionsNeeded.toArray(new String[0]),
                    Constant.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS
            );
            return false;
        }
        return true;
    }

    View.OnTouchListener colorFilterOnTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Drawable drawable = view.getBackground();

            switch (motionEvent.getAction()) {
                case (MotionEvent.ACTION_DOWN & MotionEvent.ACTION_BUTTON_PRESS): {
                    drawable.setColorFilter(0x20000000, PorterDuff.Mode.SRC_ATOP);
                    break;
                }
                case (MotionEvent.ACTION_UP & MotionEvent.ACTION_CANCEL): {
                    drawable.clearColorFilter();
                    break;
                }
            }
            return false;
        }
    };
}
