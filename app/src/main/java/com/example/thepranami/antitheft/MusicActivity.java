package com.example.thepranami.antitheft;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MusicActivity extends AppCompatActivity {
    Button stopMusic;
   public static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        stopMusic=findViewById(R.id.button);
        mediaPlayer = MediaPlayer.create(MusicActivity.this, R.raw.music);
        mediaPlayer.start();

        stopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}
