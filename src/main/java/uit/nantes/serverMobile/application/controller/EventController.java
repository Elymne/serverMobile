package uit.nantes.serverMobile.application.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.domain.EventService;
import uit.nantes.serverMobile.domain.UserService;

@RestController
@RequestMapping(value = "/api/evenement")
public class EventController {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

	@Autowired
	EventService eventService;

	@Autowired
	UserService userService;

	@GetMapping(produces = "application/json", value = "/")
	@ResponseBody
	public List<Event> getEvents() {
		return eventService.findAll();
	}

	@GetMapping(produces = "application/json", value = "/{nom}")
	@ResponseBody
	public Event getEventByTitle(@PathVariable("nom") String titleEvent) {
		return eventService.findByTitle(titleEvent);
	}

	@PostMapping(produces = "application/json", value = "/{idUtilisateur}")
	@ResponseBody
	public Event createEvent(@PathVariable("idUtilisateur") String idUser, @RequestBody Event event) {
		userService.findById(idUser);
		String uuid = UUID.randomUUID().toString();
		event.setId(uuid);
		eventService.insertEvent(event);
		return eventService.findById(uuid);
	}

	@PutMapping(produces = "application/json", value = "/{idEvenement}/{nouveauNom}/{nouvelleDate}/{nouveauLieu}")
	@ResponseBody
	public Event updateEvent(@RequestBody Event event) {
		eventService.updateEvent(event);
		return eventService.findById(event.getId());
	}

	@DeleteMapping(produces = "application/json", value = "/{id}")
	@ResponseBody
	public boolean deleteEvent(@PathVariable("id") String idEvent) {
		eventService.deleteEvent(idEvent);
		return false;
	}
}
