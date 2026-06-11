package com.milos.tickethub.controller;

import com.milos.tickethub.dto.EventRequest;
import com.milos.tickethub.dto.EventResponse;
import com.milos.tickethub.entity.Event;
import com.milos.tickethub.mapper.EventMapper;
import com.milos.tickethub.service.EventService;
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
    public EventResponse createEvent(@RequestBody EventRequest request) {
        Event event = EventMapper.toEntity(request);
        Event saved = eventService.createEvent(event);
        return EventMapper.toResponse(saved);
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id){
        return eventService.getEventById(id);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event){
        return eventService.updateEvent(id, event);
    }
}
