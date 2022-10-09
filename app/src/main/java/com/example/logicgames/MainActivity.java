package com.example.logicgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startButton, topButton, exitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        topButton = (Button)findViewById(R.id.topButton);
        exitButton = (Button)findViewById(R.id.exitButton);

        startButton.setOnClickListener((View.OnClickListener) this);
        topButton.setOnClickListener((View.OnClickListener) this);
        exitButton.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.startButton) {
            startActivity(new Intent(MainActivity.this, GamesList.class));
        }
        else if (view.getId() == R.id.topButton) {
            //ToDo: add leaderboard from Google Play Games
        }
        else if (view.getId() == R.id.exitButton) {
            finish();
        }
    }
}