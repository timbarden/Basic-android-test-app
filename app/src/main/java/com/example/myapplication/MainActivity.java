package com.example.myapplication;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          createNotificationChannel();
        }

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

    private void createNotificationChannel() {
      // Create the NotificationChannel, but only on API 26+ because
      // the NotificationChannel class is not in the Support Library.
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          int importance = NotificationManager.IMPORTANCE_DEFAULT;
          NotificationChannel channel = new NotificationChannel("channel01", "Test Channel", importance);
          channel.setDescription("Test Channel");
          // Register the channel with the system. You can't change the importance
          // or other notification behaviors after this.
          NotificationManager notificationManager = getSystemService(NotificationManager.class);
          notificationManager.createNotificationChannel(channel);
      }
    }
 
}