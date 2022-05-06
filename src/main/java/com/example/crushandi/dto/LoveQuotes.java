package com.example.crushandi.dto;


import java.util.List;

public class LoveQuotes {
    private List<String> loveQuotes;

    public LoveQuotes(List<String> loveQuotes) {
        this.loveQuotes = loveQuotes;
    }

    public LoveQuotes() {
    }

    public List<String> getLoveQuotes() {
        return loveQuotes;
    }

    public void setLoveQuotes(List<String> loveQuotes) {
        this.loveQuotes = loveQuotes;
    }
}
