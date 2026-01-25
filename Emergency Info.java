package com.example.verbalassistant.data;

import java.util.List;

public class EmergencyInfo {

    public List<String> symptoms;
    public String instructions;

    public EmergencyInfo(List<String> symptoms, String instructions) {

        this.symptoms = symptoms;
        this.instructions = instructions;
          }
}


