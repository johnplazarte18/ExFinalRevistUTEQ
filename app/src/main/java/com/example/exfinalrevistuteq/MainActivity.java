package com.example.exfinalrevistuteq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mindorks.placeholderview.PlaceHolderView;

public class MainActivity extends AppCompatActivity {

    private PlaceHolderView mGalleryView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGalleryView=(PlaceHolderView)findViewById(R.id.ltrevistas);
    }
}