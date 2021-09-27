package com.mateusmatinato.testemeli.service;

import com.mateusmatinato.testemeli.MockCommon;
import com.mateusmatinato.testemeli.domain.TopSecretResponse;
import com.mateusmatinato.testemeli.exception.LocationErrorException;
import com.mateusmatinato.testemeli.exception.MessageErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OperationQuasarServiceTest {

    @InjectMocks
    private OperationQuasarService service;

    @Test
    void shouldBuildTopSecretResponseWithSuccess() {

        TopSecretResponse resp = service.buildTopSecretResponse(
                MockCommon.mockMessagesCorrect(),
                MockCommon.mockDistancesCorrect()
        );
        Assertions.assertNotNull(resp.getMessage());
        Assertions.assertNotNull(resp.getPosition());
    }

    @Test
    void shouldThrowMessageErrorExceptionDuplicatedMessage() {
        Assertions.assertThrows(MessageErrorException.class, () -> service.buildTopSecretResponse(
                MockCommon.mockMessagesIncorrectDuplicatedMessage(),
                MockCommon.mockDistancesCorrect()
        ));
    }

    @Test
    void shouldThrowMessageErrorExceptionIncompleteMessage() {
        Assertions.assertThrows(MessageErrorException.class, () -> service.buildTopSecretResponse(
                MockCommon.mockMessagesIncorrectIncompleteMessage(),
                MockCommon.mockDistancesCorrect()
        ));
    }

    @Test
    void shouldThrowLocationErrorException() {
        Assertions.assertThrows(LocationErrorException.class, () -> service.buildTopSecretResponse(
                MockCommon.mockMessagesCorrect(),
                MockCommon.mockDistancesIncorrect()
        ));
    }

}
