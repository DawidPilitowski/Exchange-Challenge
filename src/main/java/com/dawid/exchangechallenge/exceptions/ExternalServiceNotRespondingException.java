package com.dawid.exchangechallenge.exceptions;

public class ExternalServiceNotRespondingException extends Exception {

    public ExternalServiceNotRespondingException(String serviceName) {
        super(new StringBuilder(serviceName).append("is presently unavailable").toString());
    }

    public String toString() {
        return "External Service error. Please try again.";
    }

}
