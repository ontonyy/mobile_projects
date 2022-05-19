package com.example.javafirsttry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class NightModer extends AppCompatActivity {
    private  Button snackBarBtn;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_moder);

        Switch switchMode = findViewById(R.id.switchMode);
        snackBarBtn = findViewById(R.id.snackBarBtn);
        parent = findViewById(R.id.constraintParent);

        checkBattery();

        snackBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackBar();
            }
        });

        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }

    public void showSnackBar() {
        Snackbar.make(parent, "HAHA my snack here, yes?", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(NightModer.this, "DA DA JA LOH", Toast.LENGTH_SHORT).show();
                    }
                }).setActionTextColor(Color.CYAN).setTextColor(Color.MAGENTA).show();
    }

    public void checkBattery() {
        BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
        int percentage = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        Toast.makeText(this, "Battery Percentage is "+percentage+" %", Toast.LENGTH_SHORT).show();


    }
}