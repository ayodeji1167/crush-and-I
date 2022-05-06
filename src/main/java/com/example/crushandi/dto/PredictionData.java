package com.example.crushandi.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PredictionData {

    @NotBlank
    @Size(min = 3 , max = 225)
    private String userName;

    @NotBlank
    @Size(min = 3 , max = 225)
    private String crushName;

    public PredictionData() {
    }

    public PredictionData(String userName, String crushName) {
        this.userName = userName;
        this.crushName = crushName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCrushName() {
        return crushName;
    }

    public void setCrushName(String crushName) {
        this.crushName = crushName;
    }
}
