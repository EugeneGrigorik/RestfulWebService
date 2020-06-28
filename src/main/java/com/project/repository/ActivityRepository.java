package com.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {

}
