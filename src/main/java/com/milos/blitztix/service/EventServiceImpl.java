package com.milos.blitztix.service;

import com.milos.blitztix.dto.EventResponse;
import com.milos.blitztix.dto.EventUpdateRequest;
import com.milos.blitztix.entity.Event;
import com.milos.blitztix.exception.EventNotFoundException;
import com.milos.blitztix.mapper.EventMapper;
import com.milos.blitztix.repository.EventRepository;
import com.milos.blitztix.repository.TicketRepository;
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
    public EventResponse updateEvent(Long id, EventUpdateRequest request){
        Event event = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        checkEventUpdates(event, request);
        eventRepository.save(event);
        return EventMapper.toResponse(event);
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

    private void checkEventUpdates(Event event, EventUpdateRequest request){
        if (request.description() != null && !request.description().isBlank()){
            event.setDescription(request.description());
        }
        if (request.dateTime() != null){
            event.setDateTime(request.dateTime());
        }
        if (request.location() != null && !request.location().isBlank()){
            event.setLocation(request.location());
        }
        if (request.capacity() != null){
            event.setCapacity(event.getCapacity() + request.capacity());
        }
        if (request.price()!= null){
            event.setPrice(request.price());
        }
        if (request.title() != null && !request.title().isBlank()){
            event.setTitle(request.title());
        }
        if (request.imageUrl() != null && !request.imageUrl().isBlank()){
            event.setImageUrl(request.imageUrl());
        }
    }
}
