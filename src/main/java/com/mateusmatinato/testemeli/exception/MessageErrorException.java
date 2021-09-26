package com.mateusmatinato.testemeli.exception;

public class MessageErrorException extends RuntimeException{

    public MessageErrorException(){
        super("Ocorreu um erro ao obter mensagem do emissor");
    }

    public MessageErrorException(String message){
        super(message);
    }
}
