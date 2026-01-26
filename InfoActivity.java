package com.example.verbalassistant.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.verbalassistant.R;
import com.example.verbalassistant.data.*;
import java.util.Locale;

public class InfoActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private String fullSpeech;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_info);


        TextView titleView = findViewById(R.id.detailTitle);
        TextView symptomsView = findViewById(R.id.symptomsText);
        TextView instructionsView = findViewById(R.id.instructionsText);


        String typeStr = getIntent().getStringExtra("TYPE");
        if (typeStr == null) return;

        EmergencyType type = EmergencyType.valueOf(typeStr);
        EmergencyInfo info = EmergencyRepository.getInfo(type);


        titleView.setText(type.name());

        StringBuilder symptomsTextBuilder = new StringBuilder("Symptoms:\n");
        StringBuilder symptomsSpeechBuilder = new StringBuilder("Symptoms include: ");

        for (int i = 0; i < info.symptoms.size(); i++) {
            String s = info.symptoms.get(i);
            symptomsTextBuilder.append("- ").append(s).append("\n");
            symptomsSpeechBuilder.append(s);
            if (i < info.symptoms.size() - 1) {
                symptomsSpeechBuilder.append(", ");
            } else {
                symptomsSpeechBuilder.append(". ");
            }
        }

        symptomsView.setText(symptomsTextBuilder.toString());
        instructionsView.setText("First Aid:\n" + info.instructions);


        fullSpeech = symptomsSpeechBuilder.toString() + " First Aid: " + info.instructions;



        findViewById(R.id.backBtn).setOnClickListener(v -> finish());


        findViewById(R.id.callEmergencyBtn).setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:939"));
            startActivity(callIntent);
        });


        findViewById(R.id.replayBtn).setOnClickListener(v -> {
            if (tts != null) {
                tts.speak(fullSpeech, TextToSpeech.QUEUE_FLUSH, null, "REPLAY_TTS");
            }
        });


        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = tts.setLanguage(Locale.US);
                if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Short delay before speaking for better UX
                    tts.playSilentUtterance(500, TextToSpeech.QUEUE_FLUSH, null);
                    tts.speak(fullSpeech, TextToSpeech.QUEUE_ADD, null, "FullSpeechID");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}



