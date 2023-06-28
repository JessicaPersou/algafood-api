package com.algaworks.algafood.domain.exception;

public class EntityNotFound extends RuntimeException{

    private static final long SerialVersionUID = 1L;

    public EntityNotFound(String message){
        super(message);
    }
}
