package com.milos.tickethub.mapper;

import com.milos.tickethub.dto.EventRequest;
import com.milos.tickethub.dto.EventResponse;
import com.milos.tickethub.entity.Event;

public class EventMapper {
    public static EventResponse toResponse(Event event){
        return new EventResponse(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getDateTime(),
                event.getPrice(),
                event.getCapacity() - event.getSoldTickets()
        );
    }
    public static Event toEntity(EventRequest request){
        return Event.builder()
                .title(request.title())
                .description(request.description())
                .location(request.location())
                .dateTime(request.dateTime())
                .price(request.price())
                .capacity(request.capacity())
                .soldTickets(0)
                .build();
    }
}
