package com.example.startrekquestions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inizio_gioco extends AppCompatActivity {
    public static List<String> full_list = new ArrayList<>();
    public static List<String> random_list = new ArrayList<>();
    public  List<String> correct_answer_list = new ArrayList<>();
    public  List<String> select_answer_list = new ArrayList<>();
    public BufferedReader file_txt;
    public  int h = 0;
    public int check;
    int n_questions = 5;
    int n_array = n_questions - 1;
    Button next_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inizio_gioco);
        next_button= findViewById(R.id.next_button);
        inizialized_game();
        next_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View next) {
                game();
            }
        });
    }

    public void inizialized_game() {
        try {
            final InputStream file_assets = getAssets().open("Answers_Questions.txt");
            file_txt = new BufferedReader(new InputStreamReader(file_assets));
            String row = "";
            while (row != null) {
                row = file_txt.readLine();
                full_list.add(row);
                Log.d("2 ", "Scrivo");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (int c = 0; c <= n_array; c++) {
            int riga = new Random().nextInt(full_list.size());
            String choose = full_list.get(riga);
            if (choose != null) {
                random_list.add(choose);
                full_list.remove(riga);
                Log.d("4", "Domanda " + choose);
                Log.d("11", "Valore h e random list" + h + "" + random_list.size());
            }else{
                c--;
            }
        }

        TextView answer_text = findViewById(R.id.answer);
        String question_test = "Domanda di prova, poi si fà sul serio: Che cosa è Star Trek?";
        answer_text.setText(question_test);
        RadioGroup questions = findViewById(R.id.questions_chose);
        check = questions.getCheckedRadioButtonId();
        RadioButton question1 = findViewById(R.id.question_1);
        String answer1_test = "Una serie tv di fantascienza";
        question1.setText(answer1_test);
        RadioButton question2 = findViewById(R.id.question_2);
        String answer2_test ="Una serie di libri";
        question2.setText(answer2_test);
        RadioButton question3 = findViewById(R.id.question_3);
        String answer3_test = "Una serie di film senza altro seguito";
        question3.setText(answer3_test);
    }

    public void game() {
        if (h == 4){
            point();
            Log.d("9", "Uscita ");
        }
        else {
            Log.d("5", "Dentro game");
            String choose = random_list.get(h);
            Log.d("7", "Controllo h " + h);
            RadioGroup questions = findViewById(R.id.questions_chose);
            String[] game_now = choose.split("/");
            correct_answer_list.add(game_now[5]);
            Log.d("6", "Split " + game_now[0] + game_now[1] + game_now[2] + game_now[3] + game_now[4] + game_now[5]);
            TextView answer_text = findViewById(R.id.answer);
            answer_text.setText(game_now[1]);
            RadioButton question1 = findViewById(R.id.question_1);
            question1.setText(game_now[2]);
            RadioButton question2 = findViewById(R.id.question_2);
            question2.setText(game_now[3]);
            RadioButton question3 = findViewById(R.id.question_3);
            question3.setText(game_now[4]);
            int select = questions.getCheckedRadioButtonId();
            switch (select) {
                case R.id.question_1:
                    select = 1;
                    next_button.setEnabled(true);
                    break;
                case R.id.question_2:
                    select = 2;
                    next_button.setEnabled(true);
                    break;
                case R.id.question_3:
                    select = 3;
                    next_button.setEnabled(true);
                    break;
                default:
                case -1:
                    next_button.setEnabled(false);
                    Toast.makeText(this, "Scegli un' opzione prima di premere Next", Toast.LENGTH_SHORT).show();
                    //wait(3500);
                    next_button.setEnabled(true);
            }
            String select_string = Integer.toString(select);
            select_answer_list.add(select_string);
            Log.d("8", "Leggo selezione RadioButton String " + select_string);
            h++;
        }
    }

    public void point(){

        correct_answer_list.removeAll(select_answer_list);
        int result = (correct_answer_list.size() - select_answer_list.size()) * -1;
        Log.d("11", "Risultato " + result);
        String string_result =  "Hai azzeccato " + result +  " domande su " + ((select_answer_list.size()) + 1);

        Bundle data = new Bundle();
        data.putString("Result", string_result);

        Intent score = new Intent(getApplicationContext(), Punteggio.class);
        score.putExtras(data);
        startActivity(score);
    }
}






