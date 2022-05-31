package com.example.crushandi.utils;

import com.example.crushandi.entity.Statistics;
import com.example.crushandi.repository.StatisticsRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


@NoArgsConstructor
public class Scheduler implements CommandLineRunner {

    @Autowired
    private  StatisticsRepository statisticsRepository;


    @Override
    public void run(String... args) throws Exception {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Statistics statistics = new Statistics();


                LocalDateTime date = LocalDateTime.now();
                String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss"));

                statistics.setDate(formattedDate);
                statisticsRepository.save(statistics);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000 * 60 * 60);

    }

//
//    @Scheduled(fixedRateString = "PT01H")
//    public void setNewStat() {
//        Statistics statistics = new Statistics();
//
//
//        LocalDateTime date = LocalDateTime.now();
//        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss"));
//
//
//        statistics.setDate(formattedDate);
//        statisticsRepository.save(statistics);
//    }




}
