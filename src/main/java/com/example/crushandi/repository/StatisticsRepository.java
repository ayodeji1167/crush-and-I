package com.example.crushandi.repository;

import com.example.crushandi.entity.Statistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatisticsRepository extends MongoRepository<Statistics, String> {
}