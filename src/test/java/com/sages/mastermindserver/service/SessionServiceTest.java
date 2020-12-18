package com.sages.mastermindserver.service;

import com.sages.mastermindserver.model.Session;
import com.sages.mastermindserver.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
    @Mock
    private GuessService guessService;
    @Mock
    private SessionRepository sessionRepository;
    @InjectMocks
    private SessionService sessionService;

    @Test
    public void shouldCreateSessionWithCodeFromGuessServiceAndReturnId() {
        //given
        String someSessionId = "1-2-3";
        String someCode = "1234";
        Session notSavedSession = Session.builder().code(someCode).build();
        Session savedSession = Session.builder().code(someCode).id(someSessionId).build();
        Mockito.when(guessService.code()).thenReturn(someCode);
        Mockito.when(sessionRepository.save(notSavedSession)).thenReturn(Mono.just(savedSession));

        //when
        Mono<String> sessionId = sessionService.createSessionAndReturnId();

        //then
        StepVerifier.create(sessionId)
                .expectNext(someSessionId)
                .verifyComplete();
    }
}
