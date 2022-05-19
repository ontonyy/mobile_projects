package com.example.javafirsttry;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;


public class RegisterUser extends AppCompatActivity {
    private ImageView userImage;
    private Button userImageBtn, userRegister;

    private EditText userName, userEmail, userPassword, userRePassword;

    private RadioButton userMale, userFemale, userGenderX;

    private Spinner userCountries;

    private CheckBox userAgreeLicense;

    private User user;
    private boolean countryBool = false;
    private DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        userImage = findViewById(R.id.RegisterUserImage);
        userImageBtn = findViewById(R.id.pickImageUser);

        userName = findViewById(R.id.RegisterUserName);
        userEmail = findViewById(R.id.RegisterUserEmail);
        userPassword = findViewById(R.id.RegisterUserPassword);
        userRePassword = findViewById(R.id.RegisterUserRePassword);

        userMale = findViewById(R.id.radioMale);
        userFemale = findViewById(R.id.radioFemale);
        userGenderX = findViewById(R.id.radioGenderX);

        userCountries = findViewById(R.id.spinnerCountries);

        userAgreeLicense = findViewById(R.id.RegisterUserAgreeBtn);

        userRegister = findViewById(R.id.registerUserBtn);

        DB = new DatabaseHelper(this);

        user = new User();

        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkEditedText(userName) && checkEditedText(userEmail)
                        && checkEditedText(userPassword) && checkEditedText(userRePassword)
                        && checkRadios() && checkPasswords() && checkCountry()) {
                    user.setName(userName.getText().toString());
                    user.setEmail(userEmail.getText().toString());
                    user.setPassword(userPassword.getText().toString());
                    user.setCountry(userCountries.getSelectedItem().toString());
                    registerUser();
                }
            }
        });

        checkUserImage();

    }

    public void checkUserImage() {
        userImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100){
            Uri imageUri = data.getData();
            userImage.setImageURI(imageUri);
        }
    }

    public boolean checkEditedText(EditText  editText) {
        if (!editText.getText().toString().equals("")) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Write all in plain texts!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkPasswords() {
        if (userPassword.getText().toString().equals(userRePassword.getText().toString())) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Passwords is not same!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkRadios() {
        if (userAgreeLicense.isChecked() &&
                (userMale.isChecked() || userFemale.isChecked() || userGenderX.isChecked())) {
            if (userMale.isChecked()) {
                user.setGender("Male");
            }
            if (userFemale.isChecked()) {
                user.setGender("Female");
            }
            if (userGenderX.isChecked()) {
                user.setGender("Gender X");
            }
            return true;
        } else {
            Toast.makeText(this, "Agree with licanse or choose gender", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkCountry() {
        if (userCountries.getSelectedItem().toString().equals("Norway") && !countryBool) {
            new AlertDialog.Builder(RegisterUser.this)
                    .setMessage("You country is Norway?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "Then change country", Toast.LENGTH_SHORT).show();
                        }
                    }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(), "Ouuu prosti togda", Toast.LENGTH_SHORT).show();
                    countryBool = true;
                }
            }).show();
        } else {
            countryBool = true;
        }
        return countryBool;
    }

    public void fileToWrite() throws IOException {
    }

    public void registerUser() {
        if (!DB.checkUsername(user.getName())) {
            Boolean insert = DB.insertData(user.getName(), user.getEmail(), user.getPassword());
            if(insert){
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
                showSnackBar();
            }else{
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "User is registered!", Toast.LENGTH_SHORT).show();
        }
    }

    public void showSnackBar() {
        Snackbar.make(findViewById(R.id.constraintLayoutRegister), user.toString(), Snackbar.LENGTH_INDEFINITE).setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName.setText("");
                userEmail.setText("");
                userPassword.setText("");
                userRePassword.setText("");
                userAgreeLicense.setChecked(false);
                userMale.setChecked(false);
                userFemale.setChecked(false);
                userGenderX.setChecked(false);
            }
        }).setActionTextColor(Color.CYAN).setTextColor(Color.MAGENTA).show();
    }
}