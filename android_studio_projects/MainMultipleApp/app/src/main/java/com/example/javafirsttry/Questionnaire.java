package com.example.javafirsttry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Questionnaire extends AppCompatActivity {
    private int correctResponses = 0;
    private RadioGroup radioGroup;
    private CheckBox checkMom;
    private CheckBox checkDad;
    private CheckBox checkBros;
    private CheckBox checkBublik;
    private TextView finalResult;
    private RadioButton antonId;
    private TextView molodecView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);


        radioGroup = findViewById(R.id.radioGroup);

        checkMom = findViewById(R.id.checkMom);
        checkDad = findViewById(R.id.checkDad);
        checkBros = findViewById(R.id.checkBros);
        checkBublik = findViewById(R.id.checkBublik);
        finalResult = findViewById(R.id.finalResult);
        molodecView = findViewById(R.id.molodecView);
        antonId = findViewById(R.id.antonId);
        Button responseBtn = findViewById(R.id.responseBtn);


        responseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswers();
            }
        });

    }

    public void checkAnswers() {
        correctResponses = 0;

        if (checkMom.isChecked()) correctResponses++;
        if (checkDad.isChecked()) correctResponses++;
        if (checkBros.isChecked()) correctResponses++;
        if (checkBublik.isChecked()) correctResponses++;
        if (antonId.isChecked()) correctResponses++;

        finalResult.setText("In final you have - " + Integer.toString(correctResponses) + "/5");

        if (correctResponses == 5) {
            molodecView.setText("MOLODEC, you can go to the next page");
        } else if (correctResponses == 0) {
            molodecView.setText("Nui dura4ok kone4no");
        }
    }
}