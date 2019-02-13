package uit.nantes.serverMobile.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.domain.security.EventCheck;
import uit.nantes.serverMobile.infra.jpa.IEventRepository;

@Service
public class EventService {

    @Autowired
    IEventRepository eventRepository;

    public Event findById(String id) {
        Event event = new Event();
        event.notExist();
        if(eventRepository.existsById(id)){
            event = eventRepository.findById(id).get();
            event.exist();
        }
        return event;
    }

    public Event findByTitle(String title) {
        Event result = new Event();
        result.notExist();
        for (Event event : eventRepository.findAll()) {
            if (event.getTitle().equals(title)) {
                result = event;
                result.exist();
                break;
            }
        }
        return result;
    }

    public boolean insertEvent(Event event) {
        eventRepository.save(event);
        return true;
    }

    public boolean updateEvent(Event event) {
        boolean result = false;
        Event repoEvent = eventRepository.findById(event.getId()).get();
        if (!EventCheck.checkEventUpdate(event, repoEvent)) {
            eventRepository.save(event);
            result = true;
        }
        return result;
    }

    public boolean deleteEvent(String id) {
        Event toDelete = null;
        boolean result = false;

        try {
            toDelete = eventRepository.findById(id).get();
            eventRepository.delete(toDelete);
            result = true;
        } catch (Exception e) {
            System.err.println(e);
        }
        return result;
    }

    public List<Event> findAll() {
        List<Event> events = new ArrayList<Event>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }
}
