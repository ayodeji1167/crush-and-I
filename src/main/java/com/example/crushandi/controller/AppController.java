package com.example.crushandi.controller;

import com.example.crushandi.dto.PredictionData;
import com.example.crushandi.dto.ReturnedResult;
import com.example.crushandi.service.LoveQuotesService;
import com.example.crushandi.service.PredictionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/predict")
public class AppController {

    private final PredictionService predictionService;
    private final LoveQuotesService loveQuotesService;

    public AppController(PredictionService predictionService, LoveQuotesService loveQuotesService) {
        this.predictionService = predictionService;
        this.loveQuotesService = loveQuotesService;
    }


    @Operation(summary = "Get Predicted Result")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Predicted!!"),
                    @ApiResponse(responseCode = "400", description = "Invalid Request")}
    )

    @PostMapping("/result")
    public ResponseEntity<?> getResult(@RequestBody PredictionData predictionData) {
        String userName = predictionData.getUserName();
        String crushName = predictionData.getCrushName();

        try {
            ReturnedResult result = predictionService.result(userName, crushName);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome To The Relationship Prediction Application";
    }

    @GetMapping("/love-quotes")
    public ResponseEntity<?> getLoveQuotes() {
        return new ResponseEntity<>(loveQuotesService.returnLoveQuotes(), HttpStatus.OK);
    }

}
