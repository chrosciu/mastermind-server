package com.sages.mastermindserver.service;

import com.sages.mastermindserver.repository.SessionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final GuessService guessService;

    public Mono<String> createSessionAndReturnId() {
        //get code from guess service
        //build Session object
        //save Session object
        //return id of saved object
        return null;
    }

    public Mono<Void> destroySessionById(String sessionId) {
        return null;
    }

    public Mono<String> getResultForGivenSessionIdAndSample(String sessionId, String sample) {
        return null;
    }
}
