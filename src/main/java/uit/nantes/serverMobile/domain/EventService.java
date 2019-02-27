package uit.nantes.serverMobile.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.api.entities.Expense;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.api.pojo.EventPojo;
import uit.nantes.serverMobile.api.pojo.IdPojo;
import uit.nantes.serverMobile.domain.util.EventCheck;
import uit.nantes.serverMobile.domain.util.ExpenseManagement;
import uit.nantes.serverMobile.infra.jpa.IEventRepository;
import uit.nantes.serverMobile.infra.jpa.IExpenseRepository;
import uit.nantes.serverMobile.infra.jpa.IUserRepository;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
@Service
public class EventService {

    @Autowired
    IEventRepository eventRepository;

    @Autowired
    IUserRepository userRepository;
    
    @Autowired
    IExpenseRepository expenseRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(String id) {
        Event event = new Event();
        if (eventRepository.existsById(id)) {
            event = eventRepository.findById(id).get();
        }
        return event;
    }

    public Event findByTitle(String title) {
        Event result = new Event();
        for (Event event : eventRepository.findAll()) {
            if (event.getTitle().equals(title)) {
                result = event;
            }
        }
        return result;
    }

    public List<Event> findAllByUser(String idUser) {
        List<Event> result = new ArrayList<>();
        if (userRepository.existsById(idUser)) {
            for (Event event : eventRepository.findAll()) {
                for (User user : event.getUserList()) {
                    if (user.getId().equals(idUser)) {
                        result.add(event);
                    }
                }
            }
        }
        return result;
    }
    
    public List<Event> findAllByUserCreator(String idUser) {
        List<Event> result = new ArrayList<>();
        if (userRepository.existsById(idUser)) {
            for (Event event : eventRepository.findAll()) {
                if(event.getUser().getId().equals(idUser)){
                    result.add(event);
                }
            }
        }
        return result;
    }

    public boolean insert(EventPojo eventPojo) {
        boolean result = false;
        if (EventCheck.checkInsert(eventPojo)
                && userRepository.existsById(eventPojo.getUserId())) {
            Event event = new Event();
            event.createId();
            event.setActive(true);
            event.setTitle(eventPojo.getTitle());
            event.setDate(eventPojo.getDate());
            event.setPlace(eventPojo.getPlace());
            event.setUser(userRepository.findById(eventPojo.getUserId()).get());

            eventRepository.save(event);
            result = true;
        }
        return result;
    }

    public boolean update(String id, EventPojo eventPojo) {
        boolean result = false;
        if (eventRepository.existsById(id)) {
            Event event = eventRepository.findById(id).get();
            if (EventCheck.checkUpdate(eventPojo)) {
                event.setTitle(eventPojo.getTitle());
                event.setDate(eventPojo.getDate());
                event.setPlace(eventPojo.getPlace());
                event.setDescription(eventPojo.getDescription());
                event.setUser(userRepository.findById(eventPojo.getUserId()).get());
                eventRepository.save(event);
                result = true;
            }
        }
        return result;
    }

    public boolean addUser(String id, IdPojo idPojo) {
        boolean result = false;
        if (eventRepository.existsById(id)
                && userRepository.existsById(idPojo.getIdObject())) {
            Event event = eventRepository.findById(id).get();
            event.getUserList().add(userRepository.findById(idPojo.getIdObject()).get());
            Expense expense = ExpenseManagement.createExpenseByCreating(userRepository.findById(idPojo.getIdObject()).get(),
                    event);
            eventRepository.save(event);
            expenseRepository.save(expense);
            
            result = true;
        }
        return result;
    }

    public boolean removeUser(String id, IdPojo idPojo) {
        boolean result = false;
        if (eventRepository.existsById(id)
                && userRepository.existsById(idPojo.getIdObject())) {
            Event event = eventRepository.findById(id).get();
            event.getUserList().remove(userRepository.findById(idPojo.getIdObject()).get());
            eventRepository.save(event);
            result = true;
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
