package com.example.crushandi.dto;

public class ReturnedResult {
    private String result;
    private int percentage;

    public ReturnedResult() {
    }

    public ReturnedResult(String result, int percentage) {
        this.result = result;
        this.percentage = percentage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
