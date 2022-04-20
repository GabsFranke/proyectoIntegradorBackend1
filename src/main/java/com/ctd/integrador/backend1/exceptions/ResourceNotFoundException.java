package com.ctd.integrador.backend1.exceptions;

import java.util.Optional;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
