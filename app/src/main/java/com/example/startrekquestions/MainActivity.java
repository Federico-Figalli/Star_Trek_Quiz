package com.example.startrekquestions;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer homeSound = MediaPlayer.create(this, R.raw.the_next_generation_intro);
        homeSound.start();
        ImageButton start = findViewById(R.id.start_button_game);
        start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View start){
            Intent start_game = new Intent(getApplicationContext(), Inizio_gioco.class);
            homeSound.stop();
            startActivity(start_game);
            }
        });
    }
}
