package com.example.crushandi.repository;

import com.example.crushandi.entity.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUserRepository extends MongoRepository<AppUser, String> {

}