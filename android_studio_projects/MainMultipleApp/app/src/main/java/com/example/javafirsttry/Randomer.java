package com.example.javafirsttry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class Randomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomer);

        Button rollButton = findViewById(R.id.rollButton);
        TextView resultsTextView = findViewById(R.id.resultsTextView);
        SeekBar seekBar = findViewById(R.id.seekBar);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.GONE);
                int intRand = new Random().nextInt(seekBar.getProgress());
                resultsTextView.setText(Integer.toString(intRand));
            }
        });

    }

}