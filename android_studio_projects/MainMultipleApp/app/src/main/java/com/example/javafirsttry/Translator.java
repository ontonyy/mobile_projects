package com.example.javafirsttry;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

public class Translator extends AppCompatActivity {
    private EditText translateTextEN, translateTextRU;
    private TextView textTranslatedEN, textTranslatedRU;
    private Button buttonToTranslateEN, buttonToTranslateRU;
    FirebaseTranslator englishToRussianTranslator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);

        translateTextEN = findViewById(R.id.translateTextEN);
        textTranslatedEN = findViewById(R.id.textTranslatedEN);
        buttonToTranslateEN = findViewById(R.id.buttonToTranslateEN);

        // on below line we are creating our firebase translate option.
        FirebaseTranslatorOptions options =
                new FirebaseTranslatorOptions.Builder()
                        // below line we are specifying our source language.
                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                        // in below line we are displaying our target language.
                        .setTargetLanguage(FirebaseTranslateLanguage.RU)
                        // after that we are building our options.
                        .build();
        // below line is to get instance
        // for firebase natural language.
        englishToRussianTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(options);

        // adding on click listener for button
        buttonToTranslateEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling method to download language
                // modal to which we have to translate.
                String string = translateTextEN.getText().toString();
                downloadModal(string);
            }
        });
    }

    private void downloadModal(String input) {
        // below line is use to download the modal which
        // we will require to translate in german language
        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().requireWifi().build();

        // below line is use to download our modal.
        englishToRussianTranslator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                // this method is called when modal is downloaded successfully.
                Toast.makeText(getApplicationContext(), "Please wait language modal is being downloaded.", Toast.LENGTH_SHORT).show();

                // calling method to translate our entered text.
                translateLanguage(input);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Fail to download modal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void translateLanguage(String input) {
        englishToRussianTranslator.translate(input).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                textTranslatedEN.setText(s);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Fail to translate", Toast.LENGTH_SHORT).show();
            }
        });
    }
}