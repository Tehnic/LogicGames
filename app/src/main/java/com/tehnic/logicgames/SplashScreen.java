package com.tehnic.logicgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.Objects;

public class SplashScreen extends AppCompatActivity {

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
        setContentView(R.layout.activity_splash_screen);
        HideSystemUI();
        final Handler handler = new Handler();
        handler.postDelayed((Runnable) () -> {
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            finish();
        }, 3000);
    }
}