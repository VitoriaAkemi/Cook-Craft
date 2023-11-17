package br.com.fiap.cookcraft.exception;

public class UniqueViolationException extends RuntimeException {

    public UniqueViolationException(String msg){
        super(msg);
    }
}
