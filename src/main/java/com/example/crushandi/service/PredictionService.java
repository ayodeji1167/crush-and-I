package com.example.crushandi.service;

import com.example.crushandi.dto.ReturnedResult;
import org.springframework.stereotype.Service;

@Service
public class PredictionService {

    public ReturnedResult result(String userName, String crushName) {

        if (userName.isEmpty() || crushName.isEmpty()) {
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
            return new ReturnedResult("FRIENDSHIP", 60);
        } else if (decider == 1) {
            return new ReturnedResult("MARRIAGE", 90);
        } else if (decider == 2) {
            return new ReturnedResult("COMPLICATED", 57);
        } else if (decider == 3) {
            return new ReturnedResult("INCONCLUSIVE", 0);
        } else if (decider == 4) {
            return new ReturnedResult("FUCK BUDDY", 43);
        } else if (decider == 5) {
            return new ReturnedResult("SOMETHING BUT NOTHING", 50);
        } else if (decider == 6) {
            return new ReturnedResult("MUTUAL UNDERSTANDING", 79);
        } else if (decider == 7) {
            return new ReturnedResult("BEST FRIEND", 85);
        } else if (decider == 8) {
            return new ReturnedResult("LOVE", 100);
        } else {
            return new ReturnedResult("Bad Request", 0);
        }

    }

    public ReturnedResult returnMessageForSameInitials() {
        return new ReturnedResult("Wow, you have the same initial as your crush...She probably is in love already, lol.", 0);
    }

    public ReturnedResult returnMessageForSameLength(String userName) {
        int lengthOfNames = userName.trim().length();
        return new ReturnedResult("Wow, You and your Crush have the same number of alphabet in your names(" + lengthOfNames + ")...hmmm," +
                " looks like you're made for each other", 0);
    }


}
