package com.example.javafirsttry;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView activities;
    int lastPosition;
    ArrayList<String> listItems;
    ListViewAdapter listViewAdapter;
    ArrayList<String> items;

    Button btnLoadMore;

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listItems = new ArrayList<String>();
        items = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.all_activities)));
        activities = (ListView) findViewById(R.id.all_activities);
        popuLateList();

        listViewAdapter = new ListViewAdapter(this, listItems);
        activities.setAdapter(listViewAdapter);
        btnLoadMore = new Button(this);
        btnLoadMore.setText("Load more");

        activities.addFooterView(btnLoadMore);
        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                loadMoreItems();
            }
        });

        activities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        openSkammerActivity();
                        break;
                    case 1:
                        openRandomerActivity();
                        break;
                    case 2:
                        openEmptyFielderActivity();
                        break;
                    case 3:
                        openQuestionnaireActivity();
                        break;
                    case 4:
                        openTodoNotesActivity();
                        break;
                    case 5:
                        openNightModerActivity();
                        break;
                    case 6:
                        openTicTacToeActivity();
                        break;
                    case 7:
                        openColorGuessingGameActivity();
                        break;
                    case 8:
                        openRegisterUserActivity();
                        break;
                    case 9:
                        openCaloriesCalculatorActivity();
                        break;
                    case 10:
                        openOnBoardingActivity();
                        break;
                    case 11:
                        openTranslatorActivity();
                        break;
                }
            }
        });
    }

    private void loadMoreItems() {
        if (items.size() > 0) {
            lastPosition = activities.getFirstVisiblePosition();
            listItems.addAll(items);
            activities.setSelection(lastPosition+1);
            items.removeAll(listItems);
            setAdapter();
        }
    }

    private void setAdapter() {
        listViewAdapter=new ListViewAdapter(this, listItems);
        activities.setAdapter(listViewAdapter);
    }

    private void popuLateList() {
        for(int i=0 ; i < 8; i++)
        {
            listItems.add(items.get(i));
        }
        items.removeAll(listItems);
        setAdapter();
    }

    public void openSkammerActivity() {
        Intent intent = new Intent(this, Skammer.class);
        startActivity(intent);
    }
    public void openRandomerActivity() {
        Intent intent = new Intent(this, Randomer.class);
        startActivity(intent);
    }
    public void openEmptyFielderActivity() {
        Intent intent = new Intent(this, BoysClub.class);
        startActivity(intent);
    }
    public void openQuestionnaireActivity() {
        Intent intent = new Intent(this, Questionnaire.class);
        startActivity(intent);
    }
    public void openTodoNotesActivity() {
        Intent intent = new Intent(this, TodoNotesActivity.class);
        startActivity(intent);
    }
    public void openNightModerActivity() {
        Intent intent = new Intent(this, NightModer.class);
        startActivity(intent);
    }

    public void openTicTacToeActivity() {
        Intent intent = new Intent(this, TicTacToe.class);
        startActivity(intent);
    }

    public void openColorGuessingGameActivity() {
        Intent intent = new Intent(this, ColorGuessingGame.class);
        startActivity(intent);
    }

    public void openRegisterUserActivity() {
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }

    public void openCaloriesCalculatorActivity() {
        Intent intent = new Intent(this, CaloriesCalculator.class);
        startActivity(intent);
    }

    public void openOnBoardingActivity() {
        Intent intent = new Intent(this, OnBoardingScreen.class);
        startActivity(intent);
    }
    public void openTranslatorActivity() {
        Intent intent = new Intent(this, Translator.class);
        startActivity(intent);
    }

}