package uit.nantes.serverMobile.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.domain.EventService;

@RestController
@RequestMapping(value = "/api")
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping(produces = "application/json", value = "/evenements")
	public List<Event> getEvents() {
		return eventService.findAll();
	}

	@GetMapping(produces = "application/json", value = "/evenement")
	public Event getEvent() {
		return eventService.findById("");
	}

	@PostMapping(produces = "application/json", value = "/evenement")
	public Event createEvent(@RequestBody Event event) {
		eventService.insertEvent(event);
		return eventService.findById(event.getId());
	}

	@DeleteMapping(produces = "application/json", value = "/evenement")
	public boolean deleteEvent(@RequestBody Event event) {
		eventService.deleteEvent(event);
		return true;
	}

	@PutMapping(produces = "application/json", value = "/evenement")
	@ResponseBody
	public Event updateEvent(@RequestBody Event event) {
		eventService.updateEvent(event);
		return eventService.findById(event.getId());
	}
}
