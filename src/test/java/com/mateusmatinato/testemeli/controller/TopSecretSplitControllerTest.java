package com.mateusmatinato.testemeli.controller;

import com.mateusmatinato.testemeli.MockCommon;
import com.mateusmatinato.testemeli.domain.SatelliteInfo;
import com.mateusmatinato.testemeli.domain.TopSecretResponse;
import com.mateusmatinato.testemeli.domain.TopSecretSplitResponse;
import com.mateusmatinato.testemeli.exception.GeneralException;
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

import java.util.Objects;

@SpringBootTest
class TopSecretSplitControllerTest {

    @Mock
    private OperationQuasarService service;

    private TopSecretSplitController controller;

    @BeforeEach
    public void setup(){
        this.controller = new TopSecretSplitController(service);
    }

    @Test
    void shouldInsertMessageInfoWithSuccess(){
        ResponseEntity<TopSecretSplitResponse> response = controller.insertMessageSplitInfo("kenobi", MockCommon.mockTopSecretSplitRequestCorrect());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void shouldInsertMessageInfoWithErrorInvalidSatellite(){
        Assertions.assertThrows(GeneralException.class,
                () -> controller.insertMessageSplitInfo("INVALID", MockCommon.mockTopSecretSplitRequestCorrect()));
    }

    @Test
    void shouldInsertMessageInfoWithErrorAlreadyExistsSatellite(){
        controller.insertMessageSplitInfo("kenobi", MockCommon.mockTopSecretSplitRequestCorrect());

        Assertions.assertThrows(GeneralException.class,
                () -> controller.insertMessageSplitInfo("kenobi", MockCommon.mockTopSecretSplitRequestCorrect()));
    }

    @Test
    void shouldCalculateMessageAndLocationWithSuccess(){
        Mockito.when(service.buildTopSecretResponse(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(MockCommon.buildTopSecretResponse());

        controller.insertMessageSplitInfo("kenobi", MockCommon.mockTopSecretSplitRequestCorrect());
        controller.insertMessageSplitInfo("sato", MockCommon.mockTopSecretSplitRequestCorrect());
        controller.insertMessageSplitInfo("skywalker", MockCommon.mockTopSecretSplitRequestCorrect());

        ResponseEntity<TopSecretResponse> response = controller.calculateMessageAndLocation();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void shouldClearSatelliteInfoWithSuccess(){
        ResponseEntity<TopSecretSplitResponse> response = controller.clearSatelliteInfo();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(0, Objects.requireNonNull(response.getBody()).getSatellites().size());
    }

}
