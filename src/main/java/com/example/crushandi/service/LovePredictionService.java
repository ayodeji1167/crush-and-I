package com.example.crushandi.service;

import com.example.crushandi.dto.ReturnedResult;

public interface LovePredictionService {

    ReturnedResult getResult(String userName, String userGender, String crushName, String crushGender);
}
