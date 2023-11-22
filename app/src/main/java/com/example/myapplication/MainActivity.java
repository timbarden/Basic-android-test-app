package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this in your onCreate() method or Application class
        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_main);
    }
}