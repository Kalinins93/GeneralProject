package ru.neoflex.webrestfulltokafka.Exceptions;

public class ExternalSystemException extends RuntimeException{
    public ExternalSystemException(Throwable ex) {
        super(ex);
    }

    public ExternalSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ExternalSystemException(String message) {
        super(message);
    }

    public ExternalSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExternalSystemException() {
    }
}
