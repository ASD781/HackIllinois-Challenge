package com.example.hackillinoischallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    String id, name, description, startTime, endTime, sponsor;
    boolean interested;
    TextView tName, tTime, tDescription, tSponsor;
    Switch sInterested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().hide();

        tName = findViewById(R.id.id_tName);
        tTime = findViewById(R.id.id_tTime);
        tDescription = findViewById(R.id.id_tDescription);
        tSponsor = findViewById(R.id.id_tSponsor);
        sInterested = findViewById(R.id.id_sInterested);

        loadIntent();
        loadUI();
    }

    public void loadIntent() {
        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        description = intent.getStringExtra("description");
        startTime = intent.getStringExtra("startTime");
        endTime = intent.getStringExtra("endTime");
        sponsor = intent.getStringExtra("sponsor");
        interested = intent.getBooleanExtra("interested", false);
    }

    public void loadUI() {
        tName.setText(name);
        tTime.setText(startTime + " to " + endTime);
        tDescription.setText(description);

        if (sponsor.equals("")) {
            tSponsor.setVisibility(View.INVISIBLE);
        } else {
            tSponsor.setText("Sponsor: " + sponsor);
        }

        if (interested) {
            sInterested.setChecked(true);
        }
    }
}
