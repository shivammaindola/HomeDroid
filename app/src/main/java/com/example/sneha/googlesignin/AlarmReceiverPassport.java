package com.example.sneha.googlesignin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;


public class AlarmReceiverPassport extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // Creating Notification...
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getNotification("Passport", "Password Renewal Reminder...");  // Change this to user text...
        notificationHelper.getManager().notify(1, nb.build());

    }
}
