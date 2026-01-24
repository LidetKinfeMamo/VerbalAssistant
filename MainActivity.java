package com.example.verbalassistant.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.verbalassistant.R;
import com.example.verbalassistant.data.EmergencyType;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        findViewById(R.id.chokingBtn)
                .setOnClickListener(v -> open(EmergencyType.CHOKING));

        findViewById(R.id.strokeBtn)
                .setOnClickListener(v -> open(EmergencyType.STROKE));

        findViewById(R.id.seizureBtn)
                .setOnClickListener(v -> open(EmergencyType.SEIZURE));

        findViewById(R.id.callBtn)
                .setOnClickListener(v ->
                        startActivity(new Intent(Intent.ACTION_DIAL,
                                Uri.parse("tel:939")))
                );
    }

    private void open(EmergencyType type) {

        Intent i = new Intent(this, InfoActivity.class);
        i.putExtra("TYPE", type.name());
        startActivity(i);
    }
