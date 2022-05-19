package com.example.javafirsttry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BoysClub extends AppCompatActivity {
    private FloatingActionButton fab;
    private TextView clickTextView;
    private int clicker;
    private RecyclerView contactsRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boys_club);

        ProgressBar progressBar = findViewById(R.id.HorizontalProgressBar);
        clickTextView = findViewById(R.id.clickTextView);
        fab = findViewById(R.id.floatingClickBtn);
        contactsRecView = findViewById(R.id.contactsRecyclerView);

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Dwain Skala", "skala@gmail.com", "https://sun9-29.userapi.com/s/v1/if2/_OPp1YH-oVJ_WJs6c2niM25aACW13jC4RzyjEMBpsh0GU05TBdXxat2o_9QV5hS7Qr0hlN5OgnVsx_eo14-UbVD3.jpg?size=1080x1080&quality=96&type=album"));
        contacts.add(new Contact("Men in black", "menInBlack@gmail.com", "https://sun9-66.userapi.com/s/v1/if2/r_AMDhzkPb698ozJJVnZ_Lp4ISa4UfZCN9MO9TSkxHtVM50CyFk6alRBlsChNHlIFZqXEeEjd57eT_zNebbfynyt.jpg?size=500x500&quality=96&type=album"));
        contacts.add(new Contact("Simple monkey", "monkey@gmail.com", "https://sun6-23.userapi.com/s/v1/if2/IkgjO8btlRybr-QmwExOLZMXC65uD0m7T0nXgWMO6lgYb7izVNzuMKfnAPFrL_R3PwcTsbA8ejJcp_IJCD3kq9Hx.jpg?size=1080x952&quality=96&type=album"));
        contacts.add(new Contact("Reverse", "reverse@gmail.com", "https://sun9-48.userapi.com/s/v1/if2/w0kGSavRsWtRiW6Fk89oVf_aCDUB4-gxx95_XJhghINAEnZBrhLdn2kjqQlkJphN8Pemz7FSMjEMDx_ieA2EbypM.jpg?size=248x380&quality=95&type=album"));

        ContactsRecViewAdapter adapter = new ContactsRecViewAdapter(this);
        adapter.setContacts(contacts);

        contactsRecView.setAdapter(adapter);
        contactsRecView.setLayoutManager(new GridLayoutManager(this, 2));




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicker += 1;
                clickTextView.setText(Integer.toString(clicker));
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    progressBar.incrementProgressBy(1);
                    SystemClock.sleep(50);
                }
            }
        });
        thread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.empty_field_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alarm_settings:
                Toast.makeText(this, "Dzin-Dzin, alarm is ON!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings_menu:
                Toast.makeText(this, "In future maybe here you can change app view", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}