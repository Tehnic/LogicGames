package com.example.logicgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startButton, topButton, exitButton;
    ImageView languageButton;
    Context context;
    Configuration config = context.getResources().getConfiguration();
    private void HideSystemUI() {
        View decorView = getWindow().getDecorView();
        int newUIoptions = decorView.getSystemUiVisibility();
        newUIoptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        newUIoptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUIoptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUIoptions |= View.SYSTEM_UI_FLAG_IMMERSIVE;
        newUIoptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(newUIoptions);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideSystemUI();
        config = new Configuration(getResources().getConfiguration());
        getResources().updateConfiguration(config,getResources().getDisplayMetrics());
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        topButton = (Button)findViewById(R.id.topButton);
        exitButton = (Button)findViewById(R.id.exitButton);
        languageButton = (ImageView)findViewById(R.id.languageButton);
        startButton.setOnClickListener(this);
        topButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
        languageButton.setOnClickListener(this);
        languageButton.setTag("en");
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
        else if (view.getId() == R.id.languageButton) {
            languageButton.setOnClickListener(v -> {
                if (languageButton.getTag().equals("en")) {
                    languageButton.setImageResource(R.drawable.ru);
                    languageButton.setTag("ru");
                } else if (languageButton.getTag().equals("ru")) {
                    languageButton.setImageResource(R.drawable.lv);
                    languageButton.setTag("lv");
                    config.locale = Locale.forLanguageTag("lv");
                } else if (languageButton.getTag().equals("lv")) {
                    languageButton.setImageResource(R.drawable.gb);
                    languageButton.setTag("en");
                    config.locale = Locale.forLanguageTag("en");
                }
            });
        }
    }
}