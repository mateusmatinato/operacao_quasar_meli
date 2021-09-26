package com.mateusmatinato.testemeli.service;

import com.mateusmatinato.testemeli.domain.PositionInfo;
import com.mateusmatinato.testemeli.domain.TopSecretResponse;
import com.mateusmatinato.testemeli.exception.LocationErrorException;
import com.mateusmatinato.testemeli.exception.MessageErrorException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OperationQuasarService {

    public TopSecretResponse buildTopSecretResponse(List<String[]> messages, Map<String, Double> distances){
        PositionInfo location = this.getLocation(distances);
        String message = this.getMessage(messages);
        return TopSecretResponse.builder().message(message).position(location).build();
    }

    public String getMessage(List<String[]> messagesReceived) {
        try {
            Optional<String[]> messageReceived = messagesReceived.stream().findFirst();
            if (messageReceived.isPresent()) {
                int messageSize = messageReceived.get().length;
                String[] messageSent = new String[messageSize];
                messagesReceived.forEach(message -> {
                    for (int i = 0; i < message.length; i++) {
                        if (!message[i].equals("")) {
                            //Check if its already filled or its different
                            if(messageSent[i] != null){
                                if(!messageSent[i].equals(message[i])){
                                    throw new MessageErrorException("Ocorreu um erro ao decifrar mensagem do emissor. Dois satélites receberam mensagens diferentes na mesma posição.");
                                }
                            }
                            else {
                                messageSent[i] = message[i];
                            }
                        }
                    }
                });

                validateIfMessageIsComplete(messageSent);
                return String.join(" ", messageSent);
            } else {
                throw new MessageErrorException();
            }
        }
        catch(Exception e){
            throw new MessageErrorException(e.getMessage());
        }

    }

    private void validateIfMessageIsComplete(String[] messageSent) {
        if(Arrays.asList(messageSent).contains(null)){
            throw new LocationErrorException();
        }
    }

    public PositionInfo getLocation(Map<String, Double> distances) {
        try {
            double dKenobi = distances.get("kenobi");
            double dSkywalker = distances.get("skywalker");
            double dSato = distances.get("sato");

            // x = (2 * dKenobi² - 3 * dSkywalker² + dSato² - 780000)/1600
            double x = ((2 * (Math.pow(dKenobi, 2))) - (3 * (Math.pow(dSkywalker, 2))) + (Math.pow(dSato, 2)) - 780000) / 1600;

            // y = -6x + dKenobi²/200 - dSkywalker²/200 -1350
            double y = -6 * x + Math.pow(dKenobi, 2) / 200 - Math.pow(dSkywalker, 2) / 200 - 1350;

            double roundX = Math.round(x * 100.0) / 100.0;
            double roundY = Math.round(y * 100.0) / 100.0;

            return PositionInfo.builder()
                    .x(roundX)
                    .y(roundY)
                    .build();
        }
        catch(Exception e){
            throw new LocationErrorException();
        }
    }
}
