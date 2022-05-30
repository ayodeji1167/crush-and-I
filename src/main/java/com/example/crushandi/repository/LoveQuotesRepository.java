package com.example.crushandi.repository;

import com.example.crushandi.entity.LoveQuotes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoveQuotesRepository extends MongoRepository<LoveQuotes, String> {
}