package com.example.myapplication28;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

    MediaPlayer mediaPlayer;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //this method is called when the service is created
    @Override
    public void onCreate() {
        super.onCreate();
        //load to the music file
        mediaPlayer = MediaPlayer.create(this,R.raw.backgroundmusic);
        mediaPlayer.setLooping(true);//set the music file to loop
        mediaPlayer.setVolume(100,100);
    }

    //when the service is started
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();//starts the music
        return super.onStartCommand(intent, flags, startId);
    }

    //when the service is destroyed to stop playing the music
    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}