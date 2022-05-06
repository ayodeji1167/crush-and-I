package com.example.crushandi.service;

import org.springframework.stereotype.Service;

@Service
public class PredictionService {

    public String result(String userName, String crushName) {

        if (userName.isEmpty() || crushName.isEmpty()){
            throw new RuntimeException("Username or CrushName can't be empty");
        }

        String trimmedUserName = userName.trim();
        String trimmedCrushName = crushName.trim();

        if (trimmedUserName.charAt(0) == trimmedCrushName.charAt(0)) {
            return returnMessageForSameInitials();
        }
        if (trimmedUserName.length() == trimmedCrushName.length()) {
            return returnMessageForSameLength(userName);
        }


        int namesLength = trimmedUserName.length() + trimmedCrushName.length();

        int decider = namesLength % 9;

        if (decider == 0) {
            return variousResult(trimmedUserName, trimmedCrushName, "FRIENDSHIP");
        } else if (decider == 1) {
            return variousResult(trimmedUserName, trimmedCrushName, "MARRIAGE");
        } else if (decider == 2) {
            return variousResult(trimmedUserName, trimmedCrushName, "COMPLICATED");
        } else if (decider == 3) {
            return variousResult(trimmedUserName, trimmedCrushName, "INCONCLUSIVE");
        } else if (decider == 4) {
            return variousResult(trimmedUserName, trimmedCrushName, "FUCK BUDDY");
        } else if (decider == 5) {
            return variousResult(trimmedUserName, trimmedCrushName, "SOMETHING BUT NOTHING");
        } else if (decider == 6) {
            return variousResult(trimmedUserName, trimmedCrushName, "MUTUAL UNDERSTANDING");
        } else if (decider == 7) {
            return variousResult(trimmedUserName, trimmedCrushName, "BEST FRIENDS");
        } else if (decider == 8) {
            return variousResult(trimmedUserName, trimmedCrushName, "LOVE");
        } else {
            return "Bad Request";
        }

    }

    public String returnMessageForSameInitials() {
        return "Wow, you have the same initial as your crush...She probably is in love already, lol.";
    }

    public String returnMessageForSameLength(String userName) {
        int lengthOfNames = userName.trim().length();
        return "Wow, You and your Crush have the same number of alphabet in your names(" + lengthOfNames + ")...hmmm, looks like you're made for eachother";
    }

    public String variousResult(String userName, String crushName, String result) {
        return "The relationship between " + userName + " and " + crushName + " is " + result;
    }


}
