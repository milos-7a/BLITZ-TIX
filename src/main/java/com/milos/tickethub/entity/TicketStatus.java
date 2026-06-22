package com.milos.tickethub.entity;

public enum TicketStatus {
    PURCHASED,
    CANCELLED;

    public String getValue(){
        return name();
    }
}
