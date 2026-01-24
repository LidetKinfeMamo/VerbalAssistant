public class EmergencyRepository {

    public static EmergencyInfo getInfo(EmergencyType type) {

        switch (type) {

            case CHOKING:
                return new EmergencyInfo(
                        List.of(
                                "Difficulty breathing",
                                "Clutching the throat",
                                "Unable to speak or cough"
                        ),
                        "Stand behind the person. Place your fist above the navel and perform abdominal thrusts until the object is expelled."
                );

            case STROKE:
                return new EmergencyInfo(
                        List.of(
                                "Face drooping",
                                "Arm weakness",
                                "Slurred speech"
                        ),
                        "Call emergency services immediately. Keep the person calm, lying on their side, and do not give food or drink."
                );

            case SEIZURE:
                return new EmergencyInfo(
                        List.of(
                                "Uncontrolled shaking",
                                "Loss of consciousness",
                                "Muscle stiffness"
                        ),
                        "Clear nearby objects. Do not restrain the person. After the seizure, place them on their side."
                );
        }
        return null;
    }
}
