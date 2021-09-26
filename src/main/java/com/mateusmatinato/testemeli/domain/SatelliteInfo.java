package com.mateusmatinato.testemeli.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SatelliteInfo {

    @JsonProperty("name")
    private String name;

    @JsonProperty("distance")
    private double distance;

    @JsonProperty("message")
    private String[] message;

}
