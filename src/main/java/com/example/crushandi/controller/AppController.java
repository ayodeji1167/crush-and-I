package com.example.crushandi.controller;


import com.example.crushandi.dto.ReturnedResult;
import com.example.crushandi.dto.request.PredictionData;
import com.example.crushandi.entity.LoveQuotes;
import com.example.crushandi.service.LoveQuotesService;
import com.example.crushandi.service.PickUpLineService;
import com.example.crushandi.service.PredictionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/predict")
public class AppController {

    private final PredictionService predictionService;
    private final LoveQuotesService loveQuotesService;
    private final PickUpLineService pickUpLineService;

    public AppController(PredictionService predictionService, LoveQuotesService loveQuotesService, PickUpLineService pickUpLineService) {
        this.predictionService = predictionService;
        this.loveQuotesService = loveQuotesService;
        this.pickUpLineService = pickUpLineService;
    }


    @Operation(summary = "Get Predicted Result")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Result Predicted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReturnedResult.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)})

    @PostMapping("/result")
    public ResponseEntity<?> getResult(@RequestBody @Valid PredictionData predictionData) {
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
    public Map<String, String> welcomeMessage() {
        Map<String , String> endpoints = new HashMap<>();
        endpoints.put("/predict/result" , "to get the relationship prediction");
        endpoints.put("/predict/love-quotes" , "to get the Love Quotes");
        endpoints.put("/predict/pickup-lines" , "to get the Pick up Lines");

        return endpoints;
    }


    @Operation(summary = "Get Love Quotes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List Of Love Quotes",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoveQuotes.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)})

    @GetMapping("/love-quotes")
    public ResponseEntity<?> getLoveQuotes() {
        return new ResponseEntity<>(loveQuotesService.getAllLoveQuotes(), HttpStatus.OK);
    }


}
