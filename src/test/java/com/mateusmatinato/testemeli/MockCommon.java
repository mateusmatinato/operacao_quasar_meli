package com.mateusmatinato.testemeli;

import com.mateusmatinato.testemeli.domain.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockCommon {

    public static List<String[]> mockMessagesCorrect() {
        return Arrays.asList(
                new String[]{"this", "", ""},
                new String[]{"", "is", ""},
                new String[]{"", "", "correct"}
        );

    }

    public static Map<String, Double> mockDistancesCorrect() {
        Map<String, Double> map = new HashMap<>();
        map.put("kenobi", 100.00);
        map.put("sato", 100.00);
        map.put("skywalker", 100.00);
        return map;
    }

    public static List<String[]> mockMessagesIncorrectDuplicatedMessage() {
        return Arrays.asList(
                new String[]{"this", "", ""},
                new String[]{"", "is", "correct"},
                new String[]{"", "", "incorrect"}
        );
    }

    public static Map<String, Double> mockDistancesIncorrect() {
        Map<String, Double> map = new HashMap<>();
        map.put("this", 100.00);
        map.put("is", 100.00);
        map.put("incorrect", 100.00);
        return map;
    }

    public static List<String[]> mockMessagesIncorrectIncompleteMessage() {
        return Arrays.asList(
                new String[]{"this", "", ""},
                new String[]{"", "", ""},
                new String[]{"", "", "incomplete"}
        );
    }

    public static TopSecretRequest buildTopSecretCorrect() {
        return TopSecretRequest.builder()
                .satellites(mockSatellitesList())
                .build();
    }

    private static List<SatelliteInfo> mockSatellitesList() {
        return Arrays.asList(
                SatelliteInfo.builder().name("kenobi").distance(100).message(new String[]{"this", "", ""}).build(),
                SatelliteInfo.builder().name("sato").distance(200).message(new String[]{"", "is", ""}).build(),
                SatelliteInfo.builder().name("skywalker").distance(100).message(new String[]{"", "", "correct"}).build()
        );
    }

    public static TopSecretResponse buildTopSecretResponse() {
        return TopSecretResponse.builder()
                .position(PositionInfo.builder().y(100).x(200).build())
                .message("this is correct")
                .build();
    }

    public static TopSecretSplitRequest mockTopSecretSplitRequestCorrect() {
        return TopSecretSplitRequest.builder()
                .distance(100)
                .message(new String[]{"this", "is", "correct"})
                .build();
    }
}
