package com.ctd.integrador.backend1.exceptions;

import java.util.NoSuchElementException;


public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }

    public NoSuchElementException getNoSuchElementException(String mensaje){
        return new NoSuchElementException(mensaje);
    }
}
