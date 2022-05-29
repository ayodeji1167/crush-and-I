package com.example.crushandi.controller;

import com.example.crushandi.entity.Statistics;
import com.example.crushandi.repository.StatisticsRepository;
import com.example.crushandi.serviceImpl.lovecalc.StatServiceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stat")
public class StatisticsController {

    private final StatServiceImpl service;
    private final StatisticsRepository statisticsRepository;

    public StatisticsController(StatServiceImpl service, StatisticsRepository statisticsRepository) {
        this.service = service;
        this.statisticsRepository = statisticsRepository;
    }


    @GetMapping
    public ResponseEntity<?> getAllStat() {
            return ResponseEntity.ok(statisticsRepository.findAll());
    }

    @GetMapping("/count")
    public void addToCount() {
        List<Statistics> statistics = statisticsRepository.findAll(Sort.by("id").descending());
        Statistics theRealStat = statistics.get(0);
        theRealStat.setViews(theRealStat.getViews() + 1);
        statisticsRepository.save(theRealStat);
    }
}
