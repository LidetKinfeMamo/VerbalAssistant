package com.example.verbalassistant.ui;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.verbalassistant.R;
import com.example.verbalassistant.data.*;

import java.util.Locale;

public class InfoActivity extends AppCompatActivity {

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_info);

        TextView symptomsView = findViewById(R.id.symptomsText);
        TextView instructionsView = findViewById(R.id.instructionsText);

        EmergencyType type =
                EmergencyType.valueOf(getIntent().getStringExtra("TYPE"));

        EmergencyInfo info = EmergencyRepository.getInfo(type);

        StringBuilder symptomsText = new StringBuilder("Symptoms:\n");
        for (String s : info.symptoms)
            symptomsText.append("- ").append(s).append("\n");

        symptomsView.setText(symptomsText.toString());
        instructionsView.setText("First Aid:\n" + info.instructions);

        tts = new TextToSpeech(this, status -> {
            tts.setLanguage(Locale.US);
            tts.speak(info.instructions,
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null);
        });
    }
}

