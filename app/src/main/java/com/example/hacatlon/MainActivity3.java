package com.example.hacatlon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    Button check, next;
    TextView result, question, playerTour;

    EditText answer;

    public String currentQuestion = "";
    public String currentAnswer = "";
    public int indexOfQuestion = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Players.player1.lossQuestion();
        Players.player2.lossQuestion();

        check = findViewById(R.id.check);
        next = findViewById(R.id.next);

        question = findViewById(R.id.question);
        playerTour = findViewById(R.id.playerRound);
        result = findViewById(R.id.result);

        answer = findViewById(R.id.answer);

        if(Players.player1win) {
            playerTour.setText("PLAYER 1 TOUR");
            currentQuestion = Questions.questions.get(Players.player1.getCurrentQuestion());
            currentAnswer = Questions.anwserws.get(Players.player1.getCurrentQuestion());

            indexOfQuestion = Questions.questions.indexOf(currentQuestion);
            question.setText(currentQuestion);

        } else {
            playerTour.setText("PLAYER 2 TOUR");
            currentQuestion = Questions.questions.get(Players.player2.getCurrentQuestion());
            currentAnswer = Questions.anwserws.get(Players.player2.getCurrentQuestion());

            indexOfQuestion = Questions.questions.indexOf(currentQuestion);
            question.setText(currentQuestion);
        }

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Players.player1win) {
                  //  System.out.println(Players.player1.currentQuestion + " " + Questions.questions.size());
                    if(currentAnswer.equals(answer.getText().toString())) {
                        result.setText("RIGHT !!!");
                        Players.player1.anwseredQuestion.add(indexOfQuestion);
                        Players.player1.addPoint();
                    } else {
                        result.setText("WRONG !!!");
                    }

                } else {

                    if(currentAnswer.equals(answer.getText().toString())) {
                        result.setText("RIGHT !!!");
                        Players.player2.anwseredQuestion.add(indexOfQuestion);
                        Players.player2.addPoint();
                        System.out.println(Players.player2.getPlayerPoints());
                    } else {
                        result.setText("WRONG !!!");
                    }
                }
                check.setEnabled(false);
                Players.player1.lossQuestion();
                Players.player2.lossQuestion();
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Players.player1.lossQuestion();
                Players.player2.lossQuestion();

                Intent intent = new Intent();
                intent.putExtra("KEY_1", Players.player1.getPlayerPoints());
                intent.putExtra("KEY_2", Players.player2.getPlayerPoints());

                setResult(Activity.RESULT_OK, intent);
                System.out.println(intent.getIntExtra("KEY_1", 99));
                System.out.println(intent.getIntExtra("KEY_2", 99));
                finish();
            }
        });
    }
}