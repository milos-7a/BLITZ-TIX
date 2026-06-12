package com.milos.tickethub.service;

import com.milos.tickethub.entity.Event;
import com.milos.tickethub.exception.EventNotFoundException;
import com.milos.tickethub.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    @Override
    public Event createEvent(Event event){
        return eventRepository.save(event);
    }

    @Override
    public Event getEventById(Long id){
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @Override
    public Event updateEvent(Long id, Event event){
        event.setId(id);
        return eventRepository.save(event);
    }

    @Override
    public String deleteEvent(Long id){
        eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        eventRepository.deleteById(id);
        return "Event deleted successfully";
    }
}
