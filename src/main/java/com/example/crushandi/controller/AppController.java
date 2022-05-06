package com.example.crushandi.controller;

import com.example.crushandi.dto.PredictionData;
import com.example.crushandi.service.PredictionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predict")
public class AppController {

    private final PredictionService predictionService;

    public AppController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }


    @Operation(summary = "Get Predicted Result")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Predicted!!"),
                    @ApiResponse(responseCode = "400", description = "Invalid Request")}
    )

    @PostMapping("/result")
    public ResponseEntity<String> getResult(@RequestBody PredictionData predictionData) {
        String userName = predictionData.getUserName();
        String crushName = predictionData.getCrushName();

        try {
            String result = predictionService.result(userName, crushName);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
