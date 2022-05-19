package com.example.javafirsttry;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class CaloriesCalculator extends AppCompatActivity {
    private EditText calculatorAge, calculatorWeight, calculatorHeight;
    private RadioButton radioLbs, radioFeet, calRadioMale, calRadioFemale;
    private TextView weightMeasure, heightMeasure, genderMeasure;
    private Button calculateCalories;
    int age, weight, height, calories;
    private Spinner humanActivities;

    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_calculator);

        calculatorAge = findViewById(R.id.calculatorAge);
        calculatorWeight = findViewById(R.id.calculatorWeight);
        calculatorHeight = findViewById(R.id.calculatorHeight);

        radioLbs = findViewById(R.id.radioLBS);
        radioFeet = findViewById(R.id.radioFeet);
        calRadioMale = findViewById(R.id.calRadioMale);
        calRadioFemale = findViewById(R.id.calRadioFemale);

        weightMeasure = findViewById(R.id.weightMeasure);
        heightMeasure = findViewById(R.id.heightMeasure);
        genderMeasure = findViewById(R.id.genderMeasure);
        calculateCalories = findViewById(R.id.calculateCalories);
        humanActivities = findViewById(R.id.humanActivities);

        calculateCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAge() && checkWeight() && checkHeight()) {
                    calculateCal();
                }
            }
        });

    }

    public boolean checkAge() {
        if (!calculatorAge.getText().toString().equals("")
                && calculatorAge.getText().toString().matches("-?\\d+")) {
            age = Integer.parseInt(calculatorAge.getText().toString());
            return true;
        } else {
            Toast.makeText(this, "Write correct age!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkWeight() {
        if (!calculatorWeight.getText().toString().equals("")
                && calculatorWeight.getText().toString().matches("-?\\d+")) {
            weight = Integer.parseInt(calculatorWeight.getText().toString());
            return true;
        } else {
            Toast.makeText(this, "Write correct weight!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkHeight() {
        if (!calculatorHeight.getText().toString().equals("")
                && calculatorHeight.getText().toString().matches("-?\\d+")) {
            height = Integer.parseInt(calculatorHeight.getText().toString());
            return true;
        } else {
            Toast.makeText(this, "Write correct height!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public int activityPercentage() {
        if (humanActivities.getSelectedItem().toString().contains("Every")) {
            return 900;
        } else if (humanActivities.getSelectedItem().toString().contains("3")) {
            return 600;
        } else if (humanActivities.getSelectedItem().toString().contains("1")) {
            return 300;
        } else {
            return 100;
        }
    }

    public void calculateCal() {
        // for Male BMR = 66 + (13.7 x WEIGHT_IN_KILOGRAM) + (5 x HEIGHT_IN_CENTIMETER) - (6.8 x AGE_IN_YEAR)
        // for Female BMR = 655 + (9.6 x WEIGHT_IN_KILOGRAM) + (1.8 x HEIGHT_IN_CENTIMETER) - (4.7 x AGE_IN_YEAR)
        if (radioLbs.isChecked()) {
            weight = (int) (weight * 0.45359237);
        }
        if (radioFeet.isChecked()) {
            height = (int) (height * 30.48);
        }

        if (calRadioMale.isChecked()) {
            calories = (int) (266 + (13.7 * weight) + (5 * height) - (6.8 * age));
        } else {
            calories = (int) (855 + (9.6 * weight) + (1.8 * height) - (4.7 * age));
        }

        Toast.makeText(getApplicationContext(), "Per day | your calories - " + (calories + activityPercentage()), Toast.LENGTH_SHORT).show();
    }

    public void checkRadios() {
        if (radioLbs.isChecked()) {
            weightMeasure.setText("Lb");
        } else {
            weightMeasure.setText("Kg");
        }

        if (radioFeet.isChecked()) {
            Toast.makeText(this, "Write height with dot.", Toast.LENGTH_SHORT).show();
            heightMeasure.setText("Feet");
        } else {
            heightMeasure.setText("Cm");
        }

        if (calRadioFemale.isChecked()) {
            genderMeasure.setText("Your gender: Female");
        } else {
            genderMeasure.setText("Your gender: Male");
        }
    }

    @Override
    protected void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, 500);
                checkRadios();
            }
        }, 500);
        super.onResume();
    }
}