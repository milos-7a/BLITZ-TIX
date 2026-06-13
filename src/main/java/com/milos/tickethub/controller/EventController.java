package com.milos.tickethub.controller;

import com.milos.tickethub.dto.EventRequest;
import com.milos.tickethub.dto.EventResponse;
import com.milos.tickethub.entity.Event;
import com.milos.tickethub.mapper.EventMapper;
import com.milos.tickethub.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public List<EventResponse> getAllEvents(){
        return eventService.getAllEvents()
                .stream()
                .map(EventMapper::toResponse)
                .toList();
    }

    @PostMapping
    public EventResponse createEvent(@Valid @RequestBody EventRequest request) {
        Event event = EventMapper.toEntity(request);
        Event saved = eventService.createEvent(event);
        return EventMapper.toResponse(saved);
    }

    @GetMapping("/{id}")
    public EventResponse getEventById(@PathVariable Long id){
        Event event = eventService.getEventById(id);
        return EventMapper.toResponse(event);
    }

    @PutMapping("/{id}")
    public EventResponse updateEvent(@PathVariable Long id, @Valid @RequestBody EventRequest request){
        Event event = EventMapper.toEntity(request);
        Event saved = eventService.updateEvent(id, event);
        return EventMapper.toResponse(saved);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id){
        return eventService.deleteEvent(id);
    }
}
