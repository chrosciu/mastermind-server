package com.sages.mastermindserver.controller;

import com.sages.mastermindserver.exception.ImproperSampleFormatException;
import com.sages.mastermindserver.exception.SessionNotFoundException;
import com.sages.mastermindserver.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;

    //POST /session -> create session, return id
    //DELETE /session/{id} -> delete session with id
    //GET /session/{id}/guess/{sample} -> get result for given sample in session

    @PostMapping
    public Mono<String> create() {
        return sessionService.createSessionAndReturnId();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return sessionService.destroySessionById(id);
    }

    @GetMapping("/{id}/guess/{sample}")
    public Mono<String> guess(@PathVariable("id") String id, @PathVariable("sample") String sample) {
        return sessionService.getResultForGivenSessionIdAndSample(id, sample);
    }

    @ExceptionHandler(SessionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<String> handleSessionNotFoundException(SessionNotFoundException e) {
        return Mono.just("Session not found");
    }

    @ExceptionHandler(ImproperSampleFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<String> handleImproperSampleFormatException(ImproperSampleFormatException e) {
        return Mono.just("Improper format of sample");
    }
}
