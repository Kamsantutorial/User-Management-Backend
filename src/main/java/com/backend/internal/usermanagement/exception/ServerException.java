package com.backend.internal.usermanagement.exception;

public class ServerException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4563032572224892116L;
    private final String message;
    private final String code;

    public ServerException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
