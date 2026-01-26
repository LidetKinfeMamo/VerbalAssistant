package com.example.verbalassistant.data;

import java.util.Arrays;
import java.util.List;

public class EmergencyRepository {
    public static EmergencyInfo getInfo(EmergencyType type) {
        switch (type) {
            case STROKE:
                return new EmergencyInfo(
                        Arrays.asList(
                                "F.A.S.T\n F: Face Drooping - Ask them to smile\n",
                                "A: Arm Weakness - Ask them to raise both arms\n",
                                "S: Speech Difficulty - Speech is slurred or strange. Ask them to repeat a simple phrase\n",
                                "T: Time to Call Emergency - If you see any of these signs, call emergency services immediately\n"
                        ),
                        "Check the person's face, arms, and speech. If they show any signs of a stroke, call emergency services immediately and note the time symptoms started."
                );
            case SEIZURE:
                return new EmergencyInfo(
                        Arrays.asList("Uncontrolled shaking", "Loss of consciousness"),
                        "Stay calm\n Protect from injury by clearing the area\n Cushion their head\n After the seizure stops, turn the person on their side to help breathing\n Do NOT put anything in their mouth\n Do NOT try to hold them down or stop their movements\n Call emergency if seizure lasts more than 5 minutes"
                );
            case CHOKING:
                return new EmergencyInfo(
                        Arrays.asList("Unable to breathe", "Clutching throat"),
                        "Stand behind them and wrap your arms around their waist\n Make a fist and place your fist above the person's navel below the ribcage\n Grasp your fist and give quick upward thrusts. Repeat until object is dislodged\n If person is pregnant or obese, give chest thrust instead\n Call emergency services immediately if choking is severe"
                );
            default:
                return null;
        }
    }
}

