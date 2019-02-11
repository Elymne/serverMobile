package uit.nantes.serverMobile.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.infra.IEventRepository;

@Service
public class EventService {

	@Autowired
	IEventRepository eventRepository;
	
	public Event findById(String id) {
		return eventRepository.findById(id).get();
	}
	
	public void insertEvent(Event event) {
		eventRepository.save(event);
	}
	
	public void updateEvent(Event event) {
		eventRepository.save(event);
	}
	
	public void deleteEvent(Event event) {
		eventRepository.delete(event);
	}
	
	public List<Event> findAll(){
		List<Event> events = new ArrayList<Event>();
		eventRepository.findAll().forEach(events::add);
		return events; 
	}
}
