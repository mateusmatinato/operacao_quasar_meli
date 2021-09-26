package com.mateusmatinato.testemeli.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
public class TopSecretSplitResponse {

    @JsonProperty("satellites")
    private List<SatelliteInfo> satellites;

}
