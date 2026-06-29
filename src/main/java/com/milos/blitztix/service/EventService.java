package com.milos.blitztix.service;
import com.milos.blitztix.dto.EventResponse;
import com.milos.blitztix.dto.EventUpdateRequest;
import com.milos.blitztix.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    Event createEvent(Event event);
    Event getEventById(Long id);
    EventResponse updateEvent(Long id, EventUpdateRequest request);
    String deleteEvent(Long id);
    Page<EventResponse> findWithFilters(String title, String location, LocalDateTime date, Pageable pageable);
}
