package uit.nantes.serverMobile.application.controller;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

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
import uit.nantes.serverMobile.application.controller.util.JsonResponse;
import uit.nantes.serverMobile.application.controller.util.JsonToObject;
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

    @GetMapping(produces = "application/json", value = "/{title}")
    @ResponseBody
    public Event getEventByTitle(@PathVariable("title") String title) {
        return eventService.findByTitle(title);
    }

    @PostMapping(produces = "application/json")
    @ResponseBody
    public String addEvent(@RequestBody JSONObject jsonObject) throws JSONException, ParseException {
        Event event = JsonToObject.JsonToEvent(jsonObject);
        boolean result = eventService.insert(event);
        
        return JsonResponse.insertJsonResponse(result).toString();
    }

    @PutMapping(produces = "application/json", value = "/{id}")
    @ResponseBody
    public String updateEvent(@PathVariable String id, @RequestBody JSONObject jsonObject) throws JSONException, ParseException {
        
        Event event = JsonToObject.JsonToEvent(jsonObject);
        boolean result = eventService.update(id, event);
        
        return JsonResponse.updateJsonResponse(result).toString();
    }

    @DeleteMapping(produces = "application/json", value = "/{id}")
    @ResponseBody
    public String deleteEvent(@PathVariable("id") String id) throws JSONException {
        boolean result = eventService.delete(id);
        return JsonResponse.deleteJsonResponse(result).toString();
    }
}
