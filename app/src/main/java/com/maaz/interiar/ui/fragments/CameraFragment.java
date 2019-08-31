package com.maaz.interiar.ui.fragments;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.maaz.interiar.R;
import com.maaz.interiar.ui.activities.CaptureImageActivity;
import com.maaz.interiar.utils.Constant;
import com.maaz.interiar.utils.Utility;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;

public class CameraFragment extends BaseFragment implements View.OnClickListener {

    private CameraView cameraView;
    private Button buttonCapture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
    }

    private void initialize(final View view) {

        buttonCapture = view.findViewById(R.id.buttonCapture);

        cameraView = view.findViewById(R.id.cameraView);
        cameraView.setLifecycleOwner(getViewLifecycleOwner());

        buttonCapture.setOnTouchListener(colorFilterOnTouch);
        buttonCapture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case (MotionEvent.ACTION_DOWN & MotionEvent.ACTION_BUTTON_PRESS): {
                        buttonCapture.setScaleY(1.6f);
                        buttonCapture.setScaleX(1.6f);
                        break;
                    }
                    case (MotionEvent.ACTION_UP & MotionEvent.ACTION_CANCEL): {
                        buttonCapture.setScaleY(1.0f);
                        buttonCapture.setScaleX(1.0f);
                        break;
                    }
                }
                return false;
            }
        });

        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(@NonNull PictureResult result) {
                super.onPictureTaken(result);
                result.toBitmap(new BitmapCallback() {
                    @Override
                    public void onBitmapReady(@Nullable Bitmap bitmap) {
                        assert bitmap != null;
                        processImageFromBitmapAndStartImageActivity(bitmap);
                    }
                });
            }
        });
        setListeners();
    }

    private void setListeners() {
        buttonCapture.setOnClickListener(this);
    }

    private void processImageFromBitmapAndStartImageActivity(Bitmap bitmap) {
        buttonCapture.setOnClickListener(CameraFragment.this);
        buttonCapture.setScaleY(1.0f);
        buttonCapture.setScaleX(1.0f);
        Uri imageUri = Utility.getImageUri(requireContext(), bitmap);
        Intent imageIntent = new Intent(requireActivity(), CaptureImageActivity.class);
        imageIntent.putExtra(Constant.IMAGE_DATA, imageUri.toString());
        requireActivity().startActivity(imageIntent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCapture: {
                if (checkOrAskForMultipleRunTimePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    buttonCapture.setOnClickListener(null);
                    cameraView.takePicture();
                }
                break;
            }
        }
    }

}
