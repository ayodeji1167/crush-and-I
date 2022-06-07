package com.example.crushandi.repository;

import com.example.crushandi.entity.Reply;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReplyRepository extends MongoRepository<Reply, String> {
}
