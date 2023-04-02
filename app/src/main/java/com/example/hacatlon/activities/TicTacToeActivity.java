package com.example.hacatlon.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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

    private final Button[][] buttons = new Button[3][3];
    private boolean firstPlayerTurn = true;
    private int amountOfTurns;
    private int firstPlayerPoints;
    private int secondPlayerPoints;
    private TextView textViewFirstPlayer;
    private TextView textViewSecondPlayer;
    private TextView time;
    private boolean timeRun = false;
    private boolean activityStarted = false;

    Random rnd = new Random();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

        textViewFirstPlayer = findViewById(R.id.text_view_p1);
        textViewSecondPlayer = findViewById(R.id.text_view_p2);
        time = findViewById(R.id.text_view_time);

        // assign to our array every button
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int buttonResID = getResources().getIdentifier("button_" + i + j, "id", getPackageName());
                buttons[i][j] = findViewById(buttonResID);

                // colors
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                buttons[i][j].setBackgroundColor(color);

                // assign to each button onClick function
                buttons[i][j].setOnClickListener(this);

            }
            startTimer();
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(v -> resetTheGame());
    }

    // receive data from second activity
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {

                    Intent intent = result.getData();

                    setResult(Activity.RESULT_OK, intent);

                    finish();
                }
            });

    @Override
    public void onClick(View v) {

        if (!timeRun) {
            startTimer();
            timeRun = true;
        }

        // check if button is not "clicked"
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        // add X and O
        if (firstPlayerTurn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        amountOfTurns++;

        if (checkForWin()) {
            if (firstPlayerTurn) {
                firstPlayerWins();
            } else {
                secondPlayerWins();
            }
        } else if (amountOfTurns == 9) {
            draw();
        } else {
            firstPlayerTurn = !firstPlayerTurn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        // every method invocation loop gets "x" and "o"
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();

                // random colors
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                buttons[i][j].setBackgroundColor(color);
            }
        }

        // check if someone won
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

        return field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("");
    }

    // first player wins
    private void firstPlayerWins() {
        firstPlayerPoints++;
        Toast.makeText(this, "First player wins!", Toast.LENGTH_SHORT).show();
        pointsUpdate();
        tableReset();
    }

    public void startTimer() {
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText("time : " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                time.setText("end!");

                Players.player1win = firstPlayerPoints > secondPlayerPoints;

                System.out.println(Players.player1win);

                Intent intent2 = new Intent(TicTacToeActivity.this, QuizActivity.class);

                if (firstPlayerPoints == secondPlayerPoints) {
                    finish();
                } else if (firstPlayerPoints > secondPlayerPoints) {
                    Players.player1win = true;
                } else if (!activityStarted) {
                    mStartForResult.launch(intent2);
                    activityStarted = true;
                } else {
                    Players.player1win = false;
                }



            }
        }.start();
    }


    // second player wins
    private void secondPlayerWins() {
        secondPlayerPoints++;
        Toast.makeText(this, "Second player wins!", Toast.LENGTH_SHORT).show();
        pointsUpdate();
        tableReset();
    }

    private void draw() {
        Toast.makeText(this, "DRAW!", Toast.LENGTH_SHORT).show();
        tableReset();
    }

    private void pointsUpdate() {
        textViewFirstPlayer.setText("" + firstPlayerPoints);
        textViewSecondPlayer.setText("" + secondPlayerPoints);
    }

    private void tableReset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        amountOfTurns = 0;
        firstPlayerTurn = true;
    }

    // reset of the game
    private void resetTheGame() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        pointsUpdate();
        tableReset();
        startTimer();
    }

    // inverted phone
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", amountOfTurns);
        outState.putInt("player1Points", firstPlayerPoints);
        outState.putInt("player2Points", secondPlayerPoints);
        outState.putBoolean("player1Turn", firstPlayerTurn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        amountOfTurns = savedInstanceState.getInt("roundCount");
        firstPlayerPoints = savedInstanceState.getInt("player1Points");
        secondPlayerPoints = savedInstanceState.getInt("player2Points");
        firstPlayerTurn = savedInstanceState.getBoolean("player1Turn");
    }

}