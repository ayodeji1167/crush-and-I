package com.example.crushandi.repository;

import com.example.crushandi.entity.PickUpLines;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PickUpLinesRepository extends MongoRepository<PickUpLines, String> {
}