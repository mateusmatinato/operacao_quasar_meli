package com.mateusmatinato.testemeli.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopSecretSplitRequest {

    @JsonProperty("distance")
    private double distance;

    @JsonProperty("message")
    private String[] message;

}
