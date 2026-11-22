package kz.iitu.registration.controller;

import kz.iitu.registration.model.Event;
import kz.iitu.registration.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final EventService eventService;

    public RegistrationController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<Event> getEvents(@RequestParam(required = false) String title) {
        return eventService.getAllEvents(title);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/events")
    public ResponseEntity<?> createEvent(@RequestBody Event request) {
        if (request == null || isBlank(request.getTitle()) || isBlank(request.getLocation())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "title and location must not be blank"));
        }
        Event created = eventService.createEvent(request.getTitle(), request.getLocation());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody Event request) {
        if (request == null || isBlank(request.getTitle()) || isBlank(request.getLocation())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "title and location must not be blank"));
        }
        return eventService.updateEvent(id, request.getTitle(), request.getLocation())
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        boolean deleted = eventService.deleteEvent(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}