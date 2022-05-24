package com.example.crushandi.controller;

import com.example.crushandi.entity.LoveQuotes;
import com.example.crushandi.service.LoveQuotesService;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/lovequotes")
public class LoveQuotesController {

    private final LoveQuotesService loveQuotesService;

    public LoveQuotesController(LoveQuotesService loveQuotesService) {
        this.loveQuotesService = loveQuotesService;
    }


    //POPULATE LOVE QUOTES DB;
    @GetMapping("/saveall")
    public void saveAllQuotesFromApi() throws URISyntaxException {
        loveQuotesService.addLoveQuotesFromApi();
    }


    @GetMapping("/get/all")
    public List<LoveQuotes> getAllQuotes() {
        return loveQuotesService.getAllLoveQuotes();
    }

    @GetMapping("/get/{id}")
    private LoveQuotes getById(@PathVariable Long id) {
        return loveQuotesService.getQuoteById(id);
    }


    @DeleteMapping("/delete/{id}")
    private void deleteById(@PathVariable Long id) {
        loveQuotesService.deleteLoveQuotesById(id);
    }

}
