package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("FCMToken", "Fetching FCM registration token failed", task.getException());
                return;
            }
            // Get new FCM registration token
            String token = task.getResult();
            Log.d("FCMToken", token);
            // Send this token to your server to send notifications
        });

        setContentView(R.layout.activity_main);
    }
}