package com.mateusmatinato.testemeli.exception;

public class LocationErrorException extends RuntimeException{

    public LocationErrorException(){
        super("Ocorreu um erro ao obter a localização do emissor");
    }

    public LocationErrorException(String message){
        super(message);
    }

}
