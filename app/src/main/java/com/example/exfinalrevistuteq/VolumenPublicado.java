package com.example.exfinalrevistuteq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class VolumenPublicado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumenes_publicados);

        Bundle bundle = this.getIntent().getExtras();
        Toast.makeText(this,bundle.getString("IDRevista"),Toast.LENGTH_SHORT).show();

    }
}