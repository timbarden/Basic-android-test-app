package com.example.myapplication;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.util.Log;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("onMessageReceived", "");
    }

    @Override
    public void onNewToken(String newToken) {
        super.onNewToken(newToken);
        Log.d("onNewToken", newToken);
    }
}