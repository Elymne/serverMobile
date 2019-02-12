package uit.nantes.serverMobile.application.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.domain.EventService;
import uit.nantes.serverMobile.domain.UserService;

@RestController
@RequestMapping(value = "/api")
public class EventController {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

	@Autowired
	EventService eventService;

	@Autowired
	UserService userService;

	@GetMapping(produces = "application/json", value = "/evenements")
	@ResponseBody
	public List<Event> getEvents() {
		return eventService.findAll();
	}

	@GetMapping(produces = "application/json", value = "/evenements/{idCreateur}")
	@ResponseBody
	public List<Event> getEventsByUser(@PathVariable("idCreateur") String idCreateur) {
		User creator = userService.findById(idCreateur);
		return eventService.findAllByUser(creator);
	}

	@GetMapping(produces = "application/json", value = "/evenement/{nom}")
	@ResponseBody
	public Event getEvent(@PathVariable("nom") String titleEvent) {
		return eventService.findByTitle(titleEvent);
	}

	@PostMapping(produces = "application/json", value = "/evenement/{idUtilisateur}/{nom}/{date}/{lieu}")
	@ResponseBody
	public Event createEvent(@PathVariable("idUtilisateur") String idCreator, @PathVariable("nom") String titleEvent,
			@PathVariable("date") String dateEvent, @PathVariable("lieu") String placeEvent) {
		User createur = userService.findById(idCreator);
		LocalDate localDatePlace = LocalDate.parse(dateEvent, formatter);
		Event newEvent = new Event(titleEvent, localDatePlace, placeEvent, createur);
		eventService.insertEvent(newEvent);

		return eventService.findById(newEvent.getId());
	}

	@PutMapping(produces = "application/json", value = "/evenement/{idEvenement}/{nouveauNom}/{nouvelleDate}/{nouveauLieu}")
	@ResponseBody
	public Event updateEvent(@PathVariable("idEvenement") String idEvent, @PathVariable("nouveauNom") String newName,
			@PathVariable("nouvelleDate") String newDate, @PathVariable("nouveauLieu") String newPlace) {

		return null;
	}

	@DeleteMapping(produces = "application/json", value = "/evenement/{id}")
	@ResponseBody
	public boolean deleteEvent(@PathVariable("id") String idEvent) {
		eventService.deleteEvent(idEvent);
		return false;
	}
}
