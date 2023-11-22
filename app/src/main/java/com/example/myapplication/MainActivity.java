package com.example.myapplication;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

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

        // Add permission request dialog for notifications, so it runs after user has added a school
        if (!checkPostNotificationsPermission(getApplicationContext())) {
            requestPostNotificationsPermission();
        }
    }

    public boolean checkPostNotificationsPermission(Context context) {
        // Permissions need to be requested for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
          return ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        } else {
          return true;
        }
    }
  
    // POST_NOTIFICATIONS is classified as a 'dangerous' permission so much be manually requested from the user
    public void requestPostNotificationsPermission() {
      Log.d("requestPostNotificationsPermission", "");
      requestPermissions( 
        new String[] { 
          android.Manifest.permission.POST_NOTIFICATIONS 
        }, 
        255);
    }
        
}