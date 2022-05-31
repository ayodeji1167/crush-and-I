package com.example.crushandi.utils;

import com.example.crushandi.entity.Statistics;
import com.example.crushandi.repository.StatisticsRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableScheduling
public class Scheduler {
    private final StatisticsRepository statisticsRepository;


    public Scheduler(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }


    @Scheduled(fixedRateString = "PT01H")
    public void setNewStat() {
        Statistics statistics = new Statistics();


        LocalDateTime date = LocalDateTime.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss"));


        statistics.setDate(formattedDate);
        statisticsRepository.save(statistics);
    }

}
