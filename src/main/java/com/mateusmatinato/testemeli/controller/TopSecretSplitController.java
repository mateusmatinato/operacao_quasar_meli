package com.mateusmatinato.testemeli.controller;

import com.mateusmatinato.testemeli.domain.*;
import com.mateusmatinato.testemeli.exception.GeneralException;
import com.mateusmatinato.testemeli.service.OperationQuasarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topsecret_split")
public class TopSecretSplitController {

    private final OperationQuasarService service;

    private final List<SatelliteInfo> satelliteInfos;

    public TopSecretSplitController(OperationQuasarService service) {
        this.service = service;
        this.satelliteInfos = new ArrayList<>();
    }

    @PostMapping("/{satellite_name}")
    public ResponseEntity<TopSecretSplitResponse> insertMessageSplitInfo(@PathVariable("satellite_name") String satelliteName,
                                                                         @RequestBody TopSecretSplitRequest request) {
        validateIfSatelliteInfoIsValid(satelliteName);
        this.satelliteInfos.add(SatelliteInfo.builder()
                .name(satelliteName)
                .message(request.getMessage())
                .distance(request.getDistance())
                .build());

        return ResponseEntity.ok(TopSecretSplitResponse.builder().satellites(satelliteInfos).build());
    }

    @GetMapping("/")
    public ResponseEntity<TopSecretResponse> calculateMessageAndLocation() {
        TopSecretResponse response = service.buildTopSecretResponse(buildMessages(), buildDistances());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/")
    public ResponseEntity<TopSecretSplitResponse> clearSatelliteInfo() {
        this.satelliteInfos.clear();
        return ResponseEntity.ok(TopSecretSplitResponse.builder().satellites(satelliteInfos).build());
    }

    private List<String[]> buildMessages() {
        return this.satelliteInfos.stream().map(SatelliteInfo::getMessage).collect(Collectors.toList());
    }

    private Map<String, Double> buildDistances() {
        Map<String, Double> mapDistances = new HashMap<>();
        this.satelliteInfos.forEach(satelliteInfo -> {
            mapDistances.put(satelliteInfo.getName(), satelliteInfo.getDistance());
        });
        return mapDistances;
    }

    private void validateIfSatelliteInfoIsValid(String satelliteName) {
        List<String> validSatellites = Arrays.asList("kenobi", "sato", "skywalker");
        if(!validSatellites.contains(satelliteName)){
            throw new GeneralException("Esse satélite não consta como um dos satélites disponíveis da operação Quasar.");
        }

        if(this.satelliteInfos.stream().anyMatch(satelliteInfo -> satelliteInfo.getName().equals(satelliteName))){
            throw new GeneralException("A informação desse satélite já foi recebida. Caso queira inserir novamente, execute a rota PATCH.");
        }
    }

}
