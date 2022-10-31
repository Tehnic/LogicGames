package com.example.logicgames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class FindPair extends AppCompatActivity {
    String difficulty = "";
    ArrayList<Integer> cards = new ArrayList<>();
    ArrayList<Integer> cards2 = new ArrayList<>();
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11, iv12, iv13, iv14, iv15, iv16;
    Boolean isColorsOk = false;
    Button restartButton; Button pauseButton; Button exitButton;
    int firstCard = -1; int secondCard = 0;
    int clicks = 0;
    TextView fpclicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pair);
        HideSystemUI();
        fpclicks = findViewById(R.id.fpclicks);
        restartButton = findViewById(R.id.restartButton);
        pauseButton = findViewById(R.id.pauseButton);
        exitButton = findViewById(R.id.exitButton);
        Intent intent = getIntent();
        difficulty = intent.getStringExtra("fpdifficulty");
        fpclicks.setText(getResources().getString(R.string.clicks) + "0");

        imageviewelements();
        generator(difficulty);

        buttons();
    }

    public void generator(String difficulty) {
        getCards(difficulty);
        if (difficulty.equals("easy")) {
            isColorsOk = false;
            while (isColorsOk == false) {
                getCards(difficulty);
                isColorsOk = checkColorDifference(cards);
            }
        }
        arrangeCards(cards, difficulty);
        setOnClickListeners(cards2, difficulty);
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
            ivvisibility();
            generator(difficulty);
            ivclickable();
        });
        pauseButton.setOnClickListener(v -> {
            onPause();
            //ToDo: pause menu
        });
        exitButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(FindPair.this, GamesList.class);
            startActivity(intent1);
        });
    }

    public void HideSystemUI() {
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

    public void getCards(@NonNull String difficulty) {
        cards.clear();
        if (difficulty.equals("easy")) {
            for (int i = 0; i < 8; i++) {
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                cards.add(i, color);
            }
        }
        if (difficulty.equals("medium")) {
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
        if (difficulty.equals("hard")) {
            TypedArray hard1 = getResources().obtainTypedArray(R.array.hard1);
            TypedArray hard2 = getResources().obtainTypedArray(R.array.hard2);
            ArrayList<Integer> cardsp2 = new ArrayList<>();
            ArrayList<Integer> rnd = new ArrayList<>();
            while (rnd.size() < 8) {
                int random = new Random().nextInt(hard1.length());
                if (!rnd.contains(random)) {
                    rnd.add(random);
                }
            }
            for (int i = 0; i < 8; i++) {
                cards.add(i, hard1.getResourceId(rnd.get(i), -1));
                cardsp2.add(i, hard2.getResourceId(rnd.get(i), -1));
            }
            cards.addAll(cardsp2);

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

    public void arrangeCards(ArrayList<Integer> cards, @NonNull String difficulty) {
        cards2.addAll(cards);
        if (difficulty.equals("easy") || difficulty.equals("medium")) {
            cards2.addAll(cards);
        }
        Collections.shuffle(cards2);

        if (difficulty.equals("easy")) {
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
        if (difficulty.equals("medium") || difficulty.equals("hard")) {
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

    public void setOnClickListeners(ArrayList<Integer> cards2, @NonNull String difficulty) {
        iv1.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv1.setBackgroundColor(cards2.get(0));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv1.setImageResource(cards2.get(0));
            }
            iv1.setEnabled(false);
            checkCards(0);
        });
        iv2.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv2.setBackgroundColor(cards2.get(1));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv2.setImageResource(cards2.get(1));
            }
            iv2.setEnabled(false);
            checkCards(1);
        });
        iv3.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv3.setBackgroundColor(cards2.get(2));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv3.setImageResource(cards2.get(2));
            }
            iv3.setEnabled(false);
            checkCards(2);
        });
        iv4.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv4.setBackgroundColor(cards2.get(3));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv4.setImageResource(cards2.get(3));
            }
            iv4.setEnabled(false);
            checkCards(3);
        });
        iv5.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv5.setBackgroundColor(cards2.get(4));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv5.setImageResource(cards2.get(4));
            }
            iv5.setEnabled(false);
            checkCards(4);
        });
        iv6.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv6.setBackgroundColor(cards2.get(5));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv6.setImageResource(cards2.get(5));
            }
            iv6.setEnabled(false);
            checkCards(5);
        });
        iv7.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv7.setBackgroundColor(cards2.get(6));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv7.setImageResource(cards2.get(6));
            }
            iv7.setEnabled(false);
            checkCards(6);
        });
        iv8.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv8.setBackgroundColor(cards2.get(7));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv8.setImageResource(cards2.get(7));
            }
            iv8.setEnabled(false);
            checkCards(7);
        });
        iv9.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv9.setBackgroundColor(cards2.get(8));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv9.setImageResource(cards2.get(8));
            }
            iv9.setEnabled(false);
            checkCards(8);
        });
        iv10.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv10.setBackgroundColor(cards2.get(9));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv10.setImageResource(cards2.get(9));
            }
            iv10.setEnabled(false);
            checkCards(9);
        });
        iv11.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv11.setBackgroundColor(cards2.get(10));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv11.setImageResource(cards2.get(10));
            }
            iv11.setEnabled(false);
            checkCards(10);
        });
        iv12.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv12.setBackgroundColor(cards2.get(11));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv12.setImageResource(cards2.get(11));
            }
            iv12.setEnabled(false);
            checkCards(11);
        });
        iv13.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv13.setBackgroundColor(cards2.get(12));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv13.setImageResource(cards2.get(12));
            }
            iv13.setEnabled(false);
            checkCards(12);
        });
        iv14.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv14.setBackgroundColor(cards2.get(13));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv14.setImageResource(cards2.get(13));
            }
            iv14.setEnabled(false);
            checkCards(13);
        });
        iv15.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv15.setBackgroundColor(cards2.get(14));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv15.setImageResource(cards2.get(14));
            }
            iv15.setEnabled(false);
            checkCards(14);
        });
        iv16.setOnClickListener(v -> {
            if (difficulty.equals("easy")) {
                iv16.setBackgroundColor(cards2.get(15));
            } else if (difficulty.equals("medium") || difficulty.equals("hard")) {
                iv16.setImageResource(cards2.get(15));
            }
            iv16.setEnabled(false);
            checkCards(15);
        });
    }

    public void checkCards(int position) {
        if (difficulty.equals("easy") || difficulty.equals("medium")) {
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
                }
                firstCard = -1;
                secondCard = 0;
                ivclickable();
            }
            clicks++;
            fpclicks.setText(getResources().getString(R.string.clicks) + clicks);
        }
        else if (difficulty.equals("hard")) {
            TypedArray hard1 = getResources().obtainTypedArray(R.array.hard1);
            TypedArray hard2 = getResources().obtainTypedArray(R.array.hard2);

            int index = 0; int index2 = 0;
            for (int i = 0; i < hard1.length(); i++) {
                if (hard1.getResourceId(i, 0) == cards2.get(position)) {
                    index = i;
                }
            }
            for (int i = 0; i < hard2.length(); i++) {
                if (hard2.getResourceId(i, 0) == cards2.get(position)) {
                    index2 = i;
                }
            }

            hard1.recycle();
            hard2.recycle();

            if (firstCard == -1) {
                if (index != 0) {
                    firstCard = cards2.indexOf(index);
                }
                else if (index2 != 0) {
                    firstCard = cards2.indexOf(index2);
                }
            } else {
                if (index != 0) {
                    secondCard = cards2.indexOf(index);
                }
                else if (index2 != 0) {
                    secondCard = cards2.indexOf(index2);
                }
                if (firstCard == secondCard) {
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
                }
                firstCard = -1;
                secondCard = 0;
                ivclickable();
            }
            clicks++;
            fpclicks.setText(getResources().getString(R.string.clicks) + clicks);
        }
    }
}