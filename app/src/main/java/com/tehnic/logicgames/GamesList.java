package com.tehnic.logicgames;

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

    ImageButton sqarrow_button;
    CardView sqCardView;
    LinearLayout sqhidden_view1;
    Button startSequencer;

    ImageButton mqarrow_button;
    CardView mqCardView;
    LinearLayout mqhidden_view1, mqhidden_view2;
    Button mqeasybutton, mqmediumbutton, mqhardbutton, startMathQuiz;
    TextView mqdescription;
    String mqdifficulty = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_games_list);
        HideSystemUI();

        findpairelements();
        findpairlistener();

        sequenceelements();
        sequencelistener();

        mathquizelements();
        mathquizlistener();
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
                    fpeasybutton.setTextColor(Color.parseColor("#156b05"));
                    fpmediumbutton.setTextColor(Color.parseColor("#880e4f"));
                    fphardbutton.setTextColor(Color.parseColor("#880e4f"));
                    fpdifficulty = "easy";
                    fphidden_view2.setVisibility(View.VISIBLE);
                    fpdescription.setText(getResources().getString(R.string.fpeasydesc));
                });
                fpmediumbutton.setOnClickListener(v1 -> {
                    fpeasybutton.setTextColor(Color.parseColor("#880e4f"));
                    fpmediumbutton.setTextColor(Color.parseColor("#156b05"));
                    fphardbutton.setTextColor(Color.parseColor("#880e4f"));
                    fpdifficulty = "medium";
                    fphidden_view2.setVisibility(View.VISIBLE);
                    fpdescription.setText(getResources().getString(R.string.fpmediumdesc));
                });
                fphardbutton.setOnClickListener(v1 -> {
                    fpeasybutton.setTextColor(Color.parseColor("#880e4f"));
                    fpmediumbutton.setTextColor(Color.parseColor("#880e4f"));
                    fphardbutton.setTextColor(Color.parseColor("#156b05"));
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
            finish();
        });
    }

    private void sequenceelements() {
        sqarrow_button = findViewById(R.id.sqarrow_button);
        sqCardView = findViewById(R.id.sqcardview);
        sqhidden_view1 = findViewById(R.id.sqhidden_view1);
        startSequencer = findViewById(R.id.startSequencer);
    }
    private void sequencelistener() {
        sqarrow_button.setOnClickListener(v -> {
            if (sqhidden_view1.getVisibility() == View.GONE) {
                sqhidden_view1.setVisibility(View.VISIBLE);
                sqarrow_button.setImageResource(R.drawable.ic_baseline_expand_less_24);
            } else {
                sqhidden_view1.setVisibility(View.GONE);
                sqarrow_button.setImageResource(R.drawable.ic_baseline_expand_more_24);
            }
        });

        startSequencer.setOnClickListener(v -> {
            startActivity(new Intent(GamesList.this, Sequencer.class));
            finish();
        });
    }

    private void mathquizelements() {
        mqarrow_button = findViewById(R.id.mqarrow_button);
        mqCardView = findViewById(R.id.mqcardview);
        mqhidden_view1 = findViewById(R.id.mqhidden_view1);
        mqhidden_view2 = findViewById(R.id.mqhidden_view2);
        mqeasybutton = findViewById(R.id.mqeasybutton);
        mqmediumbutton = findViewById(R.id.mqmediumbutton);
        mqhardbutton = findViewById(R.id.mqhardbutton);
        mqdescription = findViewById(R.id.mqdescription);
        startMathQuiz = findViewById(R.id.startMathQuiz);
    }
    private void mathquizlistener() {
        mqarrow_button.setOnClickListener(v -> {
            if (mqhidden_view1.getVisibility() == View.GONE) {
                mqhidden_view1.setVisibility(View.VISIBLE);
                mqarrow_button.setImageResource(R.drawable.ic_baseline_expand_less_24);

                mqeasybutton.setOnClickListener(v1 -> {
                    mqeasybutton.setTextColor(Color.parseColor("#156b05"));
                    mqmediumbutton.setTextColor(Color.parseColor("#880e4f"));
                    mqhardbutton.setTextColor(Color.parseColor("#880e4f"));
                    mqdifficulty = "easy";
                    mqhidden_view2.setVisibility(View.VISIBLE);
                    mqdescription.setText(getResources().getString(R.string.mqeasydesc));
                });
                mqmediumbutton.setOnClickListener(v1 -> {
                    mqeasybutton.setTextColor(Color.parseColor("#880e4f"));
                    mqmediumbutton.setTextColor(Color.parseColor("#156b05"));
                    mqhardbutton.setTextColor(Color.parseColor("#880e4f"));
                    mqdifficulty = "medium";
                    mqhidden_view2.setVisibility(View.VISIBLE);
                    mqdescription.setText(getResources().getString(R.string.mqmediumdesc));
                });
                mqhardbutton.setOnClickListener(v1 -> {
                    mqeasybutton.setTextColor(Color.parseColor("#880e4f"));
                    mqmediumbutton.setTextColor(Color.parseColor("#880e4f"));
                    mqhardbutton.setTextColor(Color.parseColor("#156b05"));
                    mqdifficulty = "hard";
                    mqhidden_view2.setVisibility(View.VISIBLE);
                    mqdescription.setText(getResources().getString(R.string.mqharddesc));
                });
            } else {
                mqeasybutton.setTextColor(Color.parseColor("#880e4f"));
                mqmediumbutton.setTextColor(Color.parseColor("#880e4f"));
                mqhardbutton.setTextColor(Color.parseColor("#880e4f"));
                mqhidden_view2.setVisibility(View.GONE);
                mqhidden_view1.setVisibility(View.GONE);
                mqarrow_button.setImageResource(R.drawable.ic_baseline_expand_more_24);
            }
        });
        startMathQuiz.setOnClickListener(v -> {
            startActivity(new Intent(GamesList.this, MathQuiz.class).putExtra("mqdifficulty", mqdifficulty));
            finish();
        });
    }
}