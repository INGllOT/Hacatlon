package com.example.hacatlon.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hacatlon.participants.Players;
import com.example.hacatlon.R;
import com.example.hacatlon.database.Questions;

public class QuizActivity extends AppCompatActivity {

    Button check, next;
    TextView result, question, playerTour;
    EditText answer;

    private String currentQuestion = "";
    private String currentAnswer = "";
    private int indexOfQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        check = findViewById(R.id.check);
        next = findViewById(R.id.next);

        question = findViewById(R.id.question);
        playerTour = findViewById(R.id.playerRound);
        result = findViewById(R.id.result);

        answer = findViewById(R.id.answer);

        if (Players.player1win) {
            playerTour.setText("PLAYER 1 TOUR");
            currentQuestion = Questions.questions.get(Players.player1.getCurrentQuestion());
            currentAnswer = Questions.answers.get(Players.player1.getCurrentQuestion());

            indexOfQuestion = Questions.questions.indexOf(currentQuestion);
            question.setText(currentQuestion);

        } else {
            playerTour.setText("PLAYER 2 TOUR");
            currentQuestion = Questions.questions.get(Players.player2.getCurrentQuestion());
            currentAnswer = Questions.answers.get(Players.player2.getCurrentQuestion());

            indexOfQuestion = Questions.questions.indexOf(currentQuestion);
            question.setText(currentQuestion);
        }

        check.setOnClickListener(view -> {

            if (Players.player1win) {
                if (currentAnswer.equals(answer.getText().toString())) {
                    System.out.println(currentQuestion);
                    System.out.println(currentAnswer);
                    result.setText("RIGHT !!!");
                    Players.player1.answeredQuestion.add(indexOfQuestion);
                    Players.player1.addPoint();
                    System.out.println(Players.player1.getPlayerPoints());
                } else {
                    System.out.println(currentQuestion);
                    System.out.println(currentAnswer);
                    result.setText("WRONG !!!");
                }

            } else {
                System.out.println(Players.player2.currentQuestion + " " + Questions.questions.size());
                if (currentAnswer.equals(answer.getText().toString())) {
                    result.setText("RIGHT !!!");
                    System.out.println(currentQuestion);
                    System.out.println(currentAnswer);
                    Players.player2.answeredQuestion.add(indexOfQuestion);
                    Players.player2.addPoint();
                    System.out.println(Players.player2.getPlayerPoints());
                } else {
                    result.setText("WRONG !!!");
                    System.out.println(currentQuestion);
                    System.out.println(currentAnswer);
                }
            }
            check.setEnabled(false);
        });


        next.setOnClickListener(view -> {

            Intent intent = new Intent();
            intent.putExtra("KEY_1", Players.player1.getPlayerPoints());
            intent.putExtra("KEY_2", Players.player2.getPlayerPoints());

            setResult(Activity.RESULT_OK, intent);
            System.out.println(intent.getIntExtra("KEY_1", 99));
            System.out.println(intent.getIntExtra("KEY_2", 99));
            finish();
        });
    }
}