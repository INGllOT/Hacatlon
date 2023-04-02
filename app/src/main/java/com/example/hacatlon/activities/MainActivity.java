package com.example.hacatlon.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.app.Activity;
import android.content.Intent;

import android.widget.Button;
import android.widget.TextView;

import com.example.hacatlon.participants.Players;
import com.example.hacatlon.R;
import com.example.hacatlon.database.Questions;

public class MainActivity extends AppCompatActivity {

    Button start, reset;
    TextView firstPlayer, secondPlayer;

    private int statsFirstPlayer;
    private int statsSecondPlayer;
    private final int rounds = 1;
    private String winner = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.startId);
        reset = findViewById(R.id.resetId);

        Questions.addQuestions();

        firstPlayer = findViewById(R.id.gracz1);
        secondPlayer = findViewById(R.id.gracz2);

        // receive data from final activity
        ActivityResultLauncher<Intent> endResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        firstPlayer.setText("Player 1: " + Players.player1.getPlayerPoints());
                        secondPlayer.setText("Player 2: " + Players.player2.getPlayerPoints());

                    }
                });

        // receive data from second activity
        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();

                        assert intent != null;
                        int firstPlayerIntent = intent.getIntExtra("KEY_1", 99);
                        int secondPlayerIntnet = intent.getIntExtra("KEY_2", 99);
                        statsFirstPlayer += firstPlayerIntent;
                        statsSecondPlayer += secondPlayerIntnet;

                        firstPlayer.setText("Player 1: " + Players.player1.getPlayerPoints());
                        secondPlayer.setText("Player 2: " + Players.player2.getPlayerPoints());

                        if (Players.player1.getPlayerPoints() == rounds) {
                            winner = "PLAYER 1";
                            Players.winner = winner;
                            Intent intent2 = new Intent(MainActivity.this, ResultActivity.class);
                            endResult.launch(intent2);
                        } else if (Players.player2.getPlayerPoints() == rounds) {
                            winner = "PLAYER 2";
                            Players.winner = winner;
                            Intent intent2 = new Intent(MainActivity.this, ResultActivity.class);
                            endResult.launch(intent2);
                        }

                    }
                });


        // open second activity
        start.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TicTacToeActivity.class);
            mStartForResult.launch(intent);
        });

        // reset
        reset.setOnClickListener((view -> {
            Players.player1.setPlayerPoints(0);
            Players.player2.setPlayerPoints(0);
            firstPlayer.setText("Player 1 : ");
            secondPlayer.setText("Player 2 : ");
        }));
    }


}