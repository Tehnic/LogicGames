package com.tehnic.logicgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.games.GamesSignInClient;
import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.PlayGamesSdk;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startButton, topButton, exitButton;
    ImageView languageButton;
    Context context;
    Resources resources;
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
        setContentView(R.layout.activity_main);
        HideSystemUI();
        startButton = (Button)findViewById(R.id.startButton);
        topButton = (Button)findViewById(R.id.topButton);
        exitButton = (Button)findViewById(R.id.exitButton);
        languageButton = (ImageView)findViewById(R.id.languageButton);
        startButton.setOnClickListener(this);
        topButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
        languageButton.setOnClickListener(this);
        languageButton.setTag("en");

        PlayGamesSdk.initialize(this);
        GamesSignInClient gamesSignInClient = PlayGames.getGamesSignInClient(MainActivity.this);

        gamesSignInClient.isAuthenticated().addOnCompleteListener(isAuthenticatedTask -> {
            boolean isAuthenticated =
                    (isAuthenticatedTask.isSuccessful() &&
                            isAuthenticatedTask.getResult().isAuthenticated());

            if (isAuthenticated) {

            } else {
                gamesSignInClient.signIn();
            }
        });

    }

    public void onClick(View view) {
        if (view.getId() == R.id.startButton) {
            startActivity(new Intent(MainActivity.this, GamesList.class));
        }
        else if (view.getId() == R.id.topButton) {
            PlayGames.getLeaderboardsClient(this)
                    .getAllLeaderboardsIntent()
                    .addOnSuccessListener(intent -> startActivityForResult(intent, 1));
        }
        else if (view.getId() == R.id.exitButton) {
           finish();
           System.exit(0);
        }
        else if (view.getId() == R.id.languageButton) {
            languageButton.setOnClickListener(v -> {
                if (languageButton.getTag().equals("en")) {
                    languageButton.setImageResource(R.drawable.ru);
                    languageButton.setTag("ru");
                    context = LocaleHelper.setLocale(MainActivity.this, "ru-RU");
                    resources = context.getResources();
                } else if (languageButton.getTag().equals("ru")) {
                    languageButton.setImageResource(R.drawable.lv);
                    languageButton.setTag("lv");
                    context = LocaleHelper.setLocale(MainActivity.this, "lv-LV");
                    resources = context.getResources();
                } else if (languageButton.getTag().equals("lv")) {
                    languageButton.setImageResource(R.drawable.gb);
                    languageButton.setTag("en");
                    context = LocaleHelper.setLocale(MainActivity.this, "en-us");
                    resources = context.getResources();
                }
            });
        }
    }
}