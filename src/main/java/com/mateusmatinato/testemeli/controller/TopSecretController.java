package com.mateusmatinato.testemeli.controller;

import com.mateusmatinato.testemeli.domain.PositionInfo;
import com.mateusmatinato.testemeli.domain.SatelliteInfo;
import com.mateusmatinato.testemeli.domain.TopSecretRequest;
import com.mateusmatinato.testemeli.domain.TopSecretResponse;
import com.mateusmatinato.testemeli.service.OperationQuasarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topsecret")
public class TopSecretController {

    private final OperationQuasarService service;

    public TopSecretController(OperationQuasarService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<TopSecretResponse> processTopSecret(@RequestBody TopSecretRequest request) {
        TopSecretResponse response = service.buildTopSecretResponse(buildMessages(request), buildDistances(request));
        return ResponseEntity.ok(response);
    }

    private List<String[]> buildMessages(TopSecretRequest request) {
        return request.getSatellites().stream().map(SatelliteInfo::getMessage).collect(Collectors.toList());
    }

    private Map<String, Double> buildDistances(TopSecretRequest request) {
        Map<String, Double> mapDistances = new HashMap<>();
        request.getSatellites().forEach(satelliteInfo -> {
            mapDistances.put(satelliteInfo.getName(), satelliteInfo.getDistance());
        });
        return mapDistances;
    }

}
