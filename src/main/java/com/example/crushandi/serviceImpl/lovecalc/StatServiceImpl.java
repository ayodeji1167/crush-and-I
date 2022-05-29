package com.example.crushandi.serviceImpl.lovecalc;

import com.example.crushandi.entity.Statistics;
import com.example.crushandi.repository.StatisticsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StatServiceImpl {
    private final StatisticsRepository statisticsRepository;

    public StatServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Scheduled(fixedDelay = 1000*60 )
    public void createStatObj()throws InterruptedException{
        Statistics statistics = new Statistics();
        statisticsRepository.save(statistics);
    }

    @Scheduled(fixedDelay = 1000*60 )
    public void setNewStat()throws InterruptedException{
        List<Statistics> statistics = statisticsRepository.findAll(Sort.by("id").descending());
        Statistics theRealStat = statistics.get(0);

        LocalDateTime date = LocalDateTime.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss"));
        System.out.println(formattedDate);

        theRealStat.setDate(formattedDate);
        statisticsRepository.save(theRealStat);
    }
}
