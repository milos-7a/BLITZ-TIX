package com.milos.tickethub.service;

import com.milos.tickethub.dto.EventResponse;
import com.milos.tickethub.entity.Event;
import com.milos.tickethub.exception.EventNotFoundException;
import com.milos.tickethub.mapper.EventMapper;
import com.milos.tickethub.repository.EventRepository;
import com.milos.tickethub.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

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
    public Page<EventResponse> findWithFilters(String title, String location, LocalDateTime date, Pageable pageable){
        String titleParam = (title != null && !title.isBlank()) ? "%" + title + "%" : null;
        Page<Event> eventPage = eventRepository.findWithFilters(titleParam, location, date, pageable);
        return eventPage.map(EventMapper::toResponse);
    }

    @Override
    public String deleteEvent(Long id){
        eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        if(ticketRepository.existsByEventId(id)){
            throw new IllegalStateException(
                    "Cannot delete event with existing tickets"
            );
        }
        eventRepository.deleteById(id);
        return "Event deleted successfully";
    }
}
