package com.example.hacatlon.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hacatlon.participants.Players;
import com.example.hacatlon.R;

public class ResultActivity extends AppCompatActivity {

    TextView result;
    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.winner);
        playAgain = findViewById(R.id.playagain);

        result.setText(Players.winner);

        playAgain.setOnClickListener(view -> {
            Players.player1.setPlayerPoints(0);
            Players.player2.setPlayerPoints(0);

            Intent intent = new Intent();
            intent.putExtra("KEY_1", Players.player1.getPlayerPoints());
            intent.putExtra("KEY_2", Players.player2.getPlayerPoints());

            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }
}