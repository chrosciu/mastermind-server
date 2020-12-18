package com.sages.mastermindserver.service;

import com.sages.mastermindserver.model.Session;
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
        return sessionRepository
                .save(Session.builder().code(guessService.code()).build())
                .map(Session::getId);
    }

    public Mono<Void> destroySessionById(String sessionId) {
        return sessionRepository.deleteById(sessionId);
    }

    public Mono<String> getResultForGivenSessionIdAndSample(String sessionId, String sample) {
        return sessionRepository.findById(sessionId)
                .map(s -> guessService.guess(s.getCode(), sample));

    }
}
