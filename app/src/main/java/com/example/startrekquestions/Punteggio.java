package com.example.startrekquestions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Punteggio extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punteggio);
        Button restart = findViewById(R.id.restart_button);
        Button exit = findViewById(R.id.exit_button);
        end_game();


    restart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Intent restart = new Intent(getApplicationContext(), Inizio_gioco.class);
        startActivity(restart);
        }
    });


    exit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
            moveTaskToBack(true);
        }
    });

    }

    public void end_game() {
        Intent score = getIntent();
        Bundle data = score.getExtras();
        String result = data.getString("Result","");
        Log.d("11", "result " + result);
        TextView print_result = findViewById(R.id.risultato);
        print_result.setText(result);

    }
}
