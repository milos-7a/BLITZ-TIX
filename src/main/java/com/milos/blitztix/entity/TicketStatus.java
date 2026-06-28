package com.milos.blitztix.entity;

public enum TicketStatus {
    PURCHASED,
    CANCELLED;

    public String getValue(){
        return name();
    }
}
