package kz.iitu.registration.service;

import kz.iitu.registration.model.EventInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final List<EventInfo> events = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(3);

    public EventService() {
        events.add(new EventInfo(1, "Web Design Workshop", "Online Zoom"));
        events.add(new EventInfo(2, "JavaScript Quiz", "Auditorium 301"));
        events.add(new EventInfo(3, "IT Career Talk", "Main Hall"));
    }

    public List<EventInfo> getAllEvents(String title) {
        if (title == null || title.isBlank()) {
            return events;
        }
        String query = title.toLowerCase();
        return events.stream()
                .filter(e -> e.getTitle() != null && e.getTitle().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    public Optional<EventInfo> getEventById(int id) {
        return events.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public EventInfo createEvent(String title, String location) {
        int newId = idCounter.incrementAndGet();
        EventInfo created = new EventInfo(newId, title.trim(), location.trim());
        events.add(created);
        return created;
    }

    public Optional<EventInfo> updateEvent(int id, String title, String location) {
        Optional<EventInfo> existingOpt = getEventById(id);
        if (existingOpt.isPresent()) {
            EventInfo existing = existingOpt.get();
            existing.setTitle(title.trim());
            existing.setLocation(location.trim());
            return Optional.of(existing);
        }
        return Optional.empty();
    }

    public boolean deleteEvent(int id) {
        return events.removeIf(e -> e.getId() == id);
    }
}