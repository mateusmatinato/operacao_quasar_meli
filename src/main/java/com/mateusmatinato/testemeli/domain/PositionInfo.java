package com.mateusmatinato.testemeli.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionInfo {

    private double x;
    private double y;

}
