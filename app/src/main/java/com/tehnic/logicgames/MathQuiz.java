package com.tehnic.logicgames;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.games.PlayGames;

import java.util.Objects;
import java.util.Random;

public class MathQuiz extends AppCompatActivity {
    int numberOne = 0, numberTwo = 0, answer, score = 0;
    String DIFFICULTY, OperationIsPossible = "no", SelectedOperation, Equation, operation = "non";
    TextView equation, timer;
    EditText answerInput;
    Button restartButton, pauseButton, exitButton;
    CountDownTimer countDownTimer;
    int remainingTime = 61000;
    boolean isPaused = false;
    public static final String EASY_ID = "CgkIwLuZ7LMdEAIQBA";
    public static final String MEDIUM_ID = "CgkIwLuZ7LMdEAIQBQ";
    public static final String HARD_ID = "CgkIwLuZ7LMdEAIQBg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_quiz);
        hideSystemUI();
        Intent intent = getIntent();
        DIFFICULTY = intent.getStringExtra("mqdifficulty");

        restartButton = findViewById(R.id.restartButton);
        pauseButton = findViewById(R.id.pauseButton);
        exitButton = findViewById(R.id.exitButton);

        buttons();
    }

    public void onPause() {
        super.onPause();
        if (!isPaused) {
            pauseButton();
        }
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

    protected void onStart() {
        super.onStart();
        equation = findViewById(R.id.equation);
        timer = findViewById(R.id.timer);

        if (equation.getText().toString().trim().equals("2+2 =")) {
            Equation = generator(DIFFICULTY);
            equation.setText(Equation);
            timer(61000);
        } else {
            Equation = generator(DIFFICULTY);
            equation.setText(Equation);
        }
    }
    public String generator (String difficulty) {
        operation = operationSelect(difficulty); //Выбор операций по сложности
        SelectedOperation = selectAChar(operation, difficulty); //Случайная операция из данных
        numberOne = randomNumberOne(difficulty, SelectedOperation);
        numberTwo = randomNumberTwo(difficulty, SelectedOperation);
        OperationIsPossible = isOperationPossible(SelectedOperation, numberOne, numberTwo, difficulty);

        if (OperationIsPossible.equals("yes")) {
            switch (SelectedOperation) {
                case "+":
                    answer = Math.addExact(numberOne, numberTwo);
                    break;
                case "-":
                    answer = Math.subtractExact(numberOne, numberTwo);
                    break;
                case "*":
                    answer = Math.multiplyExact(numberOne, numberTwo);
                    break;
                case "/":
                    answer = numberOne;
                    break;
            }
        }
        else if (OperationIsPossible.equals("no")) {
            generator(difficulty);
        }
        Equation = mergeEquation(SelectedOperation, numberOne, numberTwo);
        return Equation;
    }
    public String operationSelect (@NonNull String difficulty) {
        switch (difficulty) {
            case "easy":
                operation = "+-";
                break;
            case "medium":
                operation = "*/";
                break;
            case "hard":
                operation = "+-*/";
                break;
        }
        return operation;
    }
    public String selectAChar(@NonNull String operation, String difficulty){
        operationSelect(difficulty);
        Random random = new Random();
        int index = random.nextInt(operation.length());
        char krya = operation.charAt(index);
        SelectedOperation = String.valueOf(krya);
        return SelectedOperation;
    }
    public int randomNumberOne (@NonNull String difficulty, String SelectedOperation) {
        switch (difficulty) {
            case "easy": numberOne = (int) (Math.random() * 60 - 30); break;//От -30 до 30
            case "medium":
                if (SelectedOperation.equals("*") || SelectedOperation.equals("/")) {
                    numberOne = (int) (Math.random()*30-15);
                }
                else {
                    numberOne = (int) (Math.random() * 200 - 100); //От -100 до 100
                }
                break;
            case "hard":
                if (SelectedOperation.equals("*") || SelectedOperation.equals("/")) {
                    numberOne = (int) (Math.random()*50-25);
                }
                else {
                    numberOne = (int) (Math.random() * 600 - 300); //От -300 до 300
                }
                break;
        }
        return numberOne;
    }
    public int randomNumberTwo (@NonNull String difficulty, String SelectedOperation) {
        switch(difficulty) {
            case "easy": numberTwo = (int) (Math.random() * 30); break;//От 0 до 30
            case "medium":
                if (SelectedOperation.equals("*") || SelectedOperation.equals("/")) {
                    numberTwo = (int) (Math.random()*15);
                }
                else {
                    numberTwo = (int) (Math.random() * 100); //От 0 до 100
                }
                break;
            case "hard":
                if (SelectedOperation.equals("*") || SelectedOperation.equals("/")) {
                    numberTwo = (int) (Math.random()*25);
                }
                else {
                    numberTwo = (int) (Math.random() * 300); //От 0 до 300
                }
                break;
        }
        return numberTwo;
    }
    public String isOperationPossible (@NonNull String SelectedOperation, int numberOne, int numberTwo, String difficulty) {
        switch (SelectedOperation) {
            case "*":
            case "/":
                if (numberOne == 0 || numberTwo == 0) {
                    OperationIsPossible = "no";
                }
                else {
                    if (difficulty.equals("medium")) {
                        if (((numberOne * numberTwo) < 500) && ((numberOne * numberTwo) > -500)) {
                            OperationIsPossible = "yes"; // numberOne * numberTwo = [-500; 500]
                        } else OperationIsPossible = "no";
                    }
                    if (difficulty.equals("hard")) {
                        if ((numberOne * numberTwo) < 1000 && ((numberOne * numberTwo) > -1000)) {
                            OperationIsPossible = "yes"; // numberOne * numberTwo = [-1000; 1000]
                        } else OperationIsPossible = "no";
                    }
                }
                break;
            case "-":
            case "+":
                OperationIsPossible = "yes";
                break;
        }
        return OperationIsPossible;
    }
    public String mergeEquation (@NonNull String SelectedOperation, int numberOne, int numberTwo) {
        int numberOnes;
        if (SelectedOperation.equals("/")) {
            numberOnes = (numberOne*numberTwo);
            Equation = numberOnes+" "+SelectedOperation+" "+numberTwo+" =";
        }
        else Equation = numberOne+" "+SelectedOperation+" "+numberTwo+" =";
        return Equation;
    }

    public void timer (int startingTime) {
        countDownTimer = new CountDownTimer(startingTime, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000));
                remainingTime = (int) millisUntilFinished;
                answerInput = findViewById(R.id.response);
                if (answerInput.getText().toString().length() == String.valueOf(answer).length()) {
                    if (answerInput.getText().toString().equals(String.valueOf(answer))) {
                        answerInput.getText().clear();
                        score++;
                        Equation = generator(DIFFICULTY);
                        equation.setText(Equation);
                    }
                    else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(100, 255));
                        }
                        else {
                            ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(150);
                        }
                        answerInput.getText().clear();
                    }
                }
            }
            @Override
            public void onFinish() {
                timer.setText("0");
                leaderboard(score);
                AlertDialog.Builder builder = new AlertDialog.Builder(MathQuiz.this);
                builder.setTitle("Game Over");
                builder.setMessage("Your score is: " + score);
                builder.setCancelable(false);
                builder.setPositiveButton("OK", (dialog, which) -> {
                    Intent intent = new Intent(MathQuiz.this, MainActivity.class);
                    startActivity(intent);
                });
                builder.setNegativeButton("Restart", (dialog, which) -> {
                    Intent intent = new Intent(MathQuiz.this, MathQuiz.class);
                    intent.putExtra("mqdifficulty", DIFFICULTY);
                    startActivity(intent);
                });
                builder.show();
            }
        }.start();
    }
    public void buttons() {
        //ToDo: add confirmation dialog for all buttons
        restartButton.setOnClickListener(v -> {
            score = 0;
            equation.setText("2+2 =");
            countDownTimer.cancel();
            onStart();
        });
        pauseButton.setOnClickListener(v -> {
            pauseButton();
        });
        exitButton.setOnClickListener(v -> {
            countDownTimer.cancel();
            startActivity(new Intent(MathQuiz.this, GamesList.class));
            finish();
        });
    }

    public void onBackPressed() {
        countDownTimer.cancel();
        startActivity(new Intent(MathQuiz.this, GamesList.class));
        finish();
    }

    public void pauseButton () {
        if (isPaused) {
            isPaused = false;
            timer(remainingTime);
            timer.setTextSize(96);
            equation.setVisibility(View.VISIBLE);
            answerInput.setVisibility(View.VISIBLE);
            restartButton.setVisibility(View.VISIBLE);
            exitButton.setVisibility(View.VISIBLE);
        }
        else {
            isPaused = true;
            countDownTimer.cancel();
            timer.setTextSize(48);
            timer.setText(getResources().getString(R.string.paused));
            equation.setVisibility(View.INVISIBLE);
            answerInput.setVisibility(View.INVISIBLE);
            restartButton.setVisibility(View.INVISIBLE);
            exitButton.setVisibility(View.INVISIBLE);
        }
    }

    public void leaderboard(int score) {
        switch (DIFFICULTY) {
            case "easy":
                PlayGames.getLeaderboardsClient(this)
                        .submitScore(EASY_ID, score);
                break;
            case "medium":
                PlayGames.getLeaderboardsClient(this)
                        .submitScore(MEDIUM_ID, score);
                break;
            case "hard":
                PlayGames.getLeaderboardsClient(this)
                        .submitScore(HARD_ID, score);
                break;
        }
    }
}