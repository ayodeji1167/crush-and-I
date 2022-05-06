package com.example.crushandi.dto;

import java.util.List;

public class PickUpLines {
    List<String> pickUpLines;

    public PickUpLines() {
    }

    public PickUpLines(List<String> pickUpLines) {
        this.pickUpLines = pickUpLines;
    }

    public List<String> getPickUpLines() {
        return pickUpLines;
    }

    public void setPickUpLines(List<String> pickUpLines) {
        this.pickUpLines = pickUpLines;
    }
}
