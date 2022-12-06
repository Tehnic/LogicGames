package com.tehnic.logicgames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.games.PlayGames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class FindPair extends AppCompatActivity {
    String DIFFICULTY = "";
    ArrayList<Integer> cards = new ArrayList<>();
    ArrayList<Integer> cards2 = new ArrayList<>();
    ArrayList<Integer> typedindex = new ArrayList<>();
    TableLayout tableLayout;
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11, iv12, iv13, iv14, iv15, iv16;
    Boolean isColorsOk = false;
    Button restartButton; Button pauseButton; Button exitButton;
    int firstCard = -1; int secondCard = 0;
    int clicks = 0; int pairs = 0;
    TextView fpclicks;
    Boolean isPaused = false;
    public static final String EASY_ID = "CgkIwLuZ7LMdEAIQAA";
    public static final String MEDIUM_ID = "CgkIwLuZ7LMdEAIQAg";
    public static final String HARD_ID = "CgkIwLuZ7LMdEAIQAw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pair);
        hideSystemUI();
        fpclicks = findViewById(R.id.fpclicks);
        restartButton = findViewById(R.id.restartButton);
        pauseButton = findViewById(R.id.pauseButton);
        exitButton = findViewById(R.id.exitButton);
        tableLayout = findViewById(R.id.tableLayout);
        Intent intent = getIntent();
        DIFFICULTY = intent.getStringExtra("fpdifficulty");

        imageviewelements();
        generator(DIFFICULTY);
        buttons();
    }

    public void onPause() {
        super.onPause();
        if (!isPaused) {
            pauseButton();
        }
    }

    public void generator(String DIFFICULTY) {
        fpclicks.setText(getResources().getString(R.string.clicks) + "0");
        getCards(DIFFICULTY);
        if (DIFFICULTY.equals("easy")) {
            isColorsOk = false;
            while (isColorsOk == false) {
                getCards(DIFFICULTY);
                isColorsOk = checkColorDifference(cards);
            }
        }
        arrangeCards(cards, DIFFICULTY);
        setOnClickListeners(cards2, DIFFICULTY);
        ivquestions();
    }

    public void buttons() {
        //ToDo: add confirmation dialog for all buttons
        restartButton.setOnClickListener(v -> {
            clicks = 0;
            fpclicks.setText(getResources().getString(R.string.clicks) + "0");
            cards.clear();
            cards2.clear();
            firstCard = -1;
            secondCard = 0;
            pairs = 0;
            ivvisibility();
            generator(DIFFICULTY);
            ivclickable();
        });
        pauseButton.setOnClickListener(v -> {
            pauseButton();
        });
        exitButton.setOnClickListener(v -> {
            startActivity(new Intent(FindPair.this, GamesList.class));
            finish();
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(FindPair.this, GamesList.class));
        finish();
    }

    public void hideSystemUI() {
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

    public void imageviewelements() {
        iv1 = findViewById(R.id.imageView1);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);
        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);
        iv7 = findViewById(R.id.imageView7);
        iv8 = findViewById(R.id.imageView8);
        iv9 = findViewById(R.id.imageView9);
        iv10 = findViewById(R.id.imageView10);
        iv11 = findViewById(R.id.imageView11);
        iv12 = findViewById(R.id.imageView12);
        iv13 = findViewById(R.id.imageView13);
        iv14 = findViewById(R.id.imageView14);
        iv15 = findViewById(R.id.imageView15);
        iv16 = findViewById(R.id.imageView16);
    }

    public void ivquestions() {
        iv1.setImageResource(R.drawable.question);
        iv2.setImageResource(R.drawable.question);
        iv3.setImageResource(R.drawable.question);
        iv4.setImageResource(R.drawable.question);
        iv5.setImageResource(R.drawable.question);
        iv6.setImageResource(R.drawable.question);
        iv7.setImageResource(R.drawable.question);
        iv8.setImageResource(R.drawable.question);
        iv9.setImageResource(R.drawable.question);
        iv10.setImageResource(R.drawable.question);
        iv11.setImageResource(R.drawable.question);
        iv12.setImageResource(R.drawable.question);
        iv13.setImageResource(R.drawable.question);
        iv14.setImageResource(R.drawable.question);
        iv15.setImageResource(R.drawable.question);
        iv16.setImageResource(R.drawable.question);
    }

    public void ivvisibility() {
        iv1.setVisibility(View.VISIBLE);
        iv2.setVisibility(View.VISIBLE);
        iv3.setVisibility(View.VISIBLE);
        iv4.setVisibility(View.VISIBLE);
        iv5.setVisibility(View.VISIBLE);
        iv6.setVisibility(View.VISIBLE);
        iv7.setVisibility(View.VISIBLE);
        iv8.setVisibility(View.VISIBLE);
        iv9.setVisibility(View.VISIBLE);
        iv10.setVisibility(View.VISIBLE);
        iv11.setVisibility(View.VISIBLE);
        iv12.setVisibility(View.VISIBLE);
        iv13.setVisibility(View.VISIBLE);
        iv14.setVisibility(View.VISIBLE);
        iv15.setVisibility(View.VISIBLE);
        iv16.setVisibility(View.VISIBLE);
    }

    public void ivclickable() {
        iv1.setEnabled(true);
        iv2.setEnabled(true);
        iv3.setEnabled(true);
        iv4.setEnabled(true);
        iv5.setEnabled(true);
        iv6.setEnabled(true);
        iv7.setEnabled(true);
        iv8.setEnabled(true);
        iv9.setEnabled(true);
        iv10.setEnabled(true);
        iv11.setEnabled(true);
        iv12.setEnabled(true);
        iv13.setEnabled(true);
        iv14.setEnabled(true);
        iv15.setEnabled(true);
        iv16.setEnabled(true);
    }

    public void getCards(@NonNull String DIFFICULTY) {
        cards.clear();
        if (DIFFICULTY.equals("easy")) {
            for (int i = 0; i < 8; i++) {
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                cards.add(i, color);
            }
        }
        if (DIFFICULTY.equals("medium")) {
            TypedArray medium = getResources().obtainTypedArray(R.array.medium);
            ArrayList<Integer> rnd = new ArrayList<>();
            while (rnd.size() < 8) {
                int random = new Random().nextInt(medium.length());
                if (!rnd.contains(random)) {
                    rnd.add(random);
                }
            }
            for (int i = 0; i < 8; i++) {
                cards.add(i, medium.getResourceId(rnd.get(i), -1));
            }
            medium.recycle();
        }
        if (DIFFICULTY.equals("hard")) {
            TypedArray hard1 = getResources().obtainTypedArray(R.array.hard1);
            TypedArray hard2 = getResources().obtainTypedArray(R.array.hard2);
            ArrayList<Integer> cardsp2 = new ArrayList<>();
            ArrayList<Integer> rnd = new ArrayList<>();
            while (rnd.size() < 8) {
                int random = new Random().nextInt(hard1.length()/2);
                if (!rnd.contains(random)) {
                    rnd.add(random);
                }
            }
            for (int i = 0; i < 8; i++) {
                cards.add(i, hard1.getResourceId(rnd.get(i)*2, -1));
                cardsp2.add(i, hard2.getResourceId(rnd.get(i)*2, -1));
                typedindex.add(i, (rnd.get(i)*2)+1);
            }
            cards.addAll(cardsp2);
            typedindex.addAll(typedindex);
            hard1.recycle();
            hard2.recycle();
        }
    }

    //Only for Easy - Used to check if the colors are too similar (https://en.wikipedia.org/wiki/Color_difference)
    public Boolean checkColorDifference(@NonNull ArrayList<Integer> cards) {
        boolean isPossible = false;
        boolean isChecked = false;
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < cards.size(); j++) {
                if (i != j) {
                    int color1 = cards.get(i);
                    int color2 = cards.get(j);
                    int red1 = Color.red(color1);
                    int red2 = Color.red(color2);
                    int green1 = Color.green(color1);
                    int green2 = Color.green(color2);
                    int blue1 = Color.blue(color1);
                    int blue2 = Color.blue(color2);
                    double deltaRed = Math.pow(red1 - red2, 2);
                    double deltaGreen = Math.pow(green1 - green2, 2);
                    double deltaBlue = Math.pow(blue1 - blue2, 2);
                    double deltaE = Math.sqrt(deltaRed + deltaGreen + deltaBlue);
                    if (deltaE < 120 /*Less number - more similar colors, high number - more time to wait*/) {
                        isPossible = false;
                    } else {
                        isPossible = true;
                    }
                    isChecked = true;
                    if (isPossible == false && isChecked == true) {
                        break;
                    }
                }
                if (isPossible == false && isChecked == true) {
                    break;
                }
            }
            if (isPossible == false && isChecked == true) {
                break;
            }
        }
        return isPossible;
    }

    public void arrangeCards(ArrayList<Integer> cards, @NonNull String DIFFICULTY) {
        cards2.addAll(cards);
        if (DIFFICULTY.equals("easy") || DIFFICULTY.equals("medium")) {
            cards2.addAll(cards);
        }
        long seed = System.nanoTime();
        Collections.shuffle(cards2, new Random(seed));
        Collections.shuffle(typedindex, new Random(seed));

        if (DIFFICULTY.equals("easy")) {
            iv1.setBackgroundColor(cards2.get(0));
            iv2.setBackgroundColor(cards2.get(1));
            iv3.setBackgroundColor(cards2.get(2));
            iv4.setBackgroundColor(cards2.get(3));
            iv5.setBackgroundColor(cards2.get(4));
            iv6.setBackgroundColor(cards2.get(5));
            iv7.setBackgroundColor(cards2.get(6));
            iv8.setBackgroundColor(cards2.get(7));
            iv9.setBackgroundColor(cards2.get(8));
            iv10.setBackgroundColor(cards2.get(9));
            iv11.setBackgroundColor(cards2.get(10));
            iv12.setBackgroundColor(cards2.get(11));
            iv13.setBackgroundColor(cards2.get(12));
            iv14.setBackgroundColor(cards2.get(13));
            iv15.setBackgroundColor(cards2.get(14));
            iv16.setBackgroundColor(cards2.get(15));
        }
        if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
            iv1.setImageResource(cards2.get(0));
            iv2.setImageResource(cards2.get(1));
            iv3.setImageResource(cards2.get(2));
            iv4.setImageResource(cards2.get(3));
            iv5.setImageResource(cards2.get(4));
            iv6.setImageResource(cards2.get(5));
            iv7.setImageResource(cards2.get(6));
            iv8.setImageResource(cards2.get(7));
            iv9.setImageResource(cards2.get(8));
            iv10.setImageResource(cards2.get(9));
            iv11.setImageResource(cards2.get(10));
            iv12.setImageResource(cards2.get(11));
            iv13.setImageResource(cards2.get(12));
            iv14.setImageResource(cards2.get(13));
            iv15.setImageResource(cards2.get(14));
            iv16.setImageResource(cards2.get(15));
        }
    }

    public void setOnClickListeners(ArrayList<Integer> cards2, @NonNull String DIFFICULTY) {
        iv1.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv1.setImageResource(0);
                iv1.setBackgroundColor(cards2.get(0));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv1.setImageResource(cards2.get(0));
            }
            iv1.setEnabled(false);
            iv1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(0);
                }
            }, 500);
        });
        iv2.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv2.setImageResource(0);
                iv2.setBackgroundColor(cards2.get(1));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv2.setImageResource(cards2.get(1));
            }
            iv2.setEnabled(false);
            iv2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(1);
                }
            }, 500);
        });
        iv3.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv3.setImageResource(0);
                iv3.setBackgroundColor(cards2.get(2));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv3.setImageResource(cards2.get(2));
            }
            iv3.setEnabled(false);
            iv3.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(2);
                }
            }, 500);
        });
        iv4.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv4.setImageResource(0);
                iv4.setBackgroundColor(cards2.get(3));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv4.setImageResource(cards2.get(3));
            }
            iv4.setEnabled(false);
            iv4.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(3);
                }
            }, 500);
        });
        iv5.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv5.setImageResource(0);
                iv5.setBackgroundColor(cards2.get(4));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv5.setImageResource(cards2.get(4));
            }
            iv5.setEnabled(false);
            iv5.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(4);
                }
            }, 500);
        });
        iv6.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv6.setImageResource(0);
                iv6.setBackgroundColor(cards2.get(5));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv6.setImageResource(cards2.get(5));
            }
            iv6.setEnabled(false);
            iv6.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(5);
                }
            }, 500);
        });
        iv7.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv7.setImageResource(0);
                iv7.setBackgroundColor(cards2.get(6));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv7.setImageResource(cards2.get(6));
            }
            iv7.setEnabled(false);
            iv7.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(6);
                }
            }, 500);
        });
        iv8.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv8.setImageResource(0);
                iv8.setBackgroundColor(cards2.get(7));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv8.setImageResource(cards2.get(7));
            }
            iv8.setEnabled(false);
            iv8.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(7);
                }
            }, 500);
        });
        iv9.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv9.setImageResource(0);
                iv9.setBackgroundColor(cards2.get(8));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv9.setImageResource(cards2.get(8));
            }
            iv9.setEnabled(false);
            iv9.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(8);
                }
            }, 500);
        });
        iv10.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv10.setImageResource(0);
                iv10.setBackgroundColor(cards2.get(9));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv10.setImageResource(cards2.get(9));
            }
            iv10.setEnabled(false);
            iv10.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(9);
                }
            }, 500);
        });
        iv11.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv11.setImageResource(0);
                iv11.setBackgroundColor(cards2.get(10));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv11.setImageResource(cards2.get(10));
            }
            iv11.setEnabled(false);
            iv11.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(10);
                }
            }, 500);
        });
        iv12.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv12.setImageResource(0);
                iv12.setBackgroundColor(cards2.get(11));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv12.setImageResource(cards2.get(11));
            }
            iv12.setEnabled(false);
            iv12.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(11);
                }
            }, 500);
        });
        iv13.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv13.setImageResource(0);
                iv13.setBackgroundColor(cards2.get(12));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv13.setImageResource(cards2.get(12));
            }
            iv13.setEnabled(false);
            iv13.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(12);
                }
            }, 500);
        });
        iv14.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv14.setImageResource(0);
                iv14.setBackgroundColor(cards2.get(13));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv14.setImageResource(cards2.get(13));
            }
            iv14.setEnabled(false);
            iv14.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(13);
                }
            }, 500);
        });
        iv15.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv15.setImageResource(0);
                iv15.setBackgroundColor(cards2.get(14));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv15.setImageResource(cards2.get(14));
            }
            iv15.setEnabled(false);
            iv15.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(14);
                }
            }, 500);
        });
        iv16.setOnClickListener(v -> {
            if (DIFFICULTY.equals("easy")) {
                iv16.setImageResource(0);
                iv16.setBackgroundColor(cards2.get(15));
            } else if (DIFFICULTY.equals("medium") || DIFFICULTY.equals("hard")) {
                iv16.setImageResource(cards2.get(15));
            }
            iv16.setEnabled(false);
            iv16.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkCards(15);
                }
            }, 500);
        });
    }

    public void checkCards(int position) {
        clicks++;
        if (DIFFICULTY.equals("easy") || DIFFICULTY.equals("medium")) {
            if (firstCard == -1) {
                firstCard = position;
            } else {
                secondCard = position;
                if (Objects.equals(cards2.get(firstCard), cards2.get(secondCard))) {
                    if (firstCard == 0) {
                        iv1.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 1) {
                        iv2.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 2) {
                        iv3.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 3) {
                        iv4.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 4) {
                        iv5.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 5) {
                        iv6.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 6) {
                        iv7.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 7) {
                        iv8.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 8) {
                        iv9.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 9) {
                        iv10.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 10) {
                        iv11.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 11) {
                        iv12.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 12) {
                        iv13.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 13) {
                        iv14.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 14) {
                        iv15.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 15) {
                        iv16.setVisibility(View.INVISIBLE);
                    }
                    if (secondCard == 0) {
                        iv1.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 1) {
                        iv2.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 2) {
                        iv3.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 3) {
                        iv4.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 4) {
                        iv5.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 5) {
                        iv6.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 6) {
                        iv7.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 7) {
                        iv8.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 8) {
                        iv9.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 9) {
                        iv10.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 10) {
                        iv11.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 11) {
                        iv12.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 12) {
                        iv13.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 13) {
                        iv14.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 14) {
                        iv15.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 15) {
                        iv16.setVisibility(View.INVISIBLE);
                    }
                    pairs++;
                    if (pairs == 8) {
                        ending();
                    }
                }
                else {
                    ivquestions();
                }
                firstCard = -1;
                secondCard = 0;
                ivclickable();
            }
            fpclicks.setText(getResources().getString(R.string.clicks) + clicks);
        }
        else if (DIFFICULTY.equals("hard")) {
            if (firstCard == -1) {
                firstCard = position;
            } else {
                secondCard = position;
                if (Objects.equals(typedindex.get(firstCard), typedindex.get(secondCard))) {
                    if (firstCard == 0) {
                        iv1.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 1) {
                        iv2.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 2) {
                        iv3.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 3) {
                        iv4.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 4) {
                        iv5.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 5) {
                        iv6.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 6) {
                        iv7.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 7) {
                        iv8.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 8) {
                        iv9.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 9) {
                        iv10.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 10) {
                        iv11.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 11) {
                        iv12.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 12) {
                        iv13.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 13) {
                        iv14.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 14) {
                        iv15.setVisibility(View.INVISIBLE);
                    } else if (firstCard == 15) {
                        iv16.setVisibility(View.INVISIBLE);
                    }

                    if (secondCard == 0) {
                        iv1.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 1) {
                        iv2.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 2) {
                        iv3.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 3) {
                        iv4.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 4) {
                        iv5.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 5) {
                        iv6.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 6) {
                        iv7.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 7) {
                        iv8.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 8) {
                        iv9.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 9) {
                        iv10.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 10) {
                        iv11.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 11) {
                        iv12.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 12) {
                        iv13.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 13) {
                        iv14.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 14) {
                        iv15.setVisibility(View.INVISIBLE);
                    } else if (secondCard == 15) {
                        iv16.setVisibility(View.INVISIBLE);
                    }
                    pairs++;
                    if (pairs == 8) {
                        ending();
                    }
                }
                else {
                    ivquestions();
                }
                firstCard = -1;
                secondCard = -1;
                ivclickable();
            }
            fpclicks.setText(getResources().getString(R.string.clicks) + clicks);
        }
    }

    public void ending(){
        if (pairs == 8) {
            leaderboard(clicks);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.congrats))
                    .setMessage(getResources().getString(R.string.clickcount) + clicks)
                    .setCancelable(false)
                    .setPositiveButton(getResources().getString(R.string.restart), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(FindPair.this, FindPair.class);
                            intent.putExtra("fpdifficulty", DIFFICULTY);
                            startActivity(intent);

                            finish();
                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.exit), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(FindPair.this, GamesList.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            builder.show();
        }
    }

    public void leaderboard(int clicks) {
        switch (DIFFICULTY) {
            case "easy":
                PlayGames.getLeaderboardsClient(this)
                        .submitScore(EASY_ID, clicks);
                break;
            case "medium":
                PlayGames.getLeaderboardsClient(this)
                        .submitScore(MEDIUM_ID, clicks);
                break;
            case "hard":
                PlayGames.getLeaderboardsClient(this)
                        .submitScore(HARD_ID, clicks);
                break;
        }
    }

    public void pauseButton () {
        if (isPaused) {
            isPaused = false;
            fpclicks.setText(getResources().getString(R.string.clicks) + clicks);
            tableLayout.setVisibility(View.VISIBLE);
            restartButton.setVisibility(View.VISIBLE);
            exitButton.setVisibility(View.VISIBLE);
        }
        else {
            isPaused = true;
            fpclicks.setText(getResources().getString(R.string.paused));
            tableLayout.setVisibility(View.INVISIBLE);
            restartButton.setVisibility(View.INVISIBLE);
            exitButton.setVisibility(View.INVISIBLE);
        }
    }
}