package com.example.hacatlon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    TextView result;
    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        result = findViewById(R.id.winner);
        playAgain = findViewById(R.id.playagain);

        result.setText(Players.winner.toString());



        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Players.player1.setPlayerPoints(0);
                Players.player2.setPlayerPoints(0);

                Intent intent = new Intent();
                intent.putExtra("KEY_1", Players.player1.getPlayerPoints());
                intent.putExtra("KEY_2", Players.player2.getPlayerPoints());

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}