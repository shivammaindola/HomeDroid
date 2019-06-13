package com.example.sneha.googlesignin;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    public static String channelID = "ChannelID";
    public static String channelName = "ChannelName";

    public NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);

        // Android version 26 will work only...
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        // Initializing Notification channel...
        NotificationChannel mNotification_passport = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        // Configuring Notification Channel...
        mNotification_passport.enableLights(true);
        mNotification_passport.enableVibration(true);
        mNotification_passport.setLightColor(R.color.colorPrimary);
        mNotification_passport.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        getManager().createNotificationChannel(mNotification_passport);

    }

    public NotificationManager getManager(){
        if(mManager == null){
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getNotification(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.mylogo)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
    }
}
