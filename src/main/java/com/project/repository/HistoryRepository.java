package com.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.model.History;

@Repository
public interface HistoryRepository extends MongoRepository<History, String> {

}
