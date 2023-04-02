package com.example.hacatlon.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hacatlon.participants.Players;
import com.example.hacatlon.R;

import java.util.Random;

public class TicTacToeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean kolejPierwszegoGracza = true;

    private int liczbaRuchów;

    private int gracz1PKT;
    private int gracz2PKT;

    private int gracz1RUNDA;
    private int gracz2RUNDA;

    private TextView textViewGracz1;
    private TextView textViewGracz2;

    public TextView time;
    boolean timeRun = false;

    boolean activityStarted = false;

    Random rnd = new Random();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

        textViewGracz1 = findViewById(R.id.text_view_p1);
        textViewGracz2 = findViewById(R.id.text_view_p2);
        time = findViewById(R.id.text_view_time);


        // przypisujemy do naszej tablicy każdy przycisk
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int buttonResID = getResources().getIdentifier("button_" + i + j, "id",getPackageName());
                buttons[i][j] = findViewById(buttonResID);

                // kolory
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                buttons[i][j].setBackgroundColor(color);

                // przypisujemy do każdego przciusku fukcje onClick
                buttons[i][j].setOnClickListener(this);

            }
            startTimer();
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetujGre();
            }
        });
    }


    // odbieranie danych z 2 aktywnosci
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent intent = result.getData();
                       //  przekazywanie danych do 1 aktywności
                        int gracz1Intent =   intent.getIntExtra("KEY_1", 99);
                        int gracz2Intnet =   intent.getIntExtra("KEY_2", 99);
//                intent.putExtra("KEY_1", Players.player1.getPlayerPoints());
//                intent.putExtra("KEY_2", Players.player2.getPlayerPoints());
                setResult(Activity.RESULT_OK, intent);

                        finish();
                    }
                }
            });

    @Override
    public void onClick(View v) {

        if (!timeRun){
            startTimer();
            timeRun = true;
        }

        // sprawdzanie czy buton nie jest "klikniety"
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        // dodawanie X", "O"
        if (kolejPierwszegoGracza) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        liczbaRuchów++;

        if (checkForWin()) {
            if (kolejPierwszegoGracza) {
                player1Wygrywa();
            } else {
                player2Wygrywa();
            }
        } else if (liczbaRuchów == 9) {
            remis();
        } else {
            kolejPierwszegoGracza = !kolejPierwszegoGracza;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        // za kazdym wywolaniem metody petla pobiera "x" "o"
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();

                // randomowo kolorki
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                buttons[i][j].setBackgroundColor(color);
            }
        }

        // sprawdzanie czy ktos wygrał
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    // gracz 1 win
    private void player1Wygrywa() {
        gracz1PKT++;
        Toast.makeText(this, "Gracz 1 wygrywa!", Toast.LENGTH_SHORT).show();
        aktualizacjaPKT();
        resetTablicy();
    }

    public void startTimer() {
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText("czas : " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                time.setText("koniec!");

                if (gracz1PKT > gracz2PKT){
                    Players.player1Win = true;
                    //gracz1RUNDA ++;
                }
                else {
                    Players.player1Win = false;
                   // gracz2RUNDA ++;
                }

                System.out.println(Players.player1Win);

                // przekazywanie danych do 1 aktywności
//                Intent intent = new Intent();
//                intent.putExtra("KEY_1", gracz1RUNDA);
//                intent.putExtra("KEY_2", gracz2RUNDA);

              //  setResult(Activity.RESULT_OK, intent);

                Intent intent2 = new Intent(TicTacToeActivity.this, QuizActivity.class);


                // musi byc bo otwiera X aktywnosci na raz
                if(!activityStarted){
                    mStartForResult.launch(intent2);
                    activityStarted = true;
                }

                //finish();
            }
        }.start();
    }

    //gracz 2 win
    private void player2Wygrywa() {
        gracz2PKT++;
        Toast.makeText(this, "Gracz 2 wygrywa!", Toast.LENGTH_SHORT).show();
        aktualizacjaPKT();
        resetTablicy();
    }

    private void remis() {
        Toast.makeText(this, "REMIS!", Toast.LENGTH_SHORT).show();
        resetTablicy();
    }

    private void aktualizacjaPKT() {
        textViewGracz1.setText("Player 1: " + gracz1PKT);
        textViewGracz2.setText("Player 2: " + gracz2PKT);
    }

    private void resetTablicy() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        liczbaRuchów = 0;
        kolejPierwszegoGracza = true;
    }

    //reset gry
    private void resetujGre() {
        gracz1PKT = 0;
        gracz2PKT = 0;
        aktualizacjaPKT();
        resetTablicy();
        startTimer();
    }

    // telefon poziomo
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", liczbaRuchów);
        outState.putInt("player1Points", gracz1PKT);
        outState.putInt("player2Points", gracz2PKT);
        outState.putBoolean("player1Turn", kolejPierwszegoGracza);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        liczbaRuchów = savedInstanceState.getInt("roundCount");
        gracz1PKT = savedInstanceState.getInt("player1Points");
        gracz2PKT = savedInstanceState.getInt("player2Points");
        kolejPierwszegoGracza = savedInstanceState.getBoolean("player1Turn");
    }
}