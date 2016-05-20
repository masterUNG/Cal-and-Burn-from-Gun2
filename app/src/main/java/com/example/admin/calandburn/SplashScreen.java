package com.example.admin.calandburn;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    private Handler objHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        objHandler = new Handler();
        objHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent ToMainMenuIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(ToMainMenuIntent);
                finish();
            }
        }, 3000);

        // Sound Effect
        MediaPlayer soundInto = MediaPlayer.create(getBaseContext(), R.raw.gamecorrect);
        soundInto.start();
    }
} // Main Class
