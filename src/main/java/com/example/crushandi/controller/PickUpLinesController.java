package com.example.crushandi.controller;

import com.example.crushandi.entity.PickUpLines;
import com.example.crushandi.service.PickUpLineService;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/pickup")
public class PickUpLinesController {
    private final PickUpLineService pickUpLineService;

    public PickUpLinesController(PickUpLineService pickUpLineService) {
        this.pickUpLineService = pickUpLineService;
    }


    // POPULATE PICKUP LINE DB
    @GetMapping("/saveall")
    public void saveAllPickUpFromApi() throws URISyntaxException {
        pickUpLineService.addPickUpFromApi();
    }


    @GetMapping("/get/all")
    public List<PickUpLines> getAllPickLines() {
        return pickUpLineService.getAllPickUpLines();
    }

    @GetMapping("/get/{id}")
    private PickUpLines getById(@PathVariable Long id) {
        return pickUpLineService.getPickUpById(id);
    }


    @DeleteMapping("/delete/{id}")
    private void deleteById(@PathVariable Long id) {
        pickUpLineService.deletePickUpLine(id);
    }
}
