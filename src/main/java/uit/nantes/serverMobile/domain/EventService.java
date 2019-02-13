package uit.nantes.serverMobile.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.domain.util.EventCheck;
import uit.nantes.serverMobile.infra.jpa.IEventRepository;

@Service
public class EventService {

    @Autowired
    IEventRepository eventRepository;

    public List<Event> findAll() {
        List<Event> events = new ArrayList<Event>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }

    public Event findById(String id) {
        Event event = new Event();
        event.notExist();
        if (eventRepository.existsById(id)) {
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

    public boolean insert(Event event) {
        boolean result = false;
        boolean title = !this.eventRepository.existsById(event.getTitle());
        if (title) {
            if (EventCheck.checkInsert(event)) {
                eventRepository.save(event);
                result = true;
            }
        }
        return result;
    }

    public boolean update(String id, Event event) {
        boolean result = true;
        if (eventRepository.existsById(event.getId())) {
            Event eventUpdate = eventRepository.findById(id).get();
            if (EventCheck.checkUpdate(eventUpdate)) {
                eventUpdate.setTitle(event.getTitle());
                eventUpdate.setPlace(event.getPlace());
                eventRepository.save(eventUpdate);
            } else {
                result = false;
            }
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            result = true;
        }
        return result;
    }

}
