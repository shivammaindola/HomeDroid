package com.example.sneha.googlesignin;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    MediaPlayer mp = null;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"BroadCast Received",Toast.LENGTH_LONG).show();
        eventMethod();
    }
    protected void eventMethod(){
        Uri uri= Uri.parse("android.resource://com.example.sneha.googlesignin/raw/ring.mp3") ;

      // mp = MediaPlayer.create(this,uri);

        mp.start();
    }
    public  void start(){

    }
}
