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
        Event result = new Event();
        try {
            result = eventRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            System.err.println(e);
        }
        return result;
    }

    public Event findByTitle(String title) {
        Event result = new Event();
        try {
            result = eventRepository.findByTitle(title);
        } catch (NoSuchElementException e) {
            System.err.println(e);
        }
        return result;
    }

    public List<Event> findAllByIdUser(String idUser) {
        List<Event> userEvents = new ArrayList<Event>();
        eventRepository.findAllByIdUser(idUser).forEach(userEvents::add);
        return userEvents;
    }

    public boolean insertEvent(Event event) {
        boolean result = false;
        Event repoEvent = eventRepository.findByTitle(event.getTitle());
        if (!EventCheck.checkEventInsert(event, repoEvent)) {
            eventRepository.save(event);
            result = true;
        }
        return result;
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
