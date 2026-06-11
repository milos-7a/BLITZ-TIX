package com.milos.tickethub.service;
import com.milos.tickethub.entity.Event;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    Event createEvent(Event event);
    Event getEventById(Long id);
    Event updateEvent(Long id, Event event);
    String deleteEvent(Long id);
}
