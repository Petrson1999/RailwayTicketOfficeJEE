package com.railvayticketiffice.exeptions;

public class PersistException extends Exception {

    public PersistException() {
    }

    public PersistException(String message) {
        super(message);
    }

    public PersistException(Throwable cause) {
        super(cause);
    }

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }

}
