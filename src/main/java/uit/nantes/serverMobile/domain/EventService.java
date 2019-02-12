package uit.nantes.serverMobile.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.infra.jpa.IEventRepository;

@Service
public class EventService {

	@Autowired
	IEventRepository eventRepository;
	
	public Event findById(String id) {
		return eventRepository.findById(id).get();
	}
	
	public Event findByTitle(String title) {
		return eventRepository.findByTitle(title);
	}
	
	public void insertEvent(Event event) {
		eventRepository.save(event);
	}
	
	public void updateEvent(Event event) {
		eventRepository.save(event);
	}
	
	public void deleteEvent(String id) {
		eventRepository.deleteById(id);
	}
	
	public List<Event> findAll(){
		List<Event> events = new ArrayList<Event>();
		eventRepository.findAll().forEach(events::add);
		return events; 
	}
	
	public List<Event> findAllByUser(User createur){
		List<Event> userEvents = new ArrayList<Event>();
		eventRepository.findAllByUser(createur).forEach(userEvents::add);
		return userEvents;
	}
        
        public long getCount(){
            return eventRepository.count();
        }
}
