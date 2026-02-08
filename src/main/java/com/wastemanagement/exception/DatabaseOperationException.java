package com.wastemanagement.exception;

public class DatabaseOperationException extends WasteAppException {
    public DatabaseOperationException(String message) {
        super(message);
    }
}