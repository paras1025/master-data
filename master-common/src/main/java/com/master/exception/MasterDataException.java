package com.master.exception;

public class MasterDataException extends RuntimeException {

    public MasterDataException(String message) {
        super(message);
    }

    public MasterDataException(String message, Throwable cause) {
        super(message, cause);
    }
}