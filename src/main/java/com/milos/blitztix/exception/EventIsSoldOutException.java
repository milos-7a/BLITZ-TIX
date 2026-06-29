package com.milos.blitztix.exception;

public class EventIsSoldOutException extends RuntimeException {
    public EventIsSoldOutException(Long id) {
        super("Event " + id + " is sold out");
    }
}
