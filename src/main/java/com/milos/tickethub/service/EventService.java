package com.milos.tickethub.service;
import com.milos.tickethub.dto.EventResponse;
import com.milos.tickethub.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    Event createEvent(Event event);
    Event getEventById(Long id);
    Event updateEvent(Long id, Event event);
    String deleteEvent(Long id);
    Page<EventResponse> findWithFilters(String title, String location, LocalDateTime date, Pageable pageable);
}
