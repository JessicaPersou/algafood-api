package com.algaworks.algafood.domain.exception;

public class EntityInUse extends RuntimeException{

    private static final long SerialVersionUID = 1L;

    public EntityInUse(String message){
        super(message);
    }
}
