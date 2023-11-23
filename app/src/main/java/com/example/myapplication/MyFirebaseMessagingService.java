package com.example.myapplication;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.util.Log;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("onMessageReceived", remoteMessage.getFrom());

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("Message", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // notification dialog here
    }

    @Override
    public void onNewToken(String newToken) {
        super.onNewToken(newToken);
        Log.d("onNewToken", newToken);
    }
}