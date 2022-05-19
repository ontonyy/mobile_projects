package com.example.javafirsttry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Skammer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skammer);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.funny);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://www.youtube.com/watch?v=AHaZF-0uLSc");
            }
        });

    }

    public void onBtnClick(View view) {

        TextView nameView = findViewById(R.id.nameView);
        TextView cardView = findViewById(R.id.cardView);
        TextView emailView = findViewById(R.id.emailView);

        EditText nameEdit = findViewById(R.id.nameEdit);
        EditText cardEdit = findViewById(R.id.cardEdit);
        EditText emailEdit = findViewById(R.id.emailEdit);

        if (nameEdit.getText().toString().equals("antonloh")) {
            nameView.setText("SAM LOH");
        } else if (!nameEdit.getText().toString().equals("")) {
            nameView.setText("Hello, " + nameEdit.getText().toString());
        }
        if (!cardEdit.getText().toString().equals("")) {
            cardView.setText("Nothing will happen, " + cardEdit.getText().toString());
        }
        if (!emailEdit.getText().toString().equals("")) {
            emailView.setText("Tut ladna - " + emailEdit.getText().toString());
        }

        Button btn = findViewById(R.id.button);
        if (btn.getText().toString().equals("CHTOOO")) {
            btn.setText("KAVOOO");
        } else {
            btn.setText("CHTOOO");
        }
    }

    public void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}