package com.example.javafirsttry;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

public class ColorGuessingGame extends AppCompatActivity {

    private View colorGuessSquare;
    private TextView timerColor;
    private EditText colorTypeText;
    private Button colorGuessBtn;
    private Button restartColorGame;
    private TextView colorAnswer;
    private TextView attemptsCount;
    private LinkedHashMap<String, Integer> colors = new LinkedHashMap<>();
    private int seconds;
    private int attemptsCounter;
    private boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_guessing_game);

        colorGuessSquare = findViewById(R.id.colorGuessSquare);
        timerColor = findViewById(R.id.timerColor);
        colorTypeText = findViewById(R.id.colorTypeText);
        colorGuessBtn = findViewById(R.id.colorGuessBtn);
        restartColorGame = findViewById(R.id.restartColorGame);
        colorAnswer = findViewById(R.id.colorAnswer);
        attemptsCount = findViewById(R.id.attemptsCounter);

        attemptsCounter = 1;
        putElements();
        checkTypedWord();
        runTimer();

    }

    public void checkTypedWord() {

        restartColorGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });

        colorTypeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                colorGuessBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        attemptsCounter++;
                        attemptsCount.setText(Integer.toString(attemptsCounter));
                        String color = (String) colors.keySet().toArray()[0];
                        if (String.valueOf(charSequence).equals(color)) {
                            colorAnswer.setText("LETTS'GOO it is - " + color);
                            colorTypeText.setText("");
                            colors.remove(color);
                            if (colors.size() == 0) {
                                running = false;
                                colorAnswer.setText("YOU WIN, and your time - " + timerColor.getText().toString()
                                        + "\nYour attempts: " + attemptsCounter);
                            } else {
                                colorGuessSquare.setBackgroundColor(colors.get(colors.keySet().toArray()[0]));
                            }
                        } else {
                            colorAnswer.setText("Think more...first letter is - " + color.charAt(0));
                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void runTimer()
    {

        final Handler handler = new Handler();
        seconds = 0;

        handler.post(new Runnable() {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),
                                "%d:%02d:%02d", hours,
                                minutes, secs);

                timerColor.setText(time);
                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    public void putElements() {
        colors.put("black", 0xFF000000);
        colors.put("blue", 0xFF03DAC5);
        colors.put("yellow", 0xFFFFFF00);
        colors.put("green", 0xFF00FF00);
        colors.put("purple", 0xFF6200EE);
        colors.put("white", 0xFFFFFFFF);
        colors.put("orange", 0xFFFF6600);
        colors.put("grey", 0xFF878787);
        colors.put("pink", 0xFFFF66FF);
    }

    public void restartGame() {
        colorGuessSquare.setBackgroundColor(0xFF000000);
        colorAnswer.setText("");
        colorTypeText.setText("");
        attemptsCounter = 1;
        putElements();
        checkTypedWord();
        runTimer();
    }
}