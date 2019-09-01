package com.maaz.interiar.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.maaz.interiar.R;
import com.maaz.interiar.utils.Constant;

public class CaptureImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView imageViewCaptureImage = findViewById(R.id.imageViewCaptureImage);
        Uri imageUri = Uri.parse(getIntent().getStringExtra(Constant.IMAGE_DATA));

        imageViewCaptureImage.setImageURI(imageUri);
    }
}


