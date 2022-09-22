package com.arqui.vetstore.configure.error;

public class VeterinaryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public VeterinaryNotFoundException(String message){
        super(message);
    }

}
