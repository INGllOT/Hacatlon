package com.example.hacatlon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.app.Activity;
import android.content.Intent;

//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button start, reset;
    TextView gracz1, gracz2;

    int statsGracz1 ;
    int statsGracz2 ;
    int rounds = 1;
    String winner = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.startId);
        reset = findViewById(R.id.resetId);

        Questions.addQuestions();

        gracz1 = findViewById(R.id.gracz1);
        gracz2 = findViewById(R.id.gracz2);



        // odbieranie danych z koncowej aktywnosci
        ActivityResultLauncher<Intent> endResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            gracz1.setText("Gracz1: " + Players.player1.getPlayerPoints());
                            gracz2.setText("Gracz2: " + Players.player2.getPlayerPoints());

                        }
                    }
                });

        // odbieranie danych z 2 aktywnosci
        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();

                            int gracz1Intent =   intent.getIntExtra("KEY_1", 99);
                            int gracz2Intnet =   intent.getIntExtra("KEY_2", 99);
                            statsGracz1 += gracz1Intent;
                            statsGracz2 += gracz2Intnet;

                          //  Toast.makeText(MainActivity.this, "gracz 1 "+ gracz1Intent + " " + "gracz 2 "+ gracz1Intent, Toast.LENGTH_LONG).show();
                            gracz1.setText("Gracz1: " + Players.player1.getPlayerPoints());
                            gracz2.setText("Gracz2: " + Players.player2.getPlayerPoints());

                            if(Players.player1.getPlayerPoints() == rounds) {
                                winner = "PLAYER 1";
                                Players.winner = winner;
                                Intent intent2 = new Intent(MainActivity.this, MainActivity4.class);
                                endResult.launch(intent2);
                            } else if (Players.player2.getPlayerPoints() == rounds) {
                                winner = "PLAYER 2";
                                Players.winner = winner;
                                Intent intent2 = new Intent(MainActivity.this, MainActivity4.class);
                                endResult.launch(intent2);
                            }
                        }
                    }
                });


        // otiweranie 2 aktywnosci
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                mStartForResult.launch(intent);
            }
        });

        // resetowanie
        reset.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Players.player1.setPlayerPoints(0);
                Players.player2.setPlayerPoints(0);
                gracz1.setText("Gracz1 : ");
                gracz2.setText("Gracz2 : ");
            }
        }));
    }


}