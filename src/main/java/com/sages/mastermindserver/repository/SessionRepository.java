package com.sages.mastermindserver.repository;

import com.sages.mastermindserver.model.Session;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends ReactiveMongoRepository<Session, String> {
}
