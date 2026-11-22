package kz.iitu.registration.service;

import kz.iitu.registration.model.Event;
import kz.iitu.registration.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents(String title) {
        if (title == null || title.isBlank()) {
            return eventRepository.findAll();
        }
        return eventRepository.findByTitleContainingIgnoreCase(title.trim());
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(String title, String location) {
        Event event = new Event(title.trim(), location.trim());
        return eventRepository.save(event);
    }

    public Optional<Event> updateEvent(Long id, String title, String location) {
        return eventRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(title.trim());
                    existing.setLocation(location.trim());
                    return eventRepository.save(existing);
                });
    }

    public boolean deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            return false;
        }
        eventRepository.deleteById(id);
        return true;
    }
}