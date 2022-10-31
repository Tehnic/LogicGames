package com.example.logicgames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Objects;

public class GamesList extends AppCompatActivity {

    ImageButton fparrow_button;
    CardView fpCardView;
    LinearLayout fphidden_view1, fphidden_view2;
    Button fpeasybutton, fpmediumbutton, fphardbutton, startFindPair;
    TextView fpdescription;
    String fpdifficulty = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_games_list);
        HideSystemUI();

        findpairelements();
        findpairlistener();
    }
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
    private void findpairelements() {
        fparrow_button = findViewById(R.id.fparrow_button);
        fpCardView = findViewById(R.id.fpcardview);
        fphidden_view1 = findViewById(R.id.fphidden_view1);
        fphidden_view2 = findViewById(R.id.fphidden_view2);
        fpeasybutton = findViewById(R.id.fpeasybutton);
        fpmediumbutton = findViewById(R.id.fpmediumbutton);
        fphardbutton = findViewById(R.id.fphardbutton);
        fpdescription = findViewById(R.id.fpdescription);
        startFindPair = findViewById(R.id.startFindPair);
    }
    private void findpairlistener() {
        fparrow_button.setOnClickListener(v -> {
            if (fphidden_view1.getVisibility() == View.GONE) {
                fphidden_view1.setVisibility(View.VISIBLE);
                fparrow_button.setImageResource(R.drawable.ic_baseline_expand_less_24);

                fpeasybutton.setOnClickListener(v1 -> {
                    fpeasybutton.setTextColor(Color.parseColor("#fff200"));
                    fpmediumbutton.setTextColor(Color.parseColor("#880e4f"));
                    fphardbutton.setTextColor(Color.parseColor("#880e4f"));
                    fpdifficulty = "easy";
                    fphidden_view2.setVisibility(View.VISIBLE);
                    fpdescription.setText(getResources().getString(R.string.fpeasydesc));
                });
                fpmediumbutton.setOnClickListener(v1 -> {
                    fpeasybutton.setTextColor(Color.parseColor("#880e4f"));
                    fpmediumbutton.setTextColor(Color.parseColor("#fff200"));
                    fphardbutton.setTextColor(Color.parseColor("#880e4f"));
                    fpdifficulty = "medium";
                    fphidden_view2.setVisibility(View.VISIBLE);
                    fpdescription.setText(getResources().getString(R.string.fpmediumdesc));
                });
                fphardbutton.setOnClickListener(v1 -> {
                    fpeasybutton.setTextColor(Color.parseColor("#880e4f"));
                    fpmediumbutton.setTextColor(Color.parseColor("#880e4f"));
                    fphardbutton.setTextColor(Color.parseColor("#fff200"));
                    fpdifficulty = "hard";
                    fphidden_view2.setVisibility(View.VISIBLE);
                    fpdescription.setText(getResources().getString(R.string.fpharddesc));
                });
            } else {
                fpeasybutton.setTextColor(Color.parseColor("#880e4f"));
                fpmediumbutton.setTextColor(Color.parseColor("#880e4f"));
                fphardbutton.setTextColor(Color.parseColor("#880e4f"));
                fphidden_view2.setVisibility(View.GONE);
                fphidden_view1.setVisibility(View.GONE);
                fparrow_button.setImageResource(R.drawable.ic_baseline_expand_more_24);
            }
        });
        startFindPair.setOnClickListener(v -> {
            startActivity(new Intent(GamesList.this, FindPair.class).putExtra("fpdifficulty", fpdifficulty));
        });
    }
}