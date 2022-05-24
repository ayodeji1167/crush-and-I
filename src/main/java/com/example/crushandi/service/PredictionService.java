package com.example.crushandi.service;

import com.example.crushandi.dto.ReturnedResult;


public interface PredictionService {

    ReturnedResult result(String userName, String crushName);

}
