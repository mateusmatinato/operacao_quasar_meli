package com.mateusmatinato.testemeli.controller;

import com.mateusmatinato.testemeli.MockCommon;
import com.mateusmatinato.testemeli.domain.TopSecretResponse;
import com.mateusmatinato.testemeli.service.OperationQuasarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class TopSecretControllerTest {

    @Mock
    private OperationQuasarService service;

    private TopSecretController controller;

    @BeforeEach
    public void setup(){
        this.controller = new TopSecretController(service);
    }

    @Test
    void shouldProcessTopSecretWithSuccess(){
        Mockito.when(service.buildTopSecretResponse(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(MockCommon.buildTopSecretResponse());
        ResponseEntity<TopSecretResponse> response = controller.processTopSecret(MockCommon.buildTopSecretCorrect());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

}
