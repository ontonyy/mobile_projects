package com.example.javafirsttry;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;
import java.util.stream.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToe extends AppCompatActivity {
    private TextView ticTacWin;
    private TextView ticTacStep1;
    private TextView ticTacStep2;
    private TextView ticTacStep3;
    private TextView ticTacStep4;
    private TextView ticTacStep5;
    private TextView ticTacStep6;
    private TextView ticTacStep7;
    private TextView ticTacStep8;
    private TextView ticTacStep9;

    private String mainString = "O";

    private HashSet<String> horizontal1;
    private HashSet<String> horizontal2;
    private HashSet<String> horizontal3;
    private HashSet<String> vertical1;
    private HashSet<String> vertical2;
    private HashSet<String> vertical3;
    private HashSet<String> diagonal1;
    private HashSet<String> diagonal2;

    private boolean checkClick = true;

    private Button restartTicTac;
    private Button setPlayerNames;

    private EditText playerName1;
    private EditText playerName2;
    private TextView playerNameText1;
    private TextView playerNameText2;

    private String namePlayer1 = "";
    private String namePlayer2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        ticTacWin = findViewById(R.id.ticTacWin);
        restartTicTac = findViewById(R.id.restartTicTac);
        setPlayerNames = findViewById(R.id.setPlayerNames);
        playerName1 = findViewById(R.id.playerName1);
        playerName2 = findViewById(R.id.playerName2);
        playerNameText1 = findViewById(R.id.playerNameText1);
        playerNameText2 = findViewById(R.id.playerNameText2);

        ticTacStep1 = findViewById(R.id.ticTacStep1);
        ticTacStep2 = findViewById(R.id.ticTacStep2);
        ticTacStep3 = findViewById(R.id.ticTacStep3);
        ticTacStep4 = findViewById(R.id.ticTacStep4);
        ticTacStep5 = findViewById(R.id.ticTacStep5);
        ticTacStep6 = findViewById(R.id.ticTacStep6);
        ticTacStep7 = findViewById(R.id.ticTacStep7);
        ticTacStep8 = findViewById(R.id.ticTacStep8);
        ticTacStep9 = findViewById(R.id.ticTacStep9);

        checkClicks(ticTacStep1);
        checkClicks(ticTacStep2);
        checkClicks(ticTacStep3);
        checkClicks(ticTacStep4);
        checkClicks(ticTacStep5);
        checkClicks(ticTacStep6);
        checkClicks(ticTacStep7);
        checkClicks(ticTacStep8);
        checkClicks(ticTacStep9);

        restartTicTac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartActivity();
            }
        });

        if (playerNameText1.getText().toString().equals("") || playerNameText2.getText().toString().equals("")) {
            playerNameSetting();
            setPlayerNames.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!namePlayer1.equals("")) {
                        playerNameText1.setText("Player 1(X) - " + namePlayer1);
                    } else {
                        playerNameText1.setText("");
                    }
                    if (!namePlayer2.equals("")) {
                        playerNameText2.setText("Player 2(O) - " + namePlayer2);
                    } else {
                        playerNameText2.setText("");
                    }
                }
            });
        }
    }

    public void checkClicks(TextView step) {
        step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageSend()) {
                    if (checkClick) {
                        if (step.getText().toString().contains(" ")) {
                            if (mainString.equals("X")) {
                                mainString = "O";
                            } else {
                                mainString = "X";
                            }
                        }
                        step.setText(mainString);
                        if (checkWin()) {
                            if (mainString.equals("X")) {
                                if (!namePlayer1.equals("")) {
                                    ticTacWin.setText(namePlayer1 + " is win!");
                                } else {
                                    ticTacWin.setText(mainString + " is win!");
                                }
                            } else if (mainString.equals("O")) {
                                if (!namePlayer2.equals("")) {
                                    ticTacWin.setText(namePlayer2 + " is win!");
                                } else {
                                    ticTacWin.setText(mainString + " is win!");
                                }
                            }
                            checkClick = false;
                        }
                    }
                }
            }
        });
    }

    public boolean checkWin() {
        horizontal1 = new HashSet<>(
                Arrays.asList(ticTacStep1.getText().toString(), ticTacStep2.getText().toString(), ticTacStep3.getText().toString()));

        horizontal2 = new HashSet<>(
                Arrays.asList(ticTacStep4.getText().toString(), ticTacStep5.getText().toString(), ticTacStep6.getText().toString()));

        horizontal3 = new HashSet<>(
                Arrays.asList(ticTacStep7.getText().toString(), ticTacStep8.getText().toString(), ticTacStep9.getText().toString()));

        vertical1 = new HashSet<>(
                Arrays.asList(ticTacStep1.getText().toString(), ticTacStep4.getText().toString(), ticTacStep7.getText().toString()));

        vertical2 = new HashSet<>(
                Arrays.asList(ticTacStep2.getText().toString(), ticTacStep5.getText().toString(), ticTacStep8.getText().toString()));

        vertical3 = new HashSet<>(
                Arrays.asList(ticTacStep3.getText().toString(), ticTacStep6.getText().toString(), ticTacStep9.getText().toString()));

        diagonal1 = new HashSet<>(
                Arrays.asList(ticTacStep1.getText().toString(), ticTacStep5.getText().toString(), ticTacStep9.getText().toString()));

        diagonal2 = new HashSet<>(
                Arrays.asList(ticTacStep3.getText().toString(), ticTacStep5.getText().toString(), ticTacStep7.getText().toString()));

        if (checkItemSet(horizontal1) || checkItemSet(horizontal2) || checkItemSet(horizontal3) ||
            checkItemSet(vertical1) || checkItemSet(vertical2) || checkItemSet(vertical3) ||
            checkItemSet(diagonal1) || checkItemSet(diagonal2)) {
            return true;
        }
        return false;
    }

    public boolean checkItemSet(HashSet<String> set) {
        List<String> list = new ArrayList<String>(set);
        if (set.size() == 1 ) {
            if (!list.contains("       ") && !list.contains("         ")) {
                return true;
            }
        }
        return false;
    }

    public void restartActivity() {
        ticTacStep1.setText("       ");
        ticTacStep2.setText("         ");
        ticTacStep3.setText("       ");
        ticTacStep4.setText("       ");
        ticTacStep5.setText("         ");
        ticTacStep6.setText("       ");
        ticTacStep7.setText("       ");
        ticTacStep8.setText("         ");
        ticTacStep9.setText("       ");
        ticTacWin.setText("");
        playerNameText2.setText("");
        playerNameText1.setText("");
        playerName1.setText("");
        playerName2.setText("");
        checkClick = true;
    }

    public void playerNameSetting() {
        playerName1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                namePlayer1 = String.valueOf(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        playerName2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                namePlayer2 = String.valueOf(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public boolean messageSend() {
        boolean canPlay;
        if  (playerNameText1.getText().toString().equals("") && playerNameText2.getText().toString().equals("")) {
            Toast.makeText(this, "Write all players names!", Toast.LENGTH_SHORT).show();
            canPlay = false;
        } else if (playerNameText1.getText().toString().equals("")) {
            Toast.makeText(this, "Write please Player1 name!", Toast.LENGTH_SHORT).show();
            canPlay = false;
        } else if (playerNameText2.getText().toString().equals("")) {
            Toast.makeText(this, "Write please Player2 name!", Toast.LENGTH_SHORT).show();
            canPlay = false;
        } else {
            canPlay = true;
        }
        return canPlay;
    }
}