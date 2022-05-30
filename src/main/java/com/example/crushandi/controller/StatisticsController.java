package com.example.crushandi.controller;

import com.example.crushandi.entity.Statistics;
import com.example.crushandi.repository.StatisticsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stat")
public class StatisticsController {

    private final StatisticsRepository statisticsRepository;

    public StatisticsController(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }


    @GetMapping
    public Page<Statistics> getAllStat() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        return statisticsRepository.findAll(pageable);
    }

    @GetMapping("/count")
    public void addToCount() {
        Statistics statistics = statisticsRepository.findAll(Sort.by("id").descending()).get(0);
        statistics.setViews(statistics.getViews() + 1);
        statisticsRepository.save(statistics);

    }
}
