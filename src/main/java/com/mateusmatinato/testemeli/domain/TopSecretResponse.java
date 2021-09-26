package com.mateusmatinato.testemeli.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopSecretResponse {

    private PositionInfo position;
    private String message;

}
